package com.itsci.fingerprint.model;

import javax.persistence.CascadeType;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Table(name = "topic")
public class Topic {
	private int topicId;
	private Subject subject;
	private String topicName;

	public Topic() {
		super();
	}

	public Topic(int topicId, Subject subject, String topicName) {
		super();
		this.topicId = topicId;
		this.subject = subject;
		this.topicName = topicName;
	}

	@Id
	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

}
