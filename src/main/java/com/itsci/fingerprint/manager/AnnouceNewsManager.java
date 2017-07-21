package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Schedule;

import demo.HibernateConnection;

public class AnnouceNewsManager {

	public List<Schedule> searchScheduleDate(long periodID) {
		List<Schedule> list = new ArrayList<Schedule>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Schedule where period='" + periodID + "'").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}

		return list;

	}
}
