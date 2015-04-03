package com.tesco.retail.domain.entities;

import javax.persistence.*;

@Table(name = "ForumAbuseStatus")
@Entity

public class ForumAbuseStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	@JoinColumn(name = "topicID")      
	ForumTopic topic;
	@OneToOne
	@JoinColumn(name = "customerID")
	ForumCustomer customer;

	public ForumTopic getTopic() {
		return topic;
	}

	public void setTopic(ForumTopic topic) {
		this.topic = topic;
	}

	public ForumCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ForumCustomer customer) {
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ForumAbuseStatus(int id, ForumTopic topic, ForumCustomer customer) {
		super();
		this.id = id;
		this.topic = topic;
		this.customer = customer;
	}

	public ForumAbuseStatus() {
		super();
	}

}
