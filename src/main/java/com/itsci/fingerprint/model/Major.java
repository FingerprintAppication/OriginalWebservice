package com.itsci.fingerprint.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "major")
public class Major implements Serializable {

	private static final long serialVersionUID = 1L;
	private long majorID;
	private String secondaryMajorID;
	private String majorName;
	private Faculty faculty;

	public Major() {
		super();
	}

	public Major(String majorName) {
		super();
		this.majorName = majorName;
	}

	@Id
	@GeneratedValue
	public long getMajorID() {
		return majorID;
	}

	public void setMajorID(long majorID) {
		this.majorID = majorID;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	@OneToOne(cascade = { CascadeType.ALL })
	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public String getSecondaryMajorID() {
		return secondaryMajorID;
	}

	public void setSecondaryMajorID(String secondaryMajorID) {
		this.secondaryMajorID = secondaryMajorID;
	}

}

