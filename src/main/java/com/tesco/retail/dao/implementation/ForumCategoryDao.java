package com.tesco.retail.dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import com.tesco.retail.domain.entities.ForumAdmin;
import com.tesco.retail.domain.entities.ForumCategory;
import com.tesco.retail.domain.entities.ForumComment;
import com.tesco.retail.domain.entities.ForumModerator;
import com.tesco.retail.domain.entities.ForumTopic;

public class ForumCategoryDao {

	ForumUtility util = new ForumUtility();
	EntityManager em = util.getEntityManager();

	//To Add a new Category
	public void insertCategory(ForumCategory category) {
		EntityTransaction etx = em.getTransaction();
		try{
			System.out.println("Inserting..!!");
			etx.begin();
			em.persist(category);		
			etx.commit();
			System.out.println("New Row Inserted/Updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//To Retrive Category By ID
	public ForumCategory getCategoryByID(int categoryID){		 
		ForumCategory category=em.find(ForumCategory.class, categoryID);		
		return category;		
	}


	/**
	 * To get List of all categories
	 * @return List of Categories
	 */
	public List<ForumCategory> getAllCategories() {		 
		TypedQuery<ForumCategory> query =em.createNamedQuery("ForumCategory.findAll",ForumCategory.class);
		List<ForumCategory> categoryList=query.getResultList();
		for (ForumCategory string : categoryList) {
			System.out.println("Category Names :"+string.getCategory());
		}
		return categoryList;
	}


	/**
	 * TO Retrive list of topics under category
	 * @param categoryID
	 * @return List of Topics
	 */
	public List<ForumTopic> getTopicsByCategoryID(int categoryID) {
		Query query = em.createQuery("SELECT fc from ForumTopic fc WHERE categoryID=:categoryID");
		query.setParameter("categoryID", categoryID);
		@SuppressWarnings("unchecked")
		List<ForumTopic> topicsList = query.getResultList();
		for (ForumTopic string : topicsList) {
			System.out.println("Topic NAme :"+string.getTopic());
		}
		return topicsList;
		
	}
	
	//NOt specified in Reqirement..
	public void deleteCategory(int categoryID){		
		int val=categoryID;
		EntityTransaction etx= em.getTransaction();
		try{
			etx.begin();
			System.out.println("Deleting..!!");			
			em.remove(em.find(ForumCategory.class, val));			 
			etx.commit();
			System.out.println("->> Deleted..!!!");
		}catch (Exception e) {
			e.printStackTrace();
		}		

	}

	public void updateCategory(ForumCategory category){		
		int id=category.getCategoryID();
		ForumModerator moderator= category.getForumModerator();
		String categoryName=category.getCategory();

		EntityTransaction etx= em.getTransaction();
		try {
			System.out.println("Inside Update..!!");
			ForumCategory newcategory=em.find(ForumCategory.class,id);			 
			etx.begin();
			newcategory.setCategory(categoryName);
			newcategory.setForumModerator(moderator);
			etx.commit();
		} catch (Exception e) {			
			e.printStackTrace();
		}		 
	}

}
