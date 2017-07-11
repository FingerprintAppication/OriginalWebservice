package com.itsci.fingerprint.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fingerprintdata")
public class FingerprintData implements Serializable {

	private static final long serialVersionUID = 1L;
	private long fingerprintDataID;
	private String fingerprintData;
	private int fingerprintNumber;
	private int fingerprintFlag;
	private int fingerprintPassword;
	private int fingerprintPrivilege;
	private int fingerprintBackupNumber;
	private String fingerprintUsername;

	public FingerprintData() {
		super();
	}

	@Id
	@GeneratedValue
	public long getFingerprintDataID() {
		return fingerprintDataID;
	}

	public void setFingerprintDataID(long fingerprintDataID) {
		this.fingerprintDataID = fingerprintDataID;
	}

	public String getFingerprintData() {
		return fingerprintData;
	}

	public void setFingerprintData(String fingerprintData) {
		this.fingerprintData = fingerprintData;
	}

	public int getFingerprintNumber() {
		return fingerprintNumber;
	}

	public void setFingerprintNumber(int fingerprintNumber) {
		this.fingerprintNumber = fingerprintNumber;
	}

	public int getFingerprintFlag() {
		return fingerprintFlag;
	}

	public void setFingerprintFlag(int fingerprintFlag) {
		this.fingerprintFlag = fingerprintFlag;
	}

	public int getFingerprintPassword() {
		return fingerprintPassword;
	}

	public void setFingerprintPassword(int fingerprintPassword) {
		this.fingerprintPassword = fingerprintPassword;
	}

	public String getFingerprintUsername() {
		return fingerprintUsername;
	}

	public void setFingerprintUsername(String fingerprintUsername) {
		this.fingerprintUsername = fingerprintUsername;
	}

	public int getFingerprintPrivilege() {
		return fingerprintPrivilege;
	}

	public void setFingerprintPrivilege(int fingerprintPrivilege) {
		this.fingerprintPrivilege = fingerprintPrivilege;
	}

	public int getFingerprintBackupNumber() {
		return fingerprintBackupNumber;
	}

	public void setFingerprintBackupNumber(int fingerprintBackupNumber) {
		this.fingerprintBackupNumber = fingerprintBackupNumber;
	}

}
