package com.itsci.fingerprint.model;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "informLeave")
public class InformLeave {

	private static final long serialVersionUID = 1L;
	private long informLeaveID;
	private String informType;
	private String supportDocument;
	private String status;
	private String caseDetail;

	private Schedule schedule;
	private Student student;

	public InformLeave() {
	}

	public InformLeave(int informLeaveID, String informType, String supportDocument, String status, String caseDetail) {
		this.informLeaveID = informLeaveID;
		this.informType = informType;
		this.supportDocument = supportDocument;
		this.status = status;
		this.caseDetail = caseDetail;
	}
	@Id
	@GeneratedValue
	public long getInformLeaveID() {
		return informLeaveID;
	}

	public void setInformLeaveID(long informLeaveID) {
		this.informLeaveID = informLeaveID;
	}

	public String getInformType() {
		return informType;
	}

	public void setInformType(String informType) {
		this.informType = informType;
	}

	public String getSupportDocument() {
		return supportDocument;
	}

	public void setSupportDocument(String supportDocument) {
		this.supportDocument = supportDocument;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCaseDetail() {
		return caseDetail;
	}

	public void setCaseDetail(String caseDetail) {
		this.caseDetail = caseDetail;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
