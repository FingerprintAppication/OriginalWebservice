package com.itsci.fingerprint.model;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "informleave")
public class InformLeave {
	private static final long serialVersionUID = 1L;
	private long informLeaveID;
	private String informType;
	private String supportDocument;
	private String status;
	private String caseDetail;
	private String detail;
	private Schedule schedule;
	private Student student;

	public InformLeave() {
		super();
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

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="student_personID")
	public Student getStudent() {
		return student;
	}
	
	public void setStudent(Student student) {
		this.student = student;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "InformLeave [informLeaveID=" + informLeaveID + ", informType=" + informType + ", supportDocument="
				+ supportDocument + ", status=" + status + ", caseDetail=" + caseDetail + ", detail=" + detail
				+ ", schedule=" + schedule + ", student=" + student + "]";
	}

}
