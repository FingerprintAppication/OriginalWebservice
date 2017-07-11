package com.itsci.fingerprint.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.itsci.fingerprint.manager.ParentManager;
import com.itsci.fingerprint.manager.PersonManager;
import com.itsci.fingerprint.manager.StudentManager;
import com.itsci.fingerprint.manager.TeacherManager;
import com.itsci.fingerprint.model.Parent;
import com.itsci.fingerprint.model.Person;
import com.itsci.fingerprint.model.Student;
import com.itsci.fingerprint.model.Teacher;

@RestController
public class verifyParentController {
	StudentManager sm = new StudentManager();
	ParentManager pm = new ParentManager();
	PersonManager psm = new PersonManager();
	String result;
	Student student;
	Parent parentNew;
	Person person;
	Long lastPerson;
	
	@RequestMapping(value= "/student",method = RequestMethod.POST)
	public Student searchStudent(@RequestBody Student student){
		System.out.println(student.getParentPhone() +" received");
		student = sm.searchStudent(student.getStudentID());
		return student;
	}
	
	@RequestMapping(value= "/verifyparent",method = RequestMethod.POST)
	public String verifyParent(@RequestBody Parent parent){
		student = sm.findStudentVerify(parent.getTitle());
		System.out.println(parent.getTitle() +" findStudent");
		if(student.getParentPhone() != null){
			parent.setTitle("");
			result +=" "+ pm.insertParent(parent);
			lastPerson = psm.getLastPerson();
			System.out.println(lastPerson+" last");
			parent.setPersonID(lastPerson);
			student.setParent(parent);
			result +=" "+sm.updateStudentParent(student);
			System.out.println(result);
			
		}else{
			result = "duplicate";
		}
		return result;
	}
	
	
	@RequestMapping(value= "/students",method = RequestMethod.GET)
	public Student searchStudents(@RequestParam(value="id") String id){
		student = sm.searchStudent(Long.parseLong(id));
		return student;
	}

	@RequestMapping(value= "/teachers",method = RequestMethod.GET)
	public List<Teacher> getTeacher(){
		TeacherManager tm = new TeacherManager();
		
		return tm.AllTeacher();
	}
}
