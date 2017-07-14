package com.itsci.fingerprint.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.EnrollmentManager;
import com.itsci.fingerprint.model.Attendance;
import com.itsci.fingerprint.model.Enrollment;
import com.itsci.fingerprint.model.Period;
import com.itsci.fingerprint.model.Section;

@RestController
public class ViewAttendanceController {
	EnrollmentManager emm = new EnrollmentManager();
	Enrollment enrollment;
	Period period;
	@RequestMapping(value="/attendance",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,List<Attendance>> getEnrollment (@RequestBody Period requestData) {
		period = requestData;
		/*Receive data*/
		String personID = period.getStudyType();
		String subjectNumber = period.getComingTime();
		String periodID = Long.toString(period.getPeriodID());
		/*get data*/
		System.out.println(period.getPeriodID()+" this is request data!");
		enrollment = emm.getHibernateEnrollment(personID,subjectNumber,periodID);
		/*remove data that not use */
		for(int u=0;u<enrollment.getAttendanceList().size();u++){
			enrollment.getAttendanceList().get(u).setEnrollment(null);
			enrollment.getAttendanceList().get(u).setSchedule(null);
		}
		/*remove data that not use */
		List<Attendance> listAttendace = enrollment.getAttendanceList();
		for(int y = 0;y<listAttendace.size();y++){
			System.out.println(listAttendace.get(y).getStatus()+" ##########");
			listAttendace.get(y).setEnrollment(null);
			listAttendace.get(y).setSchedule(null);
		}
		Map<String,List<Attendance>> list = new HashMap<String,List<Attendance>>();
		list.put("attendace",listAttendace);
		return list;
	}
	
	
	@RequestMapping(value="/sections",method = RequestMethod.GET)
	public ArrayList<Section> getSection () {
		ArrayList<Section> ssss=emm.findSubjectBySubjectNumberAndSubjectName("6227","ทส101","เทคโนโลยีสารสนเทศคอมพิวเตอร์");
		
		
		for(Period p:ssss.get(0).getPeriodList()){	
			
			System.out.println(Long.toString(p.getPeriodID())+" period");
		}
			return null;
	}
	
	
	

}
