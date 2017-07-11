package com.itsci.fingerprint.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {

	private static final long serialVersionUID = 1L;
	private long scheduleID;
	private int scheduleNumber;
	private Date scheduleDate;
	private Period period;
	private Postpone postpone;
	private Time ddd;
	public Schedule() {
		super();
	}

	public Schedule(int scheduleNumber, Date scheduleDate, Period period,
			Postpone postpone) {
		super();
		this.scheduleNumber = scheduleNumber;
		this.scheduleDate = scheduleDate;
		this.period = period;
		this.postpone = postpone;
	}

	public Schedule(int scheduleNumber, Date scheduleDate, Period period) {
		super();
		this.scheduleNumber = scheduleNumber;
		this.scheduleDate = scheduleDate;
		this.period = period;
	}

	public Schedule(Date scheduleDate) {
		super();
		this.scheduleDate = scheduleDate;
	}

	@Id
	@GeneratedValue
	public long getScheduleID() {
		return scheduleID;
	}

	public void setScheduleID(long scheduleID) {
		this.scheduleID = scheduleID;
	}

	public int getScheduleNumber() {
		return scheduleNumber;
	}

	public void setScheduleNumber(int scheduleNumber) {
		this.scheduleNumber = scheduleNumber;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	@OneToOne(orphanRemoval = true)
	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	@OneToOne(cascade = { CascadeType.ALL })
	public Postpone getPostpone() {
		return postpone;
	}

	public void setPostpone(Postpone postpone) {
		this.postpone = postpone;
	}

}

