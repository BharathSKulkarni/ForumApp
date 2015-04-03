package com.tesco.retail.dao.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.tesco.retail.domain.entities.ForumAdmin;
import com.tesco.retail.domain.entities.ForumCategory;
import com.tesco.retail.domain.entities.ForumModerator;

public class ForumAdminDao {
	
	ForumUtility util = new ForumUtility();
	EntityManager em = util.getEntityManager();
	
	public void insertAdmin(ForumAdmin admin) {
		EntityTransaction etx = em.getTransaction();
		try{
			etx.begin();
			em.persist(admin);
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<ForumAdmin> getAllAdmins() {
		TypedQuery<ForumAdmin> query=em.createNamedQuery("ForumAdmin.findAll", ForumAdmin.class);
		List<ForumAdmin> adminList = query.getResultList();
		for (ForumAdmin string : adminList) {
			System.out.println(string);
		}
		return adminList;
	}
	
	public void addCategory(ForumCategory category){
		ForumCategoryDao dao=new ForumCategoryDao();
		dao.insertCategory(category);		
	}
	
	//Not Necessary
	public void removeCategory(int categoryID ) {
		ForumCategoryDao dao=new ForumCategoryDao();
		dao.deleteCategory(categoryID);
	}
	
	public void updateCategory(ForumCategory category) {
		ForumCategoryDao dao=new ForumCategoryDao();
		dao.updateCategory(category);		
	}
	
	public void addModerator(ForumModerator moderator) {
		ForumModeratorDao dao=new ForumModeratorDao();
		dao.insertModerator(moderator);		
	}
	
	public void removeModerator(int moderatorID) {
		ForumModeratorDao dao=new ForumModeratorDao();
		dao.removeModerator(moderatorID);		
	}
	
	
}
