package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.AnnouceNews;
import com.itsci.fingerprint.model.Parent;
import com.itsci.fingerprint.model.Schedule;
import com.itsci.fingerprint.model.Teacher;

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

	public Teacher searchTeacher(long personID) {
		List<Teacher> list = new ArrayList<Teacher>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Teacher where personID='" + personID + "'").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}

		return list.get(0);

	}

	public Schedule searchSchedule(long periodID, String date) {
		List<Schedule> list = new ArrayList<Schedule>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Schedule where period='" + periodID + "' and scheduleDate='" + date + "'")
					.list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}

		return list.get(0);

	}

	public String insertAnnouceNews(AnnouceNews a) {
		String result;
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(a);
			session.getTransaction().commit();
			session.close();
			result = "insert success";
		} catch (Exception s) {
			s.getStackTrace();
			result = "can not insert";
			System.out.println(s.getMessage());

		}
		return result;
	}

}
