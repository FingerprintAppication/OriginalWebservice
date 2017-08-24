package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Subject;
import com.itsci.fingerprint.model.Topic;

import demo.HibernateConnection;

public class TopicManager {
	public List<Topic> getAllTopic() {
		List<Topic> list = new ArrayList<Topic>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("from Topic").list();
			session.close();
			System.out.println("GET IN TOPICMANAGER!");
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());

		}

		return list;

	}
}
