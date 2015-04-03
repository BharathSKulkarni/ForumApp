package com.tesco.retail.domain.entities;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Table(name="ForumTopic")
@Entity
@NamedQuery(name="ForumCategory.findAllTopics", query="SELECT fc from ForumTopic fc WHERE categoryID=:categoryID")
public class ForumTopic {
	
	@Id
	private int topicID;
	@Column(nullable=false)
	private String topic;
	@Column(nullable=false)
	private String description;
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name = "categoryID")
	private ForumCategory category;
	private String dateOfCreation;
	@OneToOne
	@JoinColumn(name="customerID")
	private ForumCustomer customer;
	private boolean approved = false;
	
	public int getTopicID() {
		return topicID;
	}

	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public ForumCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ForumCustomer customer) {
		this.customer = customer;
	}

	public ForumCategory getCategory() {
		return category;
	}

	public void setCategory(ForumCategory category) {
		this.category = category;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	 

	public ForumTopic(int topicID, String topic, String description,
			ForumCategory category, String dateOfCreation,
			ForumCustomer customer, boolean approved) {
		super();
		this.topicID = topicID;
		this.topic = topic;
		this.description = description;
		this.category = category;
		this.dateOfCreation = dateOfCreation;
		this.customer = customer;
		this.approved = approved;
	}

	public ForumTopic() {
		super();
	}

	@Override
	public String toString() {
		return "ForumTopic [topicID=" + topicID + ", topic=" + topic
				+ ", category=" + category + ", dateOfCreation="
				+ dateOfCreation + ", customer=" + customer + ", approved="
				+ approved + "]";
	}
	
}
