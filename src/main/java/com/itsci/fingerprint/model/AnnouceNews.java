package com.itsci.fingerprint.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "annoucenews")
public class AnnouceNews {
	private static final long serialVersionUID = 1L;
	private long annouceNewsID;
	private String annouceNewsType;
	private String detail;

	private Teacher teacher;
	private Schedule schedule;

	public AnnouceNews() {
	}

	public AnnouceNews(int annouceNewsID, String annouceNewsType, String detail) {
		this.annouceNewsID = annouceNewsID;
		this.annouceNewsType = annouceNewsType;
		this.detail = detail;
	}

	public long getAnnouceNewsID() {
		return annouceNewsID;
	}

	public void setAnnouceNewsID(long annouceNewsID) {
		this.annouceNewsID = annouceNewsID;
	}

	public String getAnnouceNewsType() {
		return annouceNewsType;
	}

	public void setAnnouceNewsType(String annouceNewsType) {
		this.annouceNewsType = annouceNewsType;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
