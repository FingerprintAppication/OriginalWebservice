package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Person;

import demo.HibernateConnection;

public class PersonManager {
	
	public List<Person> getHibernatePerson(String id) {
		List<Person> list = new ArrayList<Person>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Person where personID="+id).list();
			session.close();
			System.out.println("GET IN COMPLETED");

		} catch (Exception s) {
			s.getStackTrace();

		}
		return list;
	}
	
	public Long getLastPerson() {
		List<Long> Long = new ArrayList<Long>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Long = session.createQuery("Select MAX(personID) From Person").list();
			session.close();
			System.out.println("GET IN COMPLETED");

		} catch (Exception s) {
			s.getStackTrace();

		}
		return Long.get(0);
	}


}
