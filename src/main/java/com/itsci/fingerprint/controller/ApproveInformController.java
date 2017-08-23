package com.itsci.fingerprint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.AttendanceManager;
import com.itsci.fingerprint.manager.InformLeaveManager;
import com.itsci.fingerprint.model.Attendance;
import com.itsci.fingerprint.model.InformLeave;

@RestController
public class ApproveInformController {
	InformLeaveManager iLm = new InformLeaveManager();
	AttendanceManager adm = new AttendanceManager();
	String result ="";
	
	@RequestMapping(value = "/updateInformStatus", method = RequestMethod.POST)
	public String updateInformLeave (@RequestBody InformLeave inform) {
		InformLeave search = iLm.searchInformLeaveById(inform.getInformLeaveID());
		if("อนุมัติ".equals(inform.getStatus())){
			search.setStatus(inform.getStatus());
		
			result = iLm.updateInformLeave(search);
			
			Attendance att = adm.searchAttendanceByScheduleId(inform.getSchedule().getScheduleID(),
					inform.getStudent().getPersonID());
			
			att.setStatus("ลา");
			result= adm.updateAttendance(att);
			System.out.println(att.getAttendanceID()+ "AttID");
			System.out.println(result);
		
		}else{
			search.setStatus(inform.getStatus());
			search.setCaseDetail(inform.getCaseDetail());
			System.out.println(inform.getCaseDetail()+" 2");
			result = iLm.updateInformLeave(search);
			System.out.println(result);
		}
		
		return result;
	}

}
