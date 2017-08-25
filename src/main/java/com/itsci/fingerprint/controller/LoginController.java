package com.itsci.fingerprint.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsci.fingerprint.manager.LoginManager;
import com.itsci.fingerprint.manager.TopicManager;
import com.itsci.fingerprint.model.Login;
import com.itsci.fingerprint.model.Person;
import com.itsci.fingerprint.model.Subject;
import com.itsci.fingerprint.model.Topic;

@RestController
public class LoginController {
	TopicManager tm = new TopicManager();
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, List<String>> VerifyLogin(@RequestBody String j)
			throws SQLException, JSONException, IOException {
		Map<String, List<String>> map = new HashMap<String, List<String>>();

		LoginManager mng = new LoginManager();
		System.out.println("Json : " + j);

		JSONObject json = new JSONObject(j);
		String username = json.getString("username");
		String password = json.getString("password");

		List<Login> login = mng.searchLogin(username, password);
		List<String> listLogin = new ArrayList<>();

		listLogin.add(login.get(0).getUsername());
		listLogin.add(login.get(0).getPassword());
		listLogin.add(login.get(0).getPerson().getTitle());
		listLogin.add(login.get(0).getPerson().getFirstName());
		listLogin.add(login.get(0).getPerson().getLastName());
		listLogin.add("" + login.get(0).getPerson().getPersonID());

		if (login.size() != 0) {
			System.out.println("Login Success");
			long personID = login.get(0).getPerson().getPersonID();

			String typePerson = mng.checkPersonTeacher(personID);
			List<Subject> listSubject = new ArrayList<>();
			List<String> listSubNumber = new ArrayList<>();

			if (typePerson.equals("teacher")) {
				String teacherID = mng.searchTeacherID(personID);
				listSubject = mng.searchTeacherSubject(teacherID);
				listLogin.add(login.get(0).getPerson().getMajor().getMajorName());
				listLogin.add(login.get(0).getPerson().getMajor().getFaculty().getFacultyName());
				listLogin.add("teacher");
			} else {
				if (login.get(0).getPerson().getFingerprintData() == null) {
					listLogin.add(null);
					listLogin.add(null);
					listLogin.add("parent");
				} else {
					listSubject = mng.searchStudentSubject(personID);
					listLogin.add(login.get(0).getPerson().getMajor().getMajorName());
					listLogin.add(login.get(0).getPerson().getMajor().getFaculty().getFacultyName());
					listLogin.add("student");
					listLogin.add("" + login.get(0).getPerson().getFingerprintData().getFingerprintNumber());
				}
			}

			for (Subject i : listSubject) {
				String subjectTopic = compareSubjectToEnglish(i.getSubjectNumber());
				listSubNumber.add(subjectTopic);
			}

			map.put("login", listLogin);
			map.put("subject", listSubNumber);

			System.out.println("login" + listLogin);
			System.out.println("subject" + listSubNumber);
			System.out.println(map);

			return map;
		}

		System.out.println("Invalid login");
		Map<String, List<String>> invalid = new HashMap<String, List<String>>();
		return invalid;
	}
	
	public String compareSubjectToEnglish (String sub) {
		String subject = "";
		List<Topic> listSubject = tm.getAllTopic();
		System.out.println("compared! "+listSubject.size());
		for(Topic t:listSubject){
			if(t.getSubject().getSubjectNumber().equalsIgnoreCase(sub)){
				subject = t.getTopicName();
				System.out.println("compared!");
				break;
			}
		}
		return subject;
	}
}
