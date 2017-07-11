package com.itsci.fingerprint.model;



import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "section")
public class Section implements Serializable {

	private static final long serialVersionUID = 1L;
	private long sectionID;
	private int sectionNumber;
	private int semester;
	private int schoolYear;
	private List<Period> periodList;
	private Subject subject;
	private List<Teacher> teacherList;

	public Section() {
		super();
	}

	public Section(int sectionNumber, int semester, int schoolYear,
			Subject subject) {
		super();
		this.sectionNumber = sectionNumber;
		this.semester = semester;
		this.schoolYear = schoolYear;
		this.subject = subject;
	}

	public Section(long sectionID, int sectionNumber, int semester,
			int schoolYear, Subject subject) {
		super();
		this.sectionID = sectionID;
		this.sectionNumber = sectionNumber;
		this.semester = semester;
		this.schoolYear = schoolYear;
		this.subject = subject;
	}

	@Id
	@GeneratedValue
	public long getSectionID() {
		return sectionID;
	}

	public void setSectionID(long sectionID) {
		this.sectionID = sectionID;
	}

	public int getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getSchoolYear() {
		return schoolYear;
	}

	public void setSchoolYear(int schoolYear) {
		this.schoolYear = schoolYear;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	public List<Period> getPeriodList() {
		return periodList;
	}

	public void setPeriodList(List<Period> periodList) {
		this.periodList = periodList;
	}

	@OneToOne()
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "section_teacher")
	@Fetch(FetchMode.SUBSELECT)
	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

}

