package com.tesco.retail.dao.implementation;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.tesco.retail.domain.entities.ForumAbuseStatus;

public class ForumAbuseStatusDao {
	
	ForumUtility util = new ForumUtility();
	

	public void insertAbuseStatus(ForumAbuseStatus fas) {
		EntityManager em = util.getEntityManager();
		EntityTransaction etx = em.getTransaction();
		try {
			etx.begin();			
			em.persist(fas);
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			etx.rollback();
		}
	}
	
}
