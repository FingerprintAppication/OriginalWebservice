package com.itsci.fingerprint.model;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "postpone")
public class Postpone implements Serializable {

	private static final long serialVersionUID = 1L;
	private long postponeID;
	private String oldPostponeDate;
	private String postponeDate;
	private String postponeDetail;
	private String postponeStartTime;
	private String postponeEndTime;
	private Room room;
	private Schedule schedule;

	public Postpone() {
		super();
	}

	public Postpone(String postponeDate, String postponeDetail,
			String postponeStartTime, String postponeEndTime) {
		super();
		this.postponeDate = postponeDate;
		this.postponeDetail = postponeDetail;
		this.postponeStartTime = postponeStartTime;
		this.postponeEndTime = postponeEndTime;
	}

	public Postpone(long postponeID, String postponeDate,
			String postponeDetail, String postponeStartTime,
			String postponeEndTime, Room room) {
		super();
		this.postponeID = postponeID;
		this.postponeDate = postponeDate;
		this.postponeDetail = postponeDetail;
		this.postponeStartTime = postponeStartTime;
		this.postponeEndTime = postponeEndTime;
		this.room = room;
	}

	public Postpone(long postponeID, String postponeDate,
			String postponeDetail, String postponeStartTime,
			String postponeEndTime, Room room, Schedule schedule) {
		super();
		this.postponeID = postponeID;
		this.postponeDate = postponeDate;
		this.postponeDetail = postponeDetail;
		this.postponeStartTime = postponeStartTime;
		this.postponeEndTime = postponeEndTime;
		this.room = room;
		this.schedule = schedule;
	}

	public Postpone(long postponeID, String oldPostponeDate,
			String postponeDate, String postponeDetail,
			String postponeStartTime, String postponeEndTime, Room room,
			Schedule schedule) {
		super();
		this.postponeID = postponeID;
		this.oldPostponeDate = oldPostponeDate;
		this.postponeDate = postponeDate;
		this.postponeDetail = postponeDetail;
		this.postponeStartTime = postponeStartTime;
		this.postponeEndTime = postponeEndTime;
		this.room = room;
		this.schedule = schedule;
	}

	@Id
	@GeneratedValue
	public long getPostponeID() {
		return postponeID;
	}

	public void setPostponeID(long postponeID) {
		this.postponeID = postponeID;
	}

	public String getPostponeDate() {
		return postponeDate;
	}

	public void setPostponeDate(String postponeDate) {
		this.postponeDate = postponeDate;
	}

	public String getPostponeDetail() {
		return postponeDetail;
	}

	public void setPostponeDetail(String postponeDetail) {
		this.postponeDetail = postponeDetail;
	}

	public String getPostponeStartTime() {
		return postponeStartTime;
	}

	public void setPostponeStartTime(String postponeStartTime) {
		this.postponeStartTime = postponeStartTime;
	}

	public String getPostponeEndTime() {
		return postponeEndTime;
	}

	public void setPostponeEndTime(String postponeEndTime) {
		this.postponeEndTime = postponeEndTime;
	}

	@OneToOne
	// cascade = { CascadeType.ALL }
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@OneToOne
	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public String getOldPostponeDate() {
		return oldPostponeDate;
	}

	public void setOldPostponeDate(String oldPostponeDate) {
		this.oldPostponeDate = oldPostponeDate;
	}
}

