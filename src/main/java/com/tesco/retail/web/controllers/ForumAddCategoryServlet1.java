package com.tesco.retail.web.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.tesco.retail.dao.implementation.ForumCategoryDao;
import com.tesco.retail.dao.implementation.ForumModeratorDao;
import com.tesco.retail.domain.entities.ForumAdmin;
import com.tesco.retail.domain.entities.ForumCategory;
import com.tesco.retail.domain.entities.ForumModerator;


public class ForumAddCategoryServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ForumAddCategoryServlet1() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader reader = request.getReader();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONParser parser = new JSONParser();
		JSONObject joCategory = null;
		try {
			joCategory = (JSONObject) parser.parse(sb.toString());
		} catch (org.json.simple.parser.ParseException e) {

			e.printStackTrace();
		}
		String categoryName = (String) joCategory.get("category");
		ForumCategory category=new ForumCategory();
		
		category.setCategory(categoryName);
		
		ForumCategoryDao dao=new ForumCategoryDao();
		List<ForumCategory> categoryList = new ArrayList<ForumCategory>();
		categoryList = dao.getAllCategories();
		
		category.setCategoryID(categoryList.size()+1);
		ForumAdmin forumAdmin = new ForumAdmin(2,"ForumAdmin2");
		
		String moderatorID = (String) joCategory.get("moderatorID");
		Integer i = Integer.parseInt(moderatorID);
		ForumModeratorDao forumModeratorDao = new ForumModeratorDao();
		ForumModerator moderator = forumModeratorDao.getModeratorByID(i);
		category.setForumModerator(moderator);
		
		ForumModerator forumModerator = new ForumModerator(3,"Bharath",forumAdmin);
		category.setForumModerator(forumModerator );
		dao.insertCategory(category);
	
		
		response.setContentType("text/html");
		response.setHeader("Access-Control-Allow-Origin",
				"http://localhost:8085");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"POST,GET,HEAD,OPTIONS");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"Origin,Accept,x-auth-token,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers");
		

		List<ForumCategory> cat=dao.getAllCategories();
		PrintWriter out=response.getWriter();
		Gson gson =new Gson();
		String jsonCategoryList = gson.toJson(cat); 
		out.println(jsonCategoryList);
	}

}
