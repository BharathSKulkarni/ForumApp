package com.tesco.retail.domain.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "ForumComment")
@Entity
public class ForumComment {
	@Id
	private int commentID;
	private String comment;
	@OneToOne
	@JoinColumn(name = "customerID")
	private ForumCustomer customer;
	private String dateOfCreation;
	@OneToOne(cascade=CascadeType.REMOVE)
	@JoinColumn(name = "topicID")
	private ForumTopic topic;
	
	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ForumCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ForumCustomer customer) {
		this.customer = customer;
	}

	public String getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public ForumComment() {
		super();
	}

	public ForumTopic getTopic() {
		return topic;
	}

	public void setTopic(ForumTopic topic) {
		this.topic = topic;
	}

	public ForumComment(int commentID, String comment, ForumCustomer customer,
			String dateOfCreation, ForumTopic topic) {
		super();
		this.commentID = commentID;
		this.comment = comment;
		this.customer = customer;
		this.dateOfCreation = dateOfCreation;
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "ForumComment [commentID=" + commentID + ", comment=" + comment
				+ ", customer=" + customer + ", dateOfCreation="
				+ dateOfCreation + ", topic=" + topic + "]";
	}
	
}
