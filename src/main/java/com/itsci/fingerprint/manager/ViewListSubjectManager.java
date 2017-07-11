package com.itsci.fingerprint.manager;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Section;
import com.itsci.fingerprint.model.Subject;
import com.itsci.fingerprint.model.Teacher;

import demo.HibernateConnection;

public class ViewListSubjectManager {

	public String checkPerson(long personID) {
		List<Object> list = new ArrayList<>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Teacher where personID='" + personID + "'").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}
		String type = "";
		if (list.isEmpty()) {
			System.out.println("StudentLogin");
			type = "student";
		} else {
			System.out.println("TeacherLogin");
			System.out.println(list.toString());
			type = "teacher";
		}
		return type;

	}
	
	public List<Section> searchSection(long personID) {
		List<Section> list = new ArrayList<Section>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Enrollment where personID='" + personID + "'").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}
		System.out.println(list.toString());
		
		return null;

	}
}
