package com.itsci.fingerprint.model;

import java.io.Serializable;

<<<<<<< HEAD
import javax.persistence.*;
=======
import org.hibernate.FetchMode;


>>>>>>> 0dc3ca579a8ca9eaa734e2bd0426fc6a234b476d

@Entity
@Table(name = "informleave")
public class InformLeave {

	private long informLeaveID;
	private String informType;
	private String supportDocument;
	private String status;
	private String caseDetail;
	private String detail;
<<<<<<< HEAD
=======

>>>>>>> 0dc3ca579a8ca9eaa734e2bd0426fc6a234b476d
	private Schedule schedule;
	private Student student;

	public InformLeave() {
		super();
	}

<<<<<<< HEAD
=======
	public InformLeave(int informLeaveID, String informType, String supportDocument, String status, String caseDetail) {
		this.informLeaveID = informLeaveID;
		this.informType = informType;
		this.supportDocument = supportDocument;
		this.status = status;
		this.caseDetail = caseDetail;
	}
	
>>>>>>> 0dc3ca579a8ca9eaa734e2bd0426fc6a234b476d
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

	@OneToOne(/*cascade = CascadeType.ALL,fetch = FetchType.EAGER*/)
	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="personID")
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	

	public String getDetail() {
		return detail;
	}

<<<<<<< HEAD
	public String getDetail() {
		return detail;
	}

=======
>>>>>>> 0dc3ca579a8ca9eaa734e2bd0426fc6a234b476d
	public void setDetail(String detail) {
		this.detail = detail;
	}

<<<<<<< HEAD
	@Override
	public String toString() {
		return "InformLeave [informLeaveID=" + informLeaveID + ", informType=" + informType + ", supportDocument="
				+ supportDocument + ", status=" + status + ", caseDetail=" + caseDetail + ", detail=" + detail
				+ ", schedule=" + schedule + ", student=" + student + "]";
	}

=======
	
>>>>>>> 0dc3ca579a8ca9eaa734e2bd0426fc6a234b476d
}
