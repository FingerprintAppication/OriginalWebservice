package com.itsci.fingerprint.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "building")
public class Building implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long buildingID;
	private String buildingName;

	public Building() {
		super();
	}

	public Building(String buildingName) {
		super();
		this.buildingName = buildingName;
	}

	@Id
	@GeneratedValue
	public long getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(long buildingID) {
		this.buildingID = buildingID;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
}

