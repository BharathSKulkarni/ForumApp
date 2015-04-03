package com.tesco.retail.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.tesco.retail.domain.entities.ForumAbusiveWords;
import com.tesco.retail.domain.entities.ForumAdmin;

public class ForumAbusiveWordsDao {

	ForumUtility util = new ForumUtility();
	EntityManager em = util.getEntityManager();

	public void insertAbusiveWords(ForumAbusiveWords abuseWord) {
		EntityTransaction etx = em.getTransaction();
		try{
			etx.begin();
			em.persist(abuseWord);
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAbuseWords(){
		TypedQuery<ForumAbusiveWords> query=em.createNamedQuery("ForumAbusiveWords.findAll", ForumAbusiveWords.class);
		List<ForumAbusiveWords> abuseWordslist = query.getResultList();
		for (ForumAbusiveWords forumAbusiveWords : abuseWordslist) {
			System.out.println(" Id :"+forumAbusiveWords.getAbuseWordID());
			
		}

	}

}
