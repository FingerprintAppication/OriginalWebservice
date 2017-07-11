package com.itsci.fingerprint.model;



import java.io.Serializable;

public class AttendanceScore implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int come;
	private int late;
	private int absent;
	private int leave;
	private int na;
	private int cancel;
	private int holiday;
	private double score;

	public AttendanceScore() {
		super();
	}

	public int getCome() {
		return come;
	}

	public void setCome(int come) {
		this.come = come;
	}

	public int getLate() {
		return late;
	}

	public void setLate(int late) {
		this.late = late;
	}

	public int getAbsent() {
		return absent;
	}

	public void setAbsent(int absent) {
		this.absent = absent;
	}

	public int getLeave() {
		return leave;
	}

	public void setLeave(int leave) {
		this.leave = leave;
	}

	public int getNa() {
		return na;
	}

	public void setNa(int na) {
		this.na = na;
	}

	public int getCancel() {
		return cancel;
	}

	public void setCancel(int cancel) {
		this.cancel = cancel;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getHoliday() {
		return holiday;
	}

	public void setHoliday(int holiday) {
		this.holiday = holiday;
	}

}

