package com.itsci.fingerprint.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.ViewLeaveHistoryManager;
import com.itsci.fingerprint.model.InformLeave;
import com.itsci.fingerprint.model.Section;

@RestController
public class ViewLeaveHistoryController {
	ViewLeaveHistoryManager mng = new ViewLeaveHistoryManager();

	@RequestMapping(value = "/leaveHistory", method = RequestMethod.POST)
	public List<InformLeave> searchLeaveHistory(@RequestBody String j) throws SQLException, JSONException, IOException {

		System.out.println("JSON : " + j.toString());

		JSONObject json = new JSONObject(j);
		long personID = json.getLong("personID");

		List<InformLeave> list = new ArrayList<>();
		list = mng.searchInformLeave(personID);

		for (InformLeave i : list) {
			i.getSchedule().getPeriod().setScheduleList(null);
			i.getSchedule().getPeriod().getRoom().setFingerprintScannerList(null);
			i.getStudent().setFingerprintData(null);
			Section section = mng.searchSection(i.getSchedule().getPeriod().getPeriodID());
			section.setPeriodList(null);
			
			i.getSchedule().getPeriod().setSection(section);
			System.out.println(i.toString());
		}
		
		return list;
	}

}
