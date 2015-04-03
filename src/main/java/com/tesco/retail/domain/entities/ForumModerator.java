package com.tesco.retail.domain.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Table(name="ForumModerator")
@Entity
@NamedQuery(name="ForumModerator.findAll", query="SELECT fm FROM ForumModerator fm")
public class ForumModerator {
	@Id
	private int moderatorID;
	@Column(nullable= false)
	private String moderatorName;
	@OneToOne
	@JoinColumn(name = "adminID")
	private ForumAdmin forumAdmin;
	
	public int getModeratorID() {
		return moderatorID;
	}

	public void setModeratorID(int moderatorID) {
		this.moderatorID = moderatorID;
	}

	public String getModeratorName() {
		return moderatorName;
	}

	public void setModeratorName(String moderatorName) {
		this.moderatorName = moderatorName;
	}

	public ForumAdmin getForumAdmin() {
		return forumAdmin;
	}

	public void setForumAdmin(ForumAdmin forumAdmin) {
		this.forumAdmin = forumAdmin;
	}

	public ForumModerator(int moderatorID, String moderatorName,
			ForumAdmin forumAdmin) {
		super();
		this.moderatorID = moderatorID;
		this.moderatorName = moderatorName;
		this.forumAdmin = forumAdmin;
	}

	public ForumModerator() {
		super();
	}

	@Override
	public String toString() {
		return "ForumModerator [moderatorID=" + moderatorID
				+ ", moderatorName=" + moderatorName + ", forumAdmin="
				+ forumAdmin + "]";
	}

}
