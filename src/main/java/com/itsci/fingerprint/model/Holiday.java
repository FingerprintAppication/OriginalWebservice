package com.itsci.fingerprint.model;



import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "holiday")
public class Holiday implements Serializable {

	private static final long serialVersionUID = 1L;
	private long holidayID;
	private String holidayName;
	private String holidayDetail;
	private String holidayStartDate;
	private String holidayEndDate;

	public Holiday() {
		super();
	}

	public Holiday(String holidayName, String holidayDetail,
			String holidayStartDate, String holidayEndDate) {
		super();
		this.holidayName = holidayName;
		this.holidayDetail = holidayDetail;
		this.holidayStartDate = holidayStartDate;
		this.setHolidayEndDate(holidayEndDate);
	}

	@Id
	@GeneratedValue
	public long getHolidayID() {
		return holidayID;
	}

	public void setHolidayID(long holidayID) {
		this.holidayID = holidayID;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public String getHolidayDetail() {
		return holidayDetail;
	}

	public void setHolidayDetail(String holidayDetail) {
		this.holidayDetail = holidayDetail;
	}

	public String getHolidayStartDate() {
		return holidayStartDate;
	}

	public void setHolidayStartDate(String holidayStartDate) {
		this.holidayStartDate = holidayStartDate;
	}

	public String getHolidayEndDate() {
		return holidayEndDate;
	}

	public void setHolidayEndDate(String holidayEndDate) {
		this.holidayEndDate = holidayEndDate;
	}

}

