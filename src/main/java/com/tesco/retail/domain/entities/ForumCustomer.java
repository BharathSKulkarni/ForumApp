package com.tesco.retail.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "Customer")
@Entity
public class ForumCustomer {
	@Id
	private int customerID;
	@Column(nullable=false)
	private String customerName;
	
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public ForumCustomer(int customerID, String customerName) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
	}

	public ForumCustomer() {
		super();
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerName="
				+ customerName + "]";
	}

}
