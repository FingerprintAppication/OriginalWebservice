package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Attendance;
import com.itsci.fingerprint.model.InformLeave;
import com.itsci.fingerprint.model.Section;

import demo.HibernateConnection;

public class AttendanceManager {
	String result = "";
	
	public Attendance searchAttendanceByScheduleId(Long schedule, Long personID) {
		List<Attendance> list = new ArrayList<Attendance>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Attendance at where schedule="+schedule+" and at.enrollment.student.personID="+personID).list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}
		
		return list.get(0);

	}
	
	public String updateAttendance(Attendance atten) {
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(atten);
			session.getTransaction().commit();
			session.close();
			result = "success";
		} catch (Exception s) {
			s.getStackTrace();
			System.out.println(s.getMessage());
			result = "not success"+s.getMessage();

		}
		return result;
	}
	
	
	public Section searchSectionByPeriod(String sectionID) {
		List<Object[]> list = new ArrayList<Object[]>();
		Section section = new Section();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Section as sc join sc.periodList l where sc.sectionID ='" + sectionID + "'")
					.list();
			if (list != null && list.size() > 0) {
				Object object = list.get(0)[0];
				section = (Section) object;
			} else {
				System.out.println("error Section");
			}
			session.getTransaction().commit();
			session.close();
			System.out.println("GET IN COMPLETED Section");

		} catch (Exception s) {
			s.getStackTrace();

		}
		return section;
	}
	
	
	public List<Attendance> searchAttendanceByPeriod(Long periodID, Long personID) {
		List<Attendance> list = new ArrayList<Attendance>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Attendance at where at.schedule.period.periodID="+periodID+" and at.enrollment.student.personID="+personID).list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}
		System.out.println("searchAttendanceByPeriod = "+list.size());
		return list;

	}
}
