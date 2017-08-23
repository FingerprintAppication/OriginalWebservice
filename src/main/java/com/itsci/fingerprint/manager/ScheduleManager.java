package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Schedule;
import com.itsci.fingerprint.model.Student;

import demo.HibernateConnection;

public class ScheduleManager {

	public Schedule searchScheduleByDate(String date, Long period) {
		List<Schedule> list = new ArrayList<Schedule>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session
					.createQuery(
							"From Schedule as sc where scheduleDate='" + date + "' and sc.period.periodID=" + period)
					.list();
			session.close();

			if ( list.size() == 0 ){
				System.out.println("getSchdule Error");
			}

		} catch (Exception s) {
			s.getStackTrace();

		}

		return list.get(0);
	}

}
