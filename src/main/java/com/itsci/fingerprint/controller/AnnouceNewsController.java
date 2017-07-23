package com.itsci.fingerprint.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.AnnouceNewsManager;
import com.itsci.fingerprint.model.AnnouceNews;
import com.itsci.fingerprint.model.Schedule;
import com.itsci.fingerprint.model.Teacher;

@RestController
public class AnnouceNewsController {
	AnnouceNewsManager mng = new AnnouceNewsManager();

	@RequestMapping(value = "/annouceNews/searchDate", method = RequestMethod.POST)
	public List<String> VerifyLogin(@RequestBody String j) throws SQLException, JSONException, IOException {

		System.out.println("Json : " + j);

		JSONObject json = new JSONObject(j);
		long periodID = json.getLong("periodID");

		Date d = new Date();
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
		String[] sp = selectedDate.split("-");
		String newDate = sp[2] + "-" + sp[1] + "-" + sp[0] + " 00:00:00.0";

		JSONObject jsonPeriod = jsonSchedule.getJSONObject("period");
		long periodID = jsonPeriod.getLong("periodID");

		System.out.println("newsType + " + newsType);
		System.out.println("detail + " + detail);
		System.out.println("newDate + " + newDate);
		System.out.println("personID + " + personID);
		System.out.println("periodID + " + periodID);

		Schedule schedule = mng.searchSchedule(periodID, newDate);
		Teacher teacher = mng.searchTeacher(personID);

		AnnouceNews annouceNews = new AnnouceNews();
		annouceNews.setAnnouceNewsType(newsType);
		annouceNews.setDetail(detail);
		annouceNews.setSchedule(schedule);
		annouceNews.setTeacher(teacher);
		System.out.println(annouceNews.toString());
		String result = mng.insertAnnouceNews(annouceNews);
		System.out.println("Result // " + result);

		return result;
	}
}
