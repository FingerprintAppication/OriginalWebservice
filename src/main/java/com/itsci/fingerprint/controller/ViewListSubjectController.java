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
import com.itsci.fingerprint.model.Period;
import com.itsci.fingerprint.model.Section;
import com.itsci.fingerprint.model.Student;
import com.itsci.fingerprint.model.Subject;

@RestController
public class ViewListSubjectController {
	ViewListSubjectManager mng = new ViewListSubjectManager();

	@RequestMapping(value = "/viewListSubject", method = RequestMethod.POST)
	public List<Subject> searchSubject(@RequestBody String j) throws SQLException, JSONException, IOException {
		System.out.println("PERSON MODEL " + j);

		JSONObject jsonObject = new JSONObject(j);
		long personID = jsonObject.getLong("personID");
		System.out.println("PERSON ID : " + personID);

		String typePerson = mng.checkPersonTeacher(personID);

		List<Subject> listSubject = new ArrayList<>();

		if (typePerson.equals("teacher")) {
			String teacherID = mng.searchTeacherID(personID);
			listSubject = mng.searchTeacherSubject(teacherID);
		} else {
			listSubject = mng.searchStudentSubject(personID);
		}

		return listSubject;
	}

	@RequestMapping(value = "/viewListSubject/searchStudentParent", method = RequestMethod.POST)
	public List<Student> searchStudentParent(@RequestBody String j) throws SQLException, JSONException, IOException {
		System.out.println("PERSON MODEL " + j);

		JSONObject jsonObject = new JSONObject(j);
		long personID = jsonObject.getLong("personID");
		System.out.println("PERSON ID : " + personID);

		List<Student> listStudent = mng.searchParentStudent(personID);
		for (Student s : listStudent) {
			System.out.println("Get Student with Parent " + s.getStudentID());
		}
		System.out.println("listStudent @@@@" + listStudent.toString());

		return listStudent;
	}

	@RequestMapping(value = "/viewListSubject/period", method = RequestMethod.POST)
	public List<Section> searchPeriod(@RequestBody String j) throws SQLException, JSONException, IOException {

		JSONObject jsonObject = new JSONObject(j);
		long subjectID = jsonObject.getLong("subjectID");

		List<Section> listSection = new ArrayList<>();
		listSection = mng.searchPeriod(subjectID);

		for (Section s : listSection) {
			for (Period p : s.getPeriodList()) {
				p.setScheduleList(null);
			}
		}
		return listSection;
	}
}
