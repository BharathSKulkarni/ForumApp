package com.tesco.retail.dao.implementation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ForumUtility {
	
	public EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("myPersistence");
		EntityManager em = emf.createEntityManager();
		return em;
	}
	
}
