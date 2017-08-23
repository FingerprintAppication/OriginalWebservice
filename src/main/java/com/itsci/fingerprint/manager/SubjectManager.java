package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.AnnouceNews;
import com.itsci.fingerprint.model.Subject;

import demo.HibernateConnection;

public class SubjectManager {
	public List<Subject> getAllSubject() {
		List<Subject> list = new ArrayList<Subject>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Subject").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}

		return list;

	}
}
