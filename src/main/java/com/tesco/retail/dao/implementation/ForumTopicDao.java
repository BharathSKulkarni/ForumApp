package com.tesco.retail.dao.implementation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;


import com.tesco.retail.dao.implementation.ValidationUtility;
import com.tesco.retail.domain.entities.ForumAbuseStatus;
import com.tesco.retail.domain.entities.ForumAbusiveWords;
import com.tesco.retail.domain.entities.ForumAdmin;
import com.tesco.retail.domain.entities.ForumCategory;
import com.tesco.retail.domain.entities.ForumComment;
import com.tesco.retail.domain.entities.ForumCustomer;
import com.tesco.retail.domain.entities.ForumModerator;
import com.tesco.retail.domain.entities.ForumTopic;

public class ForumTopicDao {

	ForumUtility util = new ForumUtility();
	EntityManager em = util.getEntityManager();

	public void insertTopic(ForumTopic topic) {
		//ForumModeratorDao dao=new ForumModeratorDao();
		//ForumTopic approved= dao.approveTopicbyModerator(topic);
		
		ForumTopic approved=approveTopic(topic);
		if(approved.isApproved()){
		EntityTransaction etx = em.getTransaction();
		try {
			if(approved.isApproved()){}
			etx.begin();			
			em.persist(approved);
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}else{
			System.out.println("Dont Use Abusive Words..!!>>>>");
		}
	}

	public List<ForumTopic> getAllTopic() {
		Query query = em.createQuery("SELECT ft from ForumTopic ft");
		List<ForumTopic> topicList = query.getResultList();
		for (ForumTopic string : topicList) {
			System.out.println(string);
		}
		return topicList;
	}

	public ForumTopic getTopicByID(int topicID){
		ForumTopic topic=em.find(ForumTopic.class, topicID);		 		
		return topic;		
	}


	public List<ForumComment> getCommentsByTopic(int topicID) {
		Query query = em.createQuery("SELECT fc from ForumComment fc WHERE topicID=:topicID");
		query.setParameter("topicID", topicID);
		@SuppressWarnings("unchecked")
		List<ForumComment> commentList = query.getResultList();
		for (ForumComment string : commentList) {
			System.out.println(string);
		}
		return commentList;
	}

	public void removeTopic(int topicID) {
		System.out.println("removeTopic called");
		EntityTransaction etx = em.getTransaction();
		try {
			etx.begin();

			//Remove all comments under Topic to be deleted
			Query query=em.createQuery("Select fc from ForumComment fc where topicID=:id");
			query.setParameter("id",topicID);
			@SuppressWarnings("unchecked")			 
			List<ForumComment> comments=query.getResultList();
			for (ForumComment forumComment : comments) {
				em.remove(forumComment);
			}

			//Remove Topic
			em.remove(em.find(ForumTopic.class, topicID));
			etx.commit();
			System.out.println("Removed topic");
		} catch (Exception e) {
			etx.rollback();
			e.printStackTrace();
		}

	}

	public void updateTopic(ForumTopic topic) {
		int id=topic.getTopicID();		 

		EntityTransaction etx= em.getTransaction();
		try {
			System.out.println("Inside Update..!!");
			ForumTopic newTopic=em.find(ForumTopic.class,id);			 
			etx.begin();
			newTopic.setTopic(topic.getTopic());
			newTopic.setDescription(topic.getDescription());
			etx.commit();
		} catch(Exception e) {			
			e.printStackTrace();
		}		

	}

	public ForumTopic approveTopic(ForumTopic topic) {
		//ValidationUtility validate=new ValidationUtility();
		ForumAbuseStatus status=null;
		int id=topic.getTopicID();		 
		boolean result=validateTopic(topic);
		EntityTransaction etx= em.getTransaction();
		if(result){
			//ForumTopic newTopic=em.find(ForumTopic.class,id);	
			topic.setApproved(true);
		}else{			 
			topic.setApproved(false);
			ForumCustomer customer=topic.getCustomer();	
			ForumTopic topic1=topic;
			status=new ForumAbuseStatus(0, topic1,customer);
			ForumAbuseStatusDao dao=new ForumAbuseStatusDao();
			dao.insertAbuseStatus(status);			
		}
		if(topic.isApproved()){
			System.out.println("<<<<<<<<<<<<<<<<<<<Setting topic approved as true>>>>>>>>>>>>>>>>>>>");
			topic.setApproved(true);
		}else{
			System.out.println("<<<<<<<<<<<<<<<<<<<Setting topic approved as true>>>>>>>>>>>>>>>>>>>");
			topic.setApproved(true);
		}
		return topic;
	}
	
public boolean validateTopic(ForumTopic topic){	

		
		boolean validationStatus=true;
		String topicName=topic.getTopic();
		String desc=topic.getDescription();	

		String[] topicWords=topicName.split(" ");		 
		String[] descWords=desc.split(" ");

		TypedQuery<ForumAbusiveWords> query=em.createNamedQuery("ForumAbusiveWords.findAll", ForumAbusiveWords.class);
		List<ForumAbusiveWords> result = query.getResultList();
		
		List<String> abuseWordList = new ArrayList<String>();
		for (ForumAbusiveWords string : result) {			 
			abuseWordList.add(string.getAbuseWord());			 
		}

		for (String forumAbusiveWords : abuseWordList) {
			for(int i=0;i<topicWords.length;i++){
				if(forumAbusiveWords.equals(topicWords[i])){				 
					validationStatus=false;
				}
			}
			for (int i = 0; i < descWords.length; i++) {
				if(forumAbusiveWords.equals(descWords)){
					validationStatus=false;
				}
			}
		}
		return validationStatus;
	}
}
