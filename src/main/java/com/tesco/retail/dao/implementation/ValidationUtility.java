package com.tesco.retail.dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.tesco.retail.domain.entities.ForumAbusiveWords;
import com.tesco.retail.domain.entities.ForumComment;
import com.tesco.retail.domain.entities.ForumTopic;

public class ValidationUtility {

	ForumUtility util = new ForumUtility();
	EntityManager em = util.getEntityManager();

	public boolean validateTopic(ForumTopic topic){	
		
		boolean validationStatus=true;
		String topicName=topic.getTopic();
		String desc=topic.getDescription();	

		String[] topicWords=topicName.split(" ");		 
		String[] descWords=desc.split(" ");

		TypedQuery<ForumAbusiveWords> query=em.createNamedQuery("ForumAbusiveWords.findAll", ForumAbusiveWords.class);
		List<ForumAbusiveWords> abuseWordslist = query.getResultList();

		for (ForumAbusiveWords forumAbusiveWords : abuseWordslist) {
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

	 

	public boolean validateComment(ForumComment comment) {
		boolean validationStatus=true;
		String userComment = comment.getComment();		 
		String[] commentWords = userComment.split(" ");
		
		TypedQuery<ForumAbusiveWords> query=em.createNamedQuery("ForumAbusiveWords.findAll", ForumAbusiveWords.class);
		List<ForumAbusiveWords> abuseWordslist = query.getResultList();
		
		for (ForumAbusiveWords forumAbusiveWords : abuseWordslist) {
			if(forumAbusiveWords.equals(commentWords)){
				validationStatus=false;
			}
		}
		return validationStatus;
		
		
		/*String validatedComment = "";
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
		return validatedComment;*/
		
	}

}
