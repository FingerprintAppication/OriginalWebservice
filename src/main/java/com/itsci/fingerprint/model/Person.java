package com.itsci.fingerprint.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	private long parent;
	private String title;
	private String firstName;
	private String lastName;
	private FingerprintData fingerprintData;
	private Major major;

	public Person() {
		super();
	}

	public Person(String title, String firstName, String lastName,
			String fingerprintID) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Id
	@GeneratedValue
	public long getPersonID() {
		return parent;
	}

	public void setPersonID(long personID) {
		this.parent = personID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@OneToOne()
	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	@OneToOne()
	public FingerprintData getFingerprintData() {
		return fingerprintData;
	}

	public void setFingerprintData(FingerprintData fingerprintData) {
		this.fingerprintData = fingerprintData;
	}
}
