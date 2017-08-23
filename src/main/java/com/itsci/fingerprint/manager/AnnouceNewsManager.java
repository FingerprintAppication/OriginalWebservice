package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.AnnouceNews;
import com.itsci.fingerprint.model.Attendance;
import com.itsci.fingerprint.model.Parent;
import com.itsci.fingerprint.model.Schedule;
import com.itsci.fingerprint.model.Section;
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

	public List<Attendance> SearchAttendanceWithScheduleID(long scheduleID) {
		List<Attendance> list = new ArrayList<Attendance>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Attendance where schedule='" + scheduleID + "'").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}

		return list;
	}
	
	public String updateAttendance(Attendance a) {
		String result;
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(a);
			session.getTransaction().commit();
			session.close();
			result = "update success";
		} catch (Exception s) {
			s.getStackTrace();
			result = "can not update";
			System.out.println(s.getMessage());

		}
		return result;
	}
	
	public String deleteAnnouceNew(AnnouceNews a) {
		String result;
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.delete(a);
			session.getTransaction().commit();
			session.close();
			result = "delete success";
		} catch (Exception s) {
			s.getStackTrace();
			result = "can not delete";
			System.out.println(s.getMessage());

		}
		return result;
	}
	
	/*new added*/
	public List<AnnouceNews> getAnnounceNews() {
		List<AnnouceNews> list = new ArrayList<AnnouceNews>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From AnnouceNews").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}

		return list;

	}
	
	public Section searchSectionByPeriod(Long period) {
		List<Object[]> list = new ArrayList<Object[]>();
		Section section = new Section();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Section sc join sc.periodList l where l.periodID ="+period).list();
			if (list != null && list.size() > 0) {
				Object object = list.get(0)[0];
				section = (Section) object;
			}
			session.getTransaction().commit();
			session.close();
			System.out.println("GET IN COMPLETED");

		} catch (Exception s) {
			s.getStackTrace();

		}
		return section;
	}

}
