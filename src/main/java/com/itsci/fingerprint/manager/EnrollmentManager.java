package com.itsci.fingerprint.manager;

import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.itsci.fingerprint.model.Attendance;
import com.itsci.fingerprint.model.Enrollment;
import com.itsci.fingerprint.model.Period;
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
		System.out.println("getHibernateEnrollment "+enrollment.getAttendanceList().size());
		return enrollment;
	}
	
	public List<Enrollment> getAllEnrollment (String section,String periodID) {
		List<Enrollment> list = null;
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryString = "from Enrollment as en where en.section.sectionID ="+section;
			Query query = session.createQuery(queryString);
			list = query.list();
			session.getTransaction().commit();
			for(int u=0;u<list.size();u++){
				
			Collection<Attendance> afterFilterAttendance = filterCollectionLong(
					list.get(u).getAttendanceList(),
					session,
					"this.schedule.period.periodID = :periodID order by this.attendanceID asc",
					"periodID", periodID);
			list.get(u).getAttendanceList().clear();
			list.get(u).getAttendanceList().addAll(afterFilterAttendance);
			
			}
			session.close();
		} catch (Exception s) {
			s.getStackTrace();

		}
		return list;
	}
	
	public List<Enrollment> getAllEnrollmentByPersonID (String personID) {
		List<Enrollment> list = null;
		try {
			SessionFactory sessionFactory = HibernateConnection.doHibernateConnection();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			list = session.createQuery("From Enrollment en where en.student.personID="+personID).list();
			/*for(int y=0;y<list.size();y++){
				Collection<Period> afterFilterAttendance = filterCollectionLong(
						list.get(y).getSection().getPeriodList(),
						session,"this.section.sectionID=:sectionID",
						"sectionID", list.get(y).getSection().getSectionID()+"");
				list.get(y).getSection().getPeriodList().clear();
				list.get(y).getSection().getPeriodList().addAll(afterFilterAttendance);

			}
			*/
			session.getTransaction().commit();
			session.close();
			System.out.println("GET IN COMPLETED Section");

		} catch (Exception s) {
			s.getStackTrace();

		}
		return list;
	}
	
	
	
	private static <T> Collection<T> filterCollectionLong(
			Collection<T> collection, Session s, String filterString,
			String filterParameterRef, String filterPatameter) {
		Query filterQuery = s.createFilter(collection, "where " + filterString);
		filterQuery.setParameter(filterParameterRef,
				Long.parseLong(filterPatameter));
		return filterQuery.list();
	}
	

	public void removeDataNotUse (Enrollment enroll) {
		for(int u=0;u<enroll.getAttendanceList().size();u++){
			enroll.getAttendanceList().get(u).setEnrollment(null);
			enroll.getAttendanceList().get(u).setSchedule(null);
		}
	}

}
