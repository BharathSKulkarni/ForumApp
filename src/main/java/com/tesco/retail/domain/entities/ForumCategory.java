package com.tesco.retail.domain.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "ForumCategory")
@Entity
@NamedQuery(name="ForumCategory.findAll", query="SELECT fc FROM ForumCategory fc")
public class ForumCategory {
	@Id
	private int categoryID;
	@Column(nullable = false)
	private String category;
	@OneToOne
	@JoinColumn(name = "moderatorID")
	private ForumModerator forumModerator;
	private boolean approved = false;
	
	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ForumModerator getForumModerator() {
		return forumModerator;
	}

	public void setForumModerator(ForumModerator forumModerator) {
		this.forumModerator = forumModerator;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public ForumCategory(int categoryID, String category,
			ForumModerator forumModerator, boolean approved) {
		super();
		this.categoryID = categoryID;
		this.category = category;
		this.forumModerator = forumModerator;
		this.approved = approved;
	}

	public ForumCategory() {
		super();
	}

	@Override
	public String toString() {
		return "ForumCategory [categoryID=" + categoryID + ", category="
				+ category + ", forumModerator=" + forumModerator
				+ ", approved=" + approved + "]";
	}

	
}
