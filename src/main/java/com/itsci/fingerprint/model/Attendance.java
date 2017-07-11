package com.itsci.fingerprint.model;

import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance implements Serializable, Comparable<Attendance> {
	
	private static final long serialVersionUID = 1L;
	private long attendanceID;
	private String status;
	private String statusDescription;
	private Enrollment enrollment;
	private Schedule schedule;

	public Attendance() {
		super();
	}

	public Attendance(String status) {
		super();
		this.status = status;
	}

	public Attendance(String status, Schedule schedule) {
		super();
		this.status = status;
		this.schedule = schedule;
	}

	public Attendance(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	@Id
	@GeneratedValue
	public long getAttendanceID() {
		return attendanceID;
	}

	public void setAttendanceID(long attendanceID) {
		this.attendanceID = attendanceID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@OneToOne(orphanRemoval = true)
	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public static Comparator<Attendance> AttendanceOrderByID = new Comparator<Attendance>() {

		@Override
		public int compare(Attendance at1, Attendance at2) {

			String attendance1 = null;
			long tmp1 = at1.getSchedule().getScheduleNumber();
			if (tmp1 >= 10) {
				attendance1 += "9" + at1.getSchedule().getScheduleNumber();
			} else {
				attendance1 += "" + at1.getSchedule().getScheduleNumber();
			}
			String attendance2 = null;
			long tmp2 = at2.getSchedule().getScheduleNumber();
			if (tmp2 >= 10) {
				attendance2 += "9" + at2.getSchedule().getScheduleNumber();
			} else {
				attendance2 += "" + at2.getSchedule().getScheduleNumber();
			}
	
			return attendance1.compareTo(attendance2);

		}

	};

	@Override
	public int compareTo(Attendance o) {
		return 0;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	@OneToOne(cascade = { CascadeType.ALL })
	public Enrollment getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}
}
