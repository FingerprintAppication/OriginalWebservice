package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.InformLeave;
import com.itsci.fingerprint.model.Login;
import com.itsci.fingerprint.model.Parent;

import demo.HibernateConnection;

public class InformLeaveManager {
	String result = "";
	public String insertInformLeave(InformLeave informLeave) {
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			informLeave.setInformLeaveID(123456);
			session.saveOrUpdate(informLeave);
			session.getTransaction().commit();
			session.close();
			result = "success";
		} catch (Exception s) {
			s.getStackTrace();
			System.out.println(s.getMessage()+"\n"+s.getStackTrace().toString());
			result = "not success";

		}
		return result;
	}
	
	public String updateInformLeave(InformLeave informLeave) {
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(informLeave);
			session.getTransaction().commit();
			session.close();
			result = "success";
		} catch (Exception s) {
			s.getStackTrace();
			System.out.println(s.getMessage());
			result = "not success";

		}
		return result;
	}
	
	public InformLeave searchInformLeaveById(Long informId) {
		List<InformLeave> list = new ArrayList<InformLeave>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From InformLeave where informLeaveID="+informId).list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}
		
		return list.get(0);

	}

}
