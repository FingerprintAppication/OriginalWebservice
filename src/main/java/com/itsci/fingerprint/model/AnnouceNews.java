package com.itsci.fingerprint.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "annoucenews")
@JsonAutoDetect
public class AnnouceNews {
	private static final long serialVersionUID = 1L;
	private long annouceNewsID;
	private String annouceNewsType;
	private String detail;
	private Date annouceDate;
	private Teacher teacher;
	private Schedule schedule;

	public AnnouceNews() {
	}

	public AnnouceNews(long annouceNewsID, String annouceNewsType, String detail) {
		this.annouceNewsID = annouceNewsID;
		this.annouceNewsType = annouceNewsType;
		this.detail = detail;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	@JsonDeserialize(using=CustomerDateAndTimeDeserialize .class)
	public Date getAnnouceDate() {
		return annouceDate;
	}

	public void setAnnouceDate(Date annouceDate) {
		this.annouceDate = annouceDate;
	}

	@Id
	@GeneratedValue
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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DATE "+annouceDate+" AnnouceNews [annouceNewsID=" + annouceNewsID + ", annouceNewsType=" + annouceNewsType + ", detail="
				+ detail + ", teacher=" + teacher + ", schedule=" + schedule + "]";
	}

}
