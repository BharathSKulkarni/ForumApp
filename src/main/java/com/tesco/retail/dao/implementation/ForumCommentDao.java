package com.tesco.retail.dao.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.tesco.retail.domain.entities.ForumAbuseStatus;
import com.tesco.retail.domain.entities.ForumAbusiveWords;
import com.tesco.retail.domain.entities.ForumAdmin;
import com.tesco.retail.domain.entities.ForumComment;
import com.tesco.retail.domain.entities.ForumCustomer;
import com.tesco.retail.domain.entities.ForumTopic;

public class ForumCommentDao {

	ForumUtility util = new ForumUtility();
	EntityManager em = util.getEntityManager();

	public void insertComment(ForumComment comment) {
		System.out.println("Inside Insert..>>>>>>>>>>>>>>>>");
		ForumComment approvedComment= approveComment(comment);
		EntityTransaction etx = em.getTransaction();
		try{
			etx.begin();
			em.persist(approvedComment);
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ForumComment> getAllComments() {
		Query query = em.createQuery("SELECT fc from ForumComment fc");
		@SuppressWarnings("unchecked")
		List<ForumComment> commentList = query.getResultList();
		for (ForumComment string : commentList) {
			System.out.println(string);
		}
		return commentList;
	}

	public List<ForumComment> getCommentByTopic(int topicID) {
		Query query = em.createQuery("SELECT fc from ForumComment fc WHERE topicID=:topicID");
		query.setParameter("topicID", topicID);
		@SuppressWarnings("unchecked")
		List<ForumComment> commentList = query.getResultList();
		for (ForumComment string : commentList) {
			System.out.println(string);
		}
		return commentList;
	}

	public void deleteComment(int commentID){		
		int val=commentID;
		EntityTransaction etx= em.getTransaction();		
		try{
			etx.begin();
			Query query = em.createQuery("DELETE  From ForumComment fc WHERE commentID=:arg1");
			query.setParameter("arg1", val);
			query.executeUpdate();
			etx.commit();
			System.out.println("->> Deleted..!!!");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ForumComment approveComment(ForumComment comment) {
		System.out.println("Inside approveComment...>>>>>>>>>>>>>>>>>");
		String modifiedComment="";	 
		String userComment = comment.getComment();			 
		String[] commentWords = userComment.split(" ");

		TypedQuery<ForumAbusiveWords> query=em.createNamedQuery("ForumAbusiveWords.findAll", ForumAbusiveWords.class);
		System.out.println("Got all abuse words");
		List<ForumAbusiveWords> resultlist = query.getResultList();
		
		List<String> abuseWordList = new ArrayList<String>();
		for (ForumAbusiveWords string : resultlist) {			 
			abuseWordList.add(string.getAbuseWord());			 
		}
		System.out.println(abuseWordList);
		

		for (String string : commentWords) {
			if (abuseWordList.contains(string)) {
				System.out.println("Inside IF(abuselist.contains) loop");
				//calling addAbuseStatus 
				addAbuseStatus(comment);
				String modifiedWord = modifyWord(string);
				modifiedComment+=modifiedWord+" ";					 
			} else {
				modifiedComment+=string+" ";
			}
		}			
		comment.setComment(modifiedComment);			

		return comment;

	}

	public String modifyWord(String string) {
		System.out.println("Inside modifyWord..>>>>>>>>>>");
		int length = string.length();
		String modifiedWord = string.substring(0, 1)
				+ string.substring(1, length - 1).replaceAll("[a-z A-Z]", "*")
				+ string.substring(length - 1);
		return modifiedWord;
	}

	public void addAbuseStatus(ForumComment comment){
		System.out.println("Inside AddAbuseStatus...>>>>>>>>>>>>");
		ForumCustomer customer=comment.getCustomer();
		ForumTopic topic=comment.getTopic();
		ForumAbuseStatus status=new ForumAbuseStatus(0,topic,customer);
		ForumAbuseStatusDao dao=new ForumAbuseStatusDao();
		dao.insertAbuseStatus(status);
	}
}
