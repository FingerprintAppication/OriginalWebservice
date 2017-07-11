package com.itsci.fingerprint.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "student")
public class Student extends Person {
	
	private static final long serialVersionUID = 1L;
	private long studentID;
	private String encodeID;
	private String parentPhone;// NEW ADD
	private Parent parent;// NEW ADD

	public Student() {
		super();
	}

	public Student(int id, String title, String firstName, String lastName, String fingerprintID, long studentID) {
		super(title, firstName, lastName, fingerprintID);
		this.studentID = studentID;
	}
	//************************* NEW ADD**************************//
	public String getParentPhone() {
		return parentPhone;
	}

	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone;
	}
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="parent")
	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}
	//************************* NEW ADD**************************//
	@Column(unique = true)
	public long getStudentID() {
		return studentID;
	}

	public void setStudentID(long studentID) {
		this.studentID = studentID;
	}

	@Transient
	public String getEncodeID() {
		return encodeID;
	}

	public void setEncodeID(String encodeID) {
		this.encodeID = encodeID;
	}
	
	

}
