package com.itsci.fingerprint.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rootadmin")
public class RootAdmin extends Person {

	private static final long serialVersionUID = 1L;
	private long rootAdminID;

	public RootAdmin() {
		super();
	}

	public RootAdmin(int id, String title, String firstName, String lastName,
			String fingerprintID, long rootAdminID) {
		super(title, firstName, lastName, fingerprintID);
		this.rootAdminID = rootAdminID;
	}

	public long getRootAdminID() {
		return rootAdminID;
	}

	public void setRootAdminID(long rootAdminID) {
		this.rootAdminID = rootAdminID;
	}

}
