package com.itsci.fingerprint.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.InformLeave;
import com.itsci.fingerprint.model.Parent;

import demo.HibernateConnection;

public class InformLeaveManager {
	String result = "";
	public String insertInformLeave(InformLeave informLeave) {
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(informLeave);
			session.getTransaction().commit();
			session.close();
			result = "insert success";
		} catch (Exception s) {
			s.getStackTrace();
			System.out.println("GG "+ s.getMessage());
			result = "can not insert";

		}
		return result;
	}

}
