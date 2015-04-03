package com.tesco.retail.dao.implementation;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import com.tesco.retail.domain.entities.ForumAbuseStatus;
import com.tesco.retail.domain.entities.ForumCategory;
import com.tesco.retail.domain.entities.ForumComment;
import com.tesco.retail.domain.entities.ForumCustomer;
import com.tesco.retail.domain.entities.ForumModerator;
import com.tesco.retail.domain.entities.ForumTopic;

public class ForumModeratorDao {

	ForumUtility util = new ForumUtility();
	EntityManager em = util.getEntityManager();

	public void insertModerator(ForumModerator moderator) {
		EntityTransaction etx = em.getTransaction();
		try {
			etx.begin();
			em.persist(moderator);
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeModerator(int id){
		ForumModerator moderator=getModeratorByID(id);
		EntityTransaction etx = em.getTransaction();
		try {
			etx.begin();
			em.remove(moderator);
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ForumModerator> getAllModerators() {
		TypedQuery<ForumModerator> query=em.createNamedQuery("ForumModerator.findAll", ForumModerator.class);
		List<ForumModerator> moderatorList = query.getResultList();
		for (ForumModerator string : moderatorList) {
			System.out.println("Moderator Name :"+string.getModeratorName());
		}
		return moderatorList;
	}

	public ForumModerator getModeratorByID(int moderatorID){
		ForumModerator moderator=em.find(ForumModerator.class, moderatorID);		 		
		return moderator;		
	}

	public List<ForumCategory> getCategoryByModerator(int moderatorID) {
		Query query = em.createQuery("SELECT fc from ForumCategory fc WHERE moderatorID=:moderatorID");
		query.setParameter("moderatorID", moderatorID);
		@SuppressWarnings("unchecked")
		List<ForumCategory> categoryList = query.getResultList();
		for (ForumCategory string : categoryList) {
			System.out.println(string);
		}
		return categoryList;
	}

	public void addTopic(ForumTopic topic) {
		ForumTopicDao topicdao = new ForumTopicDao();
		topicdao.insertTopic(topic);
	}

	public void removeTopic(int topicID) {
		ForumTopicDao topicdao = new ForumTopicDao();
		topicdao.removeTopic(topicID);
	}

	public void updateTopic(ForumTopic topic) {
		ForumTopicDao topicdao = new ForumTopicDao();
		topicdao.updateTopic(topic);
	}

	public ForumTopic approveTopicbyModerator(ForumTopic topic) {
		ForumTopicDao topicdao = new ForumTopicDao();
		ForumTopic approvedTopic= topicdao.approveTopic(topic);
		return approvedTopic;
	}

	/*public ForumComment approveComment(ForumComment comment){
		ForumCommentDao dao=new ForumCommentDao();
		ForumComment approvedComment=dao.approveComment(comment);
		return approvedComment;
	}*/

	public void blockUser( ) {
		/*Query query=em.createQuery("select fas from ForumAbuseStatus fas");
		@SuppressWarnings("unchecked")
		List<ForumAbuseStatus> fasList=query.getResultList();
		for (ForumAbuseStatus forumAbuseStatus : fasList) {
			System.out.println(" Customer ID :"+forumAbuseStatus.getCustomer().getCustomerID());	

		}		*/ 

		/*CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ForumAbuseStatus> query = cb.createQuery(ForumAbuseStatus.class);
		Root<ForumAbuseStatus> d = query.from(ForumAbuseStatus.class);
		//Join<ForumCustomer,ForumAbuseStatus> customerID = d.join("customerID");
		query.select(d.get("customerID"),cb.count(d)).groupBy(d.get("customerID"));

		TypedQuery<ForumAbuseStatus> typedQUery=em.createQuery(query);
		List<ForumAbuseStatus> result=typedQUery.getResultList();
		for (ForumAbuseStatus forumAbuseStatus : result) {
			System.out.println(forumAbuseStatus);
		}*/
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<ForumAbuseStatus> root = cq.from(ForumAbuseStatus.class);		 
		cq.select((cb.count(root)));
		TypedQuery<Long> query=em.createQuery(cq);
		List<Long> result=query.getResultList();
		for (Long long1 : result) {
			System.out.println(long1);
		}

		 
	}

	/*public String validateComment(ForumComment comment) {
		String validatedComment = "";
		String userComment = comment.getComment();
		Query query = em.createQuery("SELECT faw.abuseWord from ForumAbusiveWords faw");
		List<String> abuseWords = query.getResultList();
		String[] commentWords = userComment.split(" ");
		for (String string : commentWords) {
			if (abuseWords.contains(string)) {
				insertIntoForumAbuseStatus(comment);
				String modifiedWord = modifyWord(string);
				validatedComment += modifiedWord + " ";
			} else {
				validatedComment += string + " ";
			}
		}
		System.out.println(validatedComment);
		return validatedComment;
	}

	public void insertIntoForumAbuseStatus(ForumComment comment) {
		ForumAbuseStatusDao asdao = new ForumAbuseStatusDao();
		asdao.insertAbuseStatus(comment);
	}

	public String modifyWord(String string) {
		int length = string.length();
		String modifiedWord = string.substring(0, 1)
				+ string.substring(1, length - 1).replaceAll("[a-z A-Z]", "*")
				+ string.substring(length - 1);
		return modifiedWord;
	}*/



}
