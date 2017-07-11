package demo;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.itsci.fingerprint.model.*;
//ควยไก่ๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆๆ
public class HibernateConnection {
	public static SessionFactory sessionFactory;

	public static SessionFactory doHibernateConnection() {
		Properties database = new Properties();
		database.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		database.setProperty("hibernate.connection.username", "root");
		database.setProperty("hibernate.connection.password", "complete12demon");
		database.setProperty("hibernate.connection.url","jdbc:mysql://localhost:3307/fingerprint2560?characterEncoding=UTF-8");
		database.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		Configuration cfg = new Configuration().setProperties(database).addPackage("com.itsci.fingerprint.model")
				.addAnnotatedClass(Attendance.class)
				.addAnnotatedClass(AttendanceScore.class)
				.addAnnotatedClass(Building.class)
				.addAnnotatedClass(Enrollment.class)
				.addAnnotatedClass(Faculty.class)
				.addAnnotatedClass(FingerprintData.class)
				.addAnnotatedClass(FingerprintScanner.class)
				.addAnnotatedClass(Holiday.class)
				.addAnnotatedClass(Login.class)
				.addAnnotatedClass(Major.class)
				.addAnnotatedClass(Parent.class)
				.addAnnotatedClass(Past.class)
				.addAnnotatedClass(Period.class)
				.addAnnotatedClass(Person.class)
				.addAnnotatedClass(Postpone.class)
				.addAnnotatedClass(Room.class)
				.addAnnotatedClass(RootAdmin.class)
				.addAnnotatedClass(Schedule.class)
				.addAnnotatedClass(Section.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Subject.class)
				.addAnnotatedClass(Teacher.class)
				.addAnnotatedClass(UpdatedAttendance.class);

		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
		sessionFactory = cfg.buildSessionFactory(ssrb.build());
		return sessionFactory;
	}

}
