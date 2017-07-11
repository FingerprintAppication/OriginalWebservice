package com.itsci.fingerprint.model;

import javax.persistence.*;

@Entity
@Table(name="Past")
public class Past {
	@Id
	@Column(name="personid")
	private int personid;
	
	@Column(name="personname")
	private String personname;

	
	public Past() {
		
	}

	public Past(int personid, String personname) {
		
		this.personid = personid;
		this.personname = personname;
	}

	public int getPersonid() {
		return personid;
	}

	public void setPersonid(int personid) {
		this.personid = personid;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}
	
	
	
	
	

}
