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

import com.itsci.fingerprint.manager.ViewListSubjectManager;
import com.itsci.fingerprint.model.Section;
import com.itsci.fingerprint.model.Subject;

@RestController
public class ViewListSubjectController {

	@RequestMapping(value = "/viewListSubject", method = RequestMethod.POST)
	public List<Subject> searchSubject(@RequestBody String j) throws SQLException, JSONException, IOException {
		System.out.println("PERSON MODEL " + j);

		ViewListSubjectManager mng = new ViewListSubjectManager();
		JSONObject jsonObject = new JSONObject(j);
		long personID = jsonObject.getLong("personID");
		System.out.println("PERSON ID : " + personID);

		String typePerson = mng.checkPerson(personID);
		List<Subject> listSubject = new ArrayList<>();
		if (typePerson.equals("student")) {
			listSubject = mng.searchStudentSubject(personID);
		} else if (typePerson.equals("teacher")) {
			String teacherID = mng.searchTeacherID(personID);
			listSubject = mng.searchTeacherSubject(teacherID);
		}

		return listSubject;
	}
}
