package com.itsci.fingerprint.manager;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Enrollment;
import com.itsci.fingerprint.model.Section;
import com.itsci.fingerprint.model.Subject;
import com.itsci.fingerprint.model.Teacher;

import demo.HibernateConnection;

public class ViewListSubjectManager {

	public String checkPerson(long personID) {
		List<Object> list = new ArrayList<>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Teacher where personID='" + personID + "'").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}
		String type = "";
		if (list.isEmpty()) {
			System.out.println("StudentLogin");
			type = "student";
		} else {
			System.out.println("TeacherLogin");
			System.out.println(list.toString());
			type = "teacher";
		}
		return type;

	}

	public List<Subject> searchStudentSubject(long personID) {
		List<Enrollment> list = new ArrayList<Enrollment>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Enrollment where student='" + personID + "'").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}

		List<Subject> listSubject = new ArrayList<>();
		for (Enrollment i : list) {
			Subject s = i.getSection().getSubject();
			listSubject.add(s);
		}

		return listSubject;

	}

	public List<Subject> searchTeacherSubject(String teacherID) {
		List<Object[]> list = new ArrayList<>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Section s join s.teacherList t where t.teacherID='" + teacherID + "'").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}

		System.out.println("OBJECT " + list.toString());

		List<Subject> listSubject = new ArrayList<>();

		for (Object[] o : list) {
			Object object = o[0];
			System.out.println(object.toString());
			Section s = (Section) object;
			listSubject.add(s.getSubject());
		}

		return listSubject;
	}

	public String searchTeacherID(long personID) {
		List<Teacher> list = new ArrayList<Teacher>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Teacher where personID='" + personID + "'").list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}

		System.out.println("TEACGER ID :::::: " + list.get(0).getTeacherID());

		return list.get(0).getTeacherID();
	}

}
