package com.itsci.fingerprint.model;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject implements Serializable {

	private static final long serialVersionUID = 1L;
	private long subjectID;
	private String subjectNumber;
	private String subjectName;
	private int credit;
	private Major major;

	public Subject() {
		super();
	}

	public Subject(long subjectID, String subjectName, int credit) {
		super();
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.credit = credit;
	}

	public Subject(String subjectNumber, String subjectName, int credit) {
		super();
		this.subjectNumber = subjectNumber;
		this.subjectName = subjectName;
		this.credit = credit;
	}

	@Id
	@GeneratedValue
	public long getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(long subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectNumber() {
		return subjectNumber;
	}

	public void setSubjectNumber(String subjectNumber) {
		this.subjectNumber = subjectNumber;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	@OneToOne()
	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}
}
