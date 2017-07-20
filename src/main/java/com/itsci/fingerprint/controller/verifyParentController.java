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

import com.itsci.fingerprint.manager.LoginManager;
import com.itsci.fingerprint.manager.ParentManager;
import com.itsci.fingerprint.manager.PersonManager;
import com.itsci.fingerprint.manager.StudentManager;
import com.itsci.fingerprint.manager.TeacherManager;
import com.itsci.fingerprint.model.Login;
import com.itsci.fingerprint.model.Parent;
import com.itsci.fingerprint.model.Person;
import com.itsci.fingerprint.model.Student;
import com.itsci.fingerprint.model.Teacher;
//�դ�Ѻ
@RestController
public class verifyParentController {
	StudentManager sm = new StudentManager();
	ParentManager pm = new ParentManager();
	PersonManager psm = new PersonManager();
	String result;
	Student student;
	Parent parentNew;
	Person person;
	LoginManager lmg;
	Long lastPerson;
	
	
	@RequestMapping(value= "/verifyparent",method = RequestMethod.POST)
	public String verifyParent(@RequestBody Student studentParent){
		result = null;
		student = sm.searchStudent(studentParent.getStudentID());
		
		if(student.getPersonID()==0){
			result = "ไม่พบรหัสนักศึกษาในฐานข้อมูล";	
		}else if (!student.getParentPhone().equals(studentParent.getParent().getPhoneNo())){
			result = "หมายเลขโทรศัพท์ไม่ตรงกับฐานข้อมูล";

		}else if (null == student.getParent()){
			parentNew = null;
			parentNew = studentParent.getParent();
			pm.insertParent(parentNew);
			lastPerson = psm.getLastPerson();
			List<Student> updateStudent = sm.findStudentByParentPhone(parentNew.getPhoneNo());
			System.out.println("size "+updateStudent.size());
			for(Student s:updateStudent){
				parentNew.setPersonID(lastPerson);
				s.setParent(parentNew);
				String res = sm.updateStudentParent(s);
				System.out.println(res +" update students !");
			}
			Login login = new Login();
			Person p = new Person();
			p.setPersonID(lastPerson);
			p.setTitle(parentNew.getTitle());
			p.setFirstName(parentNew.getFirstName());
			p.setLastName(parentNew.getLastName());
			login.setPerson(p);
			login.setUsername(parentNew.getPhoneNo());
			login.setPassword(parentNew.getPhoneNo());
			
			lmg = new LoginManager();
			lmg.saveLogin(login);
			
			result = "ลงทะเบียนเสร็จสมบูรณ์  ชื่อผู้ใช้งานและรหัสผ่านคือ หมายเลขโทรศัพท์ของคุณ";
			
		}else {
			result = "รหัสนักศึกษานี้ได้มีการลงทะเบียนแล้ว";
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
