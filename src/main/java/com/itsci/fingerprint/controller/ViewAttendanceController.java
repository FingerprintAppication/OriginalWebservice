package com.itsci.fingerprint.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.AttendanceManager;
import com.itsci.fingerprint.manager.EnrollmentManager;
import com.itsci.fingerprint.manager.TeacherManager;
import com.itsci.fingerprint.model.Attendance;
import com.itsci.fingerprint.model.Enrollment;
import com.itsci.fingerprint.model.Period;
import com.itsci.fingerprint.model.Section;

@RestController
public class ViewAttendanceController {
	EnrollmentManager emm;
	AttendanceManager am;
	TeacherManager tcm;
	Enrollment enrollment;
	Period period;
	String result = "";
	
	@RequestMapping(value="/attendance",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,List<Attendance>> getEnrollment (@RequestBody Period requestData) {
		emm = new EnrollmentManager();
		period = requestData;
		/*Receive data*/
		String personID = period.getStudyType();
		String subjectNumber = period.getComingTime();
		String periodID = Long.toString(period.getPeriodID());
		/*get data*/
		System.out.println(period.getPeriodID()+" this is request data!");
		System.out.println("test request json parent "+personID+" "+subjectNumber+" "+periodID);
		enrollment = emm.getHibernateEnrollment(personID,subjectNumber,periodID);
		/*remove data that not use */
		emm.removeDataNotUse(enrollment);
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
	
	@RequestMapping(value="/attendanceTeacher",method = RequestMethod.GET)
	public Map<String,List<Enrollment>> getEnrollmentForTeacher (@RequestParam(value="section") 
																String section,@RequestParam(value="period") String period) {
		emm = new EnrollmentManager();
		String sectionID = section;
		String periodID = period;
		Map<String,List<Enrollment>> allEnrollment = new HashMap<String,List<Enrollment>>();
		List<Enrollment> listEnroll = emm.getAllEnrollment(sectionID,periodID);
		for(int i=0;i<listEnroll.size();i++){
			emm.removeDataNotUse(listEnroll.get(i));
			listEnroll.get(i).setSection(null);
			listEnroll.get(i).getStudent().setFingerprintData(null);
		}
		allEnrollment.put("allEnrollment", listEnroll);
		return allEnrollment;
	}	
	
	@RequestMapping(value="/searchTeacher",method = RequestMethod.GET)
	@ResponseBody
	public String getSection (@RequestParam(value="id",required=false) String a ) {
		tcm = new TeacherManager();
		System.out.println(a +" response "+tcm.findByTeacherId(a));
		return tcm.findByTeacherId(a);
	}
	
	@RequestMapping(value="/attendanceParent",method = RequestMethod.GET)
	public /*Map<String,List<Enrollment>>*/List<Enrollment> getEnrollmentForParent (@RequestParam(value="personId") String personID ) {
		emm = new EnrollmentManager();
		am = new AttendanceManager();
		Map<String,List<Enrollment>> map = new HashMap<String,List<Enrollment>>();
		List<Enrollment> listEnroll = new ArrayList<Enrollment>();
		listEnroll =  emm.getAllEnrollmentByPersonID(personID);
		System.out.println("ENROLLMENT SIZES "+listEnroll.size());
		for(int o=0;o<listEnroll.size();o++){
			Section sec = am.searchSectionByPeriod(listEnroll.get(o).getSection().getSectionID()+"");
			listEnroll.get(o).setSection(sec);
			listEnroll.get(o).getStudent().setFingerprintData(null);
			listEnroll.get(o).getAttendanceList().clear();
			for(int u=0;u<listEnroll.get(o).getSection().getPeriodList().size();u++){
				Long periodID = listEnroll.get(o).getSection().getPeriodList().get(u).getPeriodID();
				System.out.println("PERIODID + "+periodID);
				List<Attendance> listAt = am.searchAttendanceByPeriod(periodID, listEnroll.get(o).getStudent().getPersonID());
				
				listEnroll.get(o).getAttendanceList().addAll(listAt);
				System.out.println("addAll ="+u+" "+listEnroll.get(o).getAttendanceList().size());
				
			}
			
		}
		
		for(int g=0;g<listEnroll.size();g++){
			listEnroll.get(g).getSection().setPeriodList(null);
			for(int y=0;y<listEnroll.get(g).getAttendanceList().size();y++){
				listEnroll.get(g).getAttendanceList().get(y).setEnrollment(null);
				listEnroll.get(g).getAttendanceList().get(y).getSchedule().setPostpone(null);
				listEnroll.get(g).getAttendanceList().get(y).getSchedule().getPeriod().setSection(null);
				listEnroll.get(g).getAttendanceList().get(y).getSchedule().getPeriod().setRoom(null);
				listEnroll.get(g).getAttendanceList().get(y).getSchedule().getPeriod().setScheduleList(null);
			}
			
		}
		map.put("ListEnrollment", listEnroll);
		return listEnroll;
	}
	
	

}
