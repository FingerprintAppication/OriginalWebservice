package com.itsci.fingerprint.model;

import java.io.Serializable;

<<<<<<< HEAD
import javax.persistence.*;
=======
import org.hibernate.FetchMode;
<<<<<<< HEAD
import org.hibernate.annotations.Cascade;
=======


>>>>>>> 0dc3ca579a8ca9eaa734e2bd0426fc6a234b476d
>>>>>>> 3b920a0e536f3a5dd89527803d4086377402a830

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
<<<<<<< HEAD

=======
	
>>>>>>> 0dc3ca579a8ca9eaa734e2bd0426fc6a234b476d
>>>>>>> 3b920a0e536f3a5dd89527803d4086377402a830
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

	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

=======
<<<<<<< HEAD
	@Override
	public String toString() {
		return "InformLeave [informLeaveID=" + informLeaveID + ", informType=" + informType + ", supportDocument="
				+ supportDocument + ", status=" + status + ", caseDetail=" + caseDetail + ", detail=" + detail
				+ ", schedule=" + schedule + ", student=" + student + "]";
	}

=======
	
>>>>>>> 0dc3ca579a8ca9eaa734e2bd0426fc6a234b476d
>>>>>>> 3b920a0e536f3a5dd89527803d4086377402a830
}
