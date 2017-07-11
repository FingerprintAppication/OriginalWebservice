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
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "period")
public class Period implements Serializable {

	private static final long serialVersionUID = 1L;
	private long periodID;
	private String dayOfWeek;
	private String studyType;
	private String comingTime;
	private String lateTime;
	private String absentTime;
	private String periodStartTime;
	private String periodEndTime;
	private Room room;
	private List<Schedule> scheduleList;

	public Period() {
		super();
	}

	public Period(String dayOfWeek, String studyType, String comingTime,
			String lateTime, String absentTime, String periodStartTime,
			String periodEndTime, Room room) {
		super();
		this.dayOfWeek = dayOfWeek;
		this.studyType = studyType;
		this.comingTime = comingTime;
		this.lateTime = lateTime;
		this.absentTime = absentTime;
		this.periodStartTime = periodStartTime;
		this.periodEndTime = periodEndTime;
		this.room = room;
	}

	@Id
	@GeneratedValue
	public long getPeriodID() {
		return periodID;
	}

	public void setPeriodID(long periodID) {
		this.periodID = periodID;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getStudyType() {
		return studyType;
	}

	public void setStudyType(String studyType) {
		this.studyType = studyType;
	}

	public String getComingTime() {
		return comingTime;
	}

	public void setComingTime(String comingTime) {
		this.comingTime = comingTime;
	}

	public String getLateTime() {
		return lateTime;
	}

	public void setLateTime(String lateTime) {
		this.lateTime = lateTime;
	}

	public String getAbsentTime() {
		return absentTime;
	}

	public void setAbsentTime(String absentTime) {
		this.absentTime = absentTime;
	}

	public String getPeriodStartTime() {
		return periodStartTime;
	}

	public void setPeriodStartTime(String periodStartTime) {
		this.periodStartTime = periodStartTime;
	}

	public String getPeriodEndTime() {
		return periodEndTime;
	}

	public void setPeriodEndTime(String periodEndTime) {
		this.periodEndTime = periodEndTime;
	}

	@OneToOne()
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}

}
