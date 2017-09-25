package com.itsci.fingerprint.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.itsci.fingerprint.fcm.AndroidPushNotificationsService;
import com.itsci.fingerprint.manager.AnnouceNewsManager;
import com.itsci.fingerprint.manager.SubjectManager;
import com.itsci.fingerprint.manager.TopicManager;
import com.itsci.fingerprint.model.AnnouceNews;
import com.itsci.fingerprint.model.Attendance;
import com.itsci.fingerprint.model.Schedule;
import com.itsci.fingerprint.model.Section;
import com.itsci.fingerprint.model.Subject;
import com.itsci.fingerprint.model.Teacher;
import com.itsci.fingerprint.model.Topic;

@RestController
public class AnnouceNewsController {
	AnnouceNewsManager mng = new AnnouceNewsManager();
	TopicManager tm = new TopicManager();

	@Autowired
	AndroidPushNotificationsService androidPushNotificationsService/*
																	 * =new
																	 * AndroidPushNotificationsService
																	 * ()
																	 */;

	@RequestMapping(value = "/annouceNews/searchDate", method = RequestMethod.POST)
	public List<String> VerifyLogin(@RequestBody String j) throws SQLException, JSONException, IOException {

		System.out.println("Json : " + j);

		JSONObject json = new JSONObject(j);
		long periodID = json.getLong("periodID");

		Date d = new Date();
		//Here set date when finish term
		d.setMonth(7);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String today = sdf.format(d);
		System.out.println("TODAY " + today);

		List<Schedule> list = mng.searchScheduleDate(periodID);

		List<String> listDate = new ArrayList<>();

		for (Schedule i : list) {
			String[] sp = i.getScheduleDate().toString().split(" ");
			try {
				Date date = sdf.parse(sp[0]);
				if (date.after(d)) {
					listDate.add(sp[0]);
					System.out.println("ADD " + sp[0]);
				}
			} catch (ParseException e) {

				e.printStackTrace();
			}

		}

		return listDate;
	}

	@RequestMapping(value = "/annouceNews", method = RequestMethod.POST)
	public String addAnnouceNews(@RequestBody String j) throws SQLException, JSONException, IOException {
		System.out.println("Json : " + j);

		JSONObject json = new JSONObject(j);
		String newsType = json.getString("annouceNewsType");
		String detail = json.getString("detail");

		JSONObject teacherJson = json.getJSONObject("teacher");
		long personID = teacherJson.getLong("personID");

		JSONObject jsonSchedule = json.getJSONObject("schedule");
		String selectedDate = jsonSchedule.getString("scheduleDate");
		/*String[] sp = selectedDate.split("-");
		String newDate = sp[2] + "-" + sp[1] + "-" + sp[0] + " 00:00:00.0";
		System.out.println("newDate "+newDate);
		System.out.println("from schedule "+selectedDate);*/
		JSONObject jsonPeriod = jsonSchedule.getJSONObject("period");
		long periodID = jsonPeriod.getLong("periodID");

		Schedule schedule = mng.searchSchedule(periodID, selectedDate);
		Teacher teacher = mng.searchTeacher(personID);

		AnnouceNews annouceNews = new AnnouceNews();
		annouceNews.setAnnouceNewsType(newsType);
		annouceNews.setDetail(detail);
		annouceNews.setSchedule(schedule);
		annouceNews.setTeacher(teacher);

		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		annouceNews.setAnnouceDate(now.getTime());

		List<AnnouceNews> listAnnouce = mng.getAllAnnouceNews();

		for (AnnouceNews i : listAnnouce) {
			if (i.getAnnouceDate().before(new Date())) {
				System.out.println("expire annouce" + mng.deleteAnnouceNews(i));
			}
		}

		Section section = mng.searchSectionByPeriod(annouceNews.getSchedule().getPeriod().getPeriodID());
		String result = mng.insertAnnouceNews(annouceNews);

		if (result.equals("insert success")) {
			String topicName = compareSubjectToEnglish(section.getSubject().getSubjectNumber());
			String teacherAnnounce = annouceNews.getTeacher().getFirstName() + " "
					+ annouceNews.getTeacher().getLastName();
			setJSONData(topicName,
					section.getSubject().getSubjectNumber() + " " + section.getSubject().getSubjectName(),
					"ผู้ประกาศข่าว : " + teacherAnnounce + " \n" + annouceNews.getDetail());

			if (newsType.equals("ยกเลิกคาบเรียน")) {
				System.out.println("cancel attendance with scheduleID : " + schedule.getScheduleID());
				List<Attendance> listAttendance = mng.SearchAttendanceWithScheduleID(schedule.getScheduleID());
				String resultUpdate = "";

				for (Attendance a : listAttendance) {
					a.setStatus("ยกเลิก");
					resultUpdate = mng.updateAttendance(a);
				}

				if (resultUpdate.equals("update success")) {
					return "1";
				} else {
					System.out.println("can't update " + mng.deleteAnnouceNews(annouceNews));
					return "0";
				}
			}

			return "1";
		}

		return "0";
	}

	// test controller
	@RequestMapping(value = "/FCM", method = RequestMethod.GET)
	public String testPostFCM() {
		// return compareSubjectToEnglish("ทส341");
		return setJSONData("Special_Topic_IN_Information_Technology", "ทดสอบ FCM real mobiles",
				"Hello firebase cloud messaging!") + "";
	}

	// set json data
	public ResponseEntity<String> setJSONData(String TOPIC, String title, String message) {
		JSONObject body = new JSONObject();
		try {
			body.put("to", "/topics/" + TOPIC);
			body.put("priority", "high");
			JSONObject notification = new JSONObject();
			notification.put("title", title);
			notification.put("body", message);
			body.put("notification", notification);
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		// HttpEntity<String> request = new HttpEntity<>(body.toString());

		try {
			CompletableFuture<String> pushNotification = androidPushNotificationsService.send(body.toString());
			CompletableFuture.allOf(pushNotification).join();

			String firebaseResponse = pushNotification.get();
			// System.out.println(firebaseResponse+" Response!");
			System.out.println(body + " Response!");
			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}

	public String compareSubjectToEnglish(String sub) {
		String subject = "";
		List<Topic> listSubject = tm.getAllTopic();
		System.out.println("compared! " + listSubject.size());
		for (Topic t : listSubject) {
			if (t.getSubject().getSubjectNumber().equalsIgnoreCase(sub)) {
				subject = t.getTopicName();
				System.out.println("compared!");
				break;
			}
		}
		return subject;
	}
}
