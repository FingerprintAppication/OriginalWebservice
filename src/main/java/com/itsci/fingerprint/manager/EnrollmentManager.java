package com.itsci.fingerprint.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.itsci.fingerprint.model.Attendance;
import com.itsci.fingerprint.model.Enrollment;
import com.itsci.fingerprint.model.Period;
import com.itsci.fingerprint.model.Person;
import com.itsci.fingerprint.model.Section;

import demo.HibernateConnection;

public class EnrollmentManager {
	Enrollment enrollment = null;
	
	public Enrollment getHibernateEnrollment(String studentID,String subjectNumber,String period) {
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryString = "from Enrollment as en  where en.student.personID = :studentID and en.section.sectionNumber = :sectionNumber and en.section.subject.subjectNumber = :subjectNumber";
			Query query = session.createQuery(queryString);
			query.setParameter("studentID", Long.parseLong(studentID));
			query.setParameter("sectionNumber", Integer.parseInt("1"));
			query.setParameter("subjectNumber", subjectNumber);
			List<?> listResult = query.list();
			if (listResult != null && listResult.size() > 0) {
				enrollment = (Enrollment) listResult.get(0);
			}
			session.getTransaction().commit();
			Collection<Period> afterFilterPeriod = filterCollectionLong(
					enrollment.getSection().getPeriodList(), session,
					"this.periodID = :periodID order by this.periodID asc",
					"periodID", period);
			
			
			enrollment.getSection().getPeriodList().clear();
			enrollment.getSection().getPeriodList().addAll(afterFilterPeriod);

			Collection<Attendance> afterFilterAttendance = filterCollectionLong(
					enrollment.getAttendanceList(),
					session,
					"this.schedule.period.periodID = :periodID order by this.attendanceID asc",
					"periodID", period);
			enrollment.getAttendanceList().clear();
			enrollment.getAttendanceList().addAll(afterFilterAttendance);
			session.close();

		} catch (Exception s) {
			s.getStackTrace();

		}
		return enrollment;
	}
	
	
	public ArrayList<Section> findSubjectBySubjectNumberAndSubjectName(
			String login, String subjectNumber, String subjectName) {
		ArrayList<Section> sectionList = new ArrayList<Section>();
		ArrayList<Enrollment> enrollmentList = new ArrayList<Enrollment>();
		SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
		Session session = sessionFactory.openSession();
		Transaction ta = session.getTransaction();
		try {
			String queryString = "select new Enrollment( en.student, en.section) from Enrollment en where en.section.subject.subjectNumber = :subjectNumber and en.section.subject.subjectName = :subjectName and en.student.personID = :personID  order by en.section.subject.subjectID, en.section.sectionNumber asc";
			Query query = session.createQuery(queryString);
			query.setParameter("subjectNumber", subjectNumber);
			query.setParameter("subjectName", subjectName);
			query.setParameter("personID", Long.parseLong(login));
			ta = session.beginTransaction();
			List<?> listResult = query.list();
			for (Object obj : listResult) {
				enrollmentList.add((Enrollment) obj);
			}
			ta.commit();

			for (Enrollment e : enrollmentList) {
				sectionList.add(e.getSection());
			}
		} catch (HibernateException ex) {
			if (ta != null) {
				ta.rollback();
			}
		} finally {
			session.close();
		}
		return sectionList;
	}
	
	private static <T> Collection<T> filterCollectionLong(
			Collection<T> collection, Session s, String filterString,
			String filterParameterRef, String filterPatameter) {
		Query filterQuery = s.createFilter(collection, "where " + filterString);
		filterQuery.setParameter(filterParameterRef,
				Long.parseLong(filterPatameter));
		return filterQuery.list();
	}

}
