package com.itsci.fingerprint.manager;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Login;
import com.itsci.fingerprint.model.Person;

import demo.HibernateConnection;

public class LoginManager {

	public List<Login> searchLogin(String username,String password) {
		List<Login> list = new ArrayList<Login>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Login where username='" + username + "' and password='" + password + "'").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}
		
		return list;

	}

}
