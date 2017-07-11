package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Parent;
import com.itsci.fingerprint.model.Person;
import com.itsci.fingerprint.model.Student;

import demo.HibernateConnection;

public class StudentManager {
	Student student;
	String result;

	public Student searchStudent(Long id) {
		List<Student> list = new ArrayList<Student>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Student where studentID=" + id).list();
			session.close();
			System.out.println("GET IN COMPLETED");

		} catch (Exception s) {
			s.getStackTrace();

		}
		if (list.size() == 0) {
			student = new Student();
		} else {
			student = list.get(0);
		}
		return student;
	}

	public String updateStudentParent(Student student) {
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(student);
			session.getTransaction().commit();
			session.close();
			result = "update successfully";

		} catch (Exception s) {
			s.getStackTrace();
			result = "can not update "+s.getMessage();
		}

		return result;
	}

	public Student findStudentVerify(String students) {
		List<Student> list = new ArrayList<Student>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Student where studentID=" + students).list();
			session.close();
			System.out.println("GET IN COMPLETED");

		} catch (Exception s) {
			s.getStackTrace();

		}
		if(list.get(0).getParent() == null){
			student = list.get(0);
		}else{
			student = new Student();
		}

		return student;
	}
}
