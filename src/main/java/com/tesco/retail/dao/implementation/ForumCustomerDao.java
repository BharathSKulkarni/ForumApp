package com.tesco.retail.dao.implementation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.tesco.retail.domain.entities.ForumCustomer;

public class ForumCustomerDao {
	
	ForumUtility util = new ForumUtility();
	EntityManager em = util.getEntityManager();
	
	public void insertCustomer(ForumCustomer customer) {
		EntityTransaction etx = em.getTransaction();
		try{
			etx.begin();
			em.persist(customer);
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ForumCustomer getCustomerByID(int id) {
		 ForumCustomer customer=em.find(ForumCustomer.class,id);
		return customer;
	}
}
