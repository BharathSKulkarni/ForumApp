package com.tesco.retail.domain.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "ForumAdmin")
@Entity
@NamedQuery(name="ForumAdmin.findAll",query="Select ad from ForumAdmin ad")
public class ForumAdmin {
	@Id
	private int adminID;
	@Column(nullable = false)
	private String adminName;

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public ForumAdmin(int adminID, String adminName) {
		super();
		this.adminID = adminID;
		this.adminName = adminName;
	}

	public ForumAdmin() {
		super();
	}

	@Override
	public String toString() {
		return "ForumAdmin [adminID=" + adminID + ", adminName=" + adminName
				+ "]";
	}

}
