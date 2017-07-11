package com.itsci.fingerprint.model;



import java.io.Serializable;
import java.util.List;

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
@Table(name = "room")
public class Room implements Serializable {

	private static final long serialVersionUID = 1L;
	private long roomID;
	private String roomName;
	private Building building;
	private List<FingerprintScanner> fingerprintScannerList;

	public Room() {
		super();
	}

	public Room(String roomName, Building building) {
		super();
		this.roomName = roomName;
		this.building = building;
	}

	@Id
	@GeneratedValue
	public long getRoomID() {
		return roomID;
	}

	public void setRoomID(long roomID) {
		this.roomID = roomID;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	@OneToOne()
	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	public List<FingerprintScanner> getFingerprintScannerList() {
		return fingerprintScannerList;
	}

	public void setFingerprintScannerList(
			List<FingerprintScanner> fingerprintScannerList) {
		this.fingerprintScannerList = fingerprintScannerList;
	}

}

