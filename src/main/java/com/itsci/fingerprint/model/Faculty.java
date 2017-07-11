package com.itsci.fingerprint.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "faculty")
public class Faculty implements Serializable {

	private static final long serialVersionUID = 1L;
	private long facultyID;
	private String facultyName;

	public Faculty() {
		super();
	}

	public Faculty(int id, String facultyName) {
		super();
		this.facultyName = facultyName;
	}

	@Id
	@GeneratedValue
	public long getFacultyID() {
		return facultyID;
	}

	public void setFacultyID(long facultyID) {
		this.facultyID = facultyID;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

}

