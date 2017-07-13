package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.itsci.fingerprint.model.Parent;
import com.itsci.fingerprint.model.Person;
import com.itsci.fingerprint.model.Student;

import demo.HibernateConnection;

public class ParentManager {
	String result;
	Parent parents;

	public String insertParent(Parent parent) {
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(parent);
			session.getTransaction().commit();
			session.close();
			result = "insert success";
		} catch (Exception s) {
			s.getStackTrace();
			result = "can not insert";

		}
		return result;
	}
	
}
