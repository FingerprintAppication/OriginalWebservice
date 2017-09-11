package com.itsci.fingerprint.manager;

import java.util.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.itsci.fingerprint.model.Enrollment;
import com.itsci.fingerprint.model.Login;
import com.itsci.fingerprint.model.Section;
import com.itsci.fingerprint.model.Subject;
import com.itsci.fingerprint.model.Teacher;

import demo.HibernateConnection;

public class LoginManager {

	public List<Login> searchLogin(String username, String password) {
		List<Login> list = new ArrayList<Login>();
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Login where username='" + username + "' and password='" + password + "'")
					.list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();
		}

		return list;
	}

	public String saveLogin(Login login) {
		String result = "";
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.saveOrUpdate(login);
			session.getTransaction().commit();
			session.close();
			result = "update successfully";

		} catch (Exception s) {
			s.getStackTrace();
			result = "can not update " + s.getMessage();
		}

		return result;
	}

	public String checkPersonTeacher(long personID) {
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
		if (!list.isEmpty()) {
			type = "teacher";
		} else {
			type = "student";
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
			list = session.createQuery("From Section s join s.teacherList t where t.teacherID='" + teacherID + "'")
					.list();
			session.close();

		} catch (Exception e) {
			e.getStackTrace();

		}

		System.out.println("OBJECT " + list.toString());

		List<Subject> listSubject = new ArrayList<>();

		for (Object[] o : list) {
			Object object = o[0];
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

		System.out.println("Teacher ID :: " + list.get(0).getTeacherID());

		return list.get(0).getTeacherID();
	}

}
