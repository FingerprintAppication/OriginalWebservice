package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Enrollment;
import com.itsci.fingerprint.model.Period;
import com.itsci.fingerprint.model.Schedule;
import com.itsci.fingerprint.model.Section;

import demo.HibernateConnection;

public class SectionManager {
	public Section searchSectionByPeriod(Long period) {
		List<Object[]> list = new ArrayList<Object[]>();
		Section section = new Section();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Section sc join sc.periodList l where l.periodID ='" + period + "'")
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

}
