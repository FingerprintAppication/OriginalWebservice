package com.itsci.fingerprint.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value="/enroll",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public Enrollment getEnrollment () {
		enrollment = 	emm.getHibernateEnrollment("6227","ทส101");
		enrollment.setSection(null);
		
		for(int u=0;u<enrollment.getAttendanceList().size();u++){
			enrollment.getAttendanceList().get(u).setEnrollment(null);
			enrollment.getAttendanceList().get(u).setSchedule(null);
		}
		
		
		/*List<Attendance> listsy = enrollment.getAttendanceList();
		String h = "";
		for(Attendance a:listsy){
			System.out.println(a.getStatus()+" status");
			h+=a.getStatus()+" ";
		}*/
		return enrollment;
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
