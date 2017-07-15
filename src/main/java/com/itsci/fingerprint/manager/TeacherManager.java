package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Student;
import com.itsci.fingerprint.model.Teacher;

import demo.HibernateConnection;

public class TeacherManager {
	String resultForFindTeacher = "";
	
	public List<Teacher> AllTeacher() {
		List<Teacher> list = new ArrayList<Teacher>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Teacher").list();
			session.close();
			System.out.println("GET IN COMPLETED");

		} catch (Exception s) {
			s.getStackTrace();

		}
		

		return list;
	}
	
	
	public String findByTeacherId(String id) {
		List<Teacher> list = new ArrayList<Teacher>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Teacher  where personID="+id).list();
			session.close();
			System.out.println("GET IN COMPLETED");

		} catch (Exception s) {
			s.getStackTrace();

		}
		if(list.size()!=0){
			resultForFindTeacher = "teacher";
		}else{
			resultForFindTeacher = "";
		}

		return resultForFindTeacher;
	}

}
