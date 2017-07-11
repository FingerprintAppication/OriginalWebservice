package com.itsci.fingerprint.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "parent")
public class Parent extends Person {

	private static final long serialVersionUID = 1L;
	private String phoneNo;
	private String email;
	
	public Parent() {
		super();
	}

	public Parent(String phoneNo, String email,String title,String firstName,String lastName,String fingerprintID) {
		super(title, firstName, lastName, fingerprintID);
		this.phoneNo = phoneNo;
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
