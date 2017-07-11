package com.itsci.fingerprint.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "fingerprintscanner")
public class FingerprintScanner implements Serializable {

	private static final long serialVersionUID = 1L;
	private long fingerprintScannerID;
	private int fingerprintScannerNumber;
	private String fingerprintScannerOEM;
	private String fingerprintScannerName;
	private String fingerprintScannerIPAddress;
	private int fingerprintScannerPort;
	private int fingerprintScannerBoardrate;
	private int fingerprintScannerPassword;
	private String fingerprintScannerDescription;
	private List<Student> studentList;

	public FingerprintScanner() {
		super();
	}

	public FingerprintScanner(int fingerprintScannerNumber,
			String fingerprintScannerIPAddress) {
		super();
		this.fingerprintScannerNumber = fingerprintScannerNumber;
		this.fingerprintScannerIPAddress = fingerprintScannerIPAddress;
	}

	@Id
	@GeneratedValue
	public long getFingerprintScannerID() {
		return fingerprintScannerID;
	}

	public void setFingerprintScannerID(long fingerprintScannerID) {
		this.fingerprintScannerID = fingerprintScannerID;
	}

	public int getFingerprintScannerNumber() {
		return fingerprintScannerNumber;
	}

	public void setFingerprintScannerNumber(int fingerprintScannerNumber) {
		this.fingerprintScannerNumber = fingerprintScannerNumber;
	}

	public String getFingerprintScannerOEM() {
		return fingerprintScannerOEM;
	}

	public void setFingerprintScannerOEM(String fingerprintScannerOEM) {
		this.fingerprintScannerOEM = fingerprintScannerOEM;
	}

	public String getFingerprintScannerName() {
		return fingerprintScannerName;
	}

	public void setFingerprintScannerName(String fingerprintScannerName) {
		this.fingerprintScannerName = fingerprintScannerName;
	}

	public String getFingerprintScannerIPAddress() {
		return fingerprintScannerIPAddress;
	}

	public void setFingerprintScannerIPAddress(
			String fingerprintScannerIPAddress) {
		this.fingerprintScannerIPAddress = fingerprintScannerIPAddress;
	}

	public int getFingerprintScannerPort() {
		return fingerprintScannerPort;
	}

	public void setFingerprintScannerPort(int fingerprintScannerPort) {
		this.fingerprintScannerPort = fingerprintScannerPort;
	}

	public int getFingerprintScannerBoardrate() {
		return fingerprintScannerBoardrate;
	}

	public void setFingerprintScannerBoardrate(int fingerprintScannerBoardrate) {
		this.fingerprintScannerBoardrate = fingerprintScannerBoardrate;
	}

	public int getFingerprintScannerPassword() {
		return fingerprintScannerPassword;
	}

	public void setFingerprintScannerPassword(int fingerprintScannerPassword) {
		this.fingerprintScannerPassword = fingerprintScannerPassword;
	}

	public String getFingerprintScannerDescription() {
		return fingerprintScannerDescription;
	}

	public void setFingerprintScannerDescription(
			String fingerprintScannerDescription) {
		this.fingerprintScannerDescription = fingerprintScannerDescription;
	}

	@Transient
	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
}
