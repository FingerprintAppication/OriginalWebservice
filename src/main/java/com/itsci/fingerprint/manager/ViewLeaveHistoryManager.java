package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.InformLeave;
import com.itsci.fingerprint.model.Schedule;
import com.itsci.fingerprint.model.Section;

import demo.HibernateConnection;

public class ViewLeaveHistoryManager {

	public List<InformLeave> searchInformLeave(long personID) {
		List<InformLeave> list = new ArrayList<InformLeave>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From InformLeave where student='" + personID + "'").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}

		if (list.size() == 0) {
			System.out.println("not found");
		} else {
			System.out.println("found" + list.size());
		}

		return list;

	}

	public Section searchSection(long periodID) {
		List<Object[]> list = new ArrayList<Object[]>();
		Section section = new Section();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Section sc join sc.periodList l where l.periodID ='" + periodID + "'")
					.list();

			if (list != null && list.size() > 0) {
				Object object = list.get(0)[0];
				section = (Section) object;
				System.out.println("GET IN COMPLETED Section");
			} else {
				System.out.println("error Section");
			}
			session.getTransaction().commit();
			session.close();

		} catch (Exception s) {
			s.getStackTrace();

		}
		return section;
	}

}
