package com.itsci.fingerprint.model;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "updatedattendance")
public class UpdatedAttendance implements Serializable {

	private static final long serialVersionUID = 1L;
	private long updatedAttendanceID;
	private String updatedAttendanceDate;

	@Id
	@GeneratedValue
	public long getUpdatedAttendanceID() {
		return updatedAttendanceID;
	}

	public void setUpdatedAttendanceID(long updatedAttendanceID) {
		this.updatedAttendanceID = updatedAttendanceID;
	}

	public String getUpdatedAttendanceDate() {
		return updatedAttendanceDate;
	}

	public void setUpdatedAttendanceDate(String updatedAttendanceDate) {
		this.updatedAttendanceDate = updatedAttendanceDate;
	}

}

