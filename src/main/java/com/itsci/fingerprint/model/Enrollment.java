package com.itsci.fingerprint.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name = "enrollment")
public class Enrollment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long enrollmentID;
	private Student student;
	private Section section;
	private List<Attendance> attendanceList;
	private AttendanceScore attendanceScore;
	private String status;

	public Enrollment() {
		super();
	}

	public Enrollment(Long enrollmentID) {
		super();
		this.enrollmentID = enrollmentID;
	}

	public Enrollment(Long enrollmentID, Student student, Section section) {
		super();
		this.enrollmentID = enrollmentID;
		this.student = student;
		this.section = section;
	}

	public Enrollment(Long enrollmentID, Student student, String status) {
		super();
		this.enrollmentID = enrollmentID;
		this.student = student;
		this.status = status;
	}

	public Enrollment(Student student, Section section) {
		super();
		this.student = student;
		this.section = section;
	}

	public Enrollment(List<Attendance> attendanceList) {
		super();
		this.attendanceList = attendanceList;
	}

	@Id
	@GeneratedValue
	public long getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(long enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	@OneToOne(cascade = { CascadeType.ALL })
	// @OrderBy("studentID")
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@OneToOne(cascade = { CascadeType.ALL })
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@OrderBy("attendanceID")
	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}

	@Transient
	public AttendanceScore getAttendanceScore() {
		return attendanceScore;
	}

	public void setAttendanceScore(AttendanceScore attendanceScore) {
		this.attendanceScore = attendanceScore;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

