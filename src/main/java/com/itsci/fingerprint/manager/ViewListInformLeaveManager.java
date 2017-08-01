package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.InformLeave;
import com.itsci.fingerprint.model.Student;

import demo.HibernateConnection;

public class ViewListInformLeaveManager {
	public List<InformLeave> searchListInformLeave() {
		List<InformLeave> list = new ArrayList<InformLeave>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From InformLeave").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
		}
		
		return list;

	}

}
