package com.itsci.fingerprint.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends Person {

	private static final long serialVersionUID = 1L;
	private String teacherID;

	public Teacher() {
		super();
	}

	public Teacher(int id, String title, String firstName, String lastName,
			String fingerprintID, String teacherID) {
		super(title, firstName, lastName, fingerprintID);
		this.teacherID = teacherID;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

	// @ManyToMany(cascade = {CascadeType.MERGE }, fetch = FetchType.EAGER)
	// public List<Section> getSectionList() {
	// return sectionList;
	// }
	//
	// public void setSectionList(List<Section> sectionList) {
	// this.sectionList = sectionList;
	// }

}
