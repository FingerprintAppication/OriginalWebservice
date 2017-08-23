package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Attendance;
import com.itsci.fingerprint.model.InformLeave;

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
}
