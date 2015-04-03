package com.tesco.retail.web.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.tesco.retail.dao.implementation.ForumCategoryDao;
import com.tesco.retail.dao.implementation.ForumCommentDao;
import com.tesco.retail.dao.implementation.ForumTopicDao;
import com.tesco.retail.domain.entities.ForumAdmin;
import com.tesco.retail.domain.entities.ForumCategory;
import com.tesco.retail.domain.entities.ForumComment;
import com.tesco.retail.domain.entities.ForumCustomer;
import com.tesco.retail.domain.entities.ForumModerator;
import com.tesco.retail.domain.entities.ForumTopic;

import com.google.gson.Gson;


public class ForumAddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ForumAddCommentServlet() {

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
		JSONObject joComment = null;
		try {
			joComment = (JSONObject) parser.parse(sb.toString());
		} catch (org.json.simple.parser.ParseException e) {

			e.printStackTrace();
		}
		String Comment = (String) joComment.get("comment");
		
		ForumComment comment=new ForumComment();
		comment.setComment(Comment);
		
		ForumCommentDao dao=new ForumCommentDao();
		List<ForumComment> commentList = new ArrayList<ForumComment>();
		commentList = dao.getAllComments();
		comment.setCommentID((commentList.size()+2));
		
		ForumCustomer customer = new ForumCustomer();
		customer.setCustomerID(2);
		customer.setCustomerName("Sanjeev");
		comment.setCustomer(customer);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   Date date = new Date();
		String datestr = dateFormat.format(date);   
		comment.setDateOfCreation(datestr);
		
		HttpSession session = request.getSession();
		int topicID = (Integer) session.getAttribute("topicID");
		ForumTopicDao topicdao = new ForumTopicDao();
		ForumTopic topic = topicdao.getTopicByID(topicID);
		comment.setTopic(topic);
		
		ForumCommentDao commentdao = new ForumCommentDao();
		commentdao.insertComment(comment);
		
		response.setContentType("text/html");
		response.setHeader("Access-Control-Allow-Origin",
				"http://localhost:8085");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"POST,GET,HEAD,OPTIONS");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"Origin,Accept,x-auth-token,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers");
		
	}

}
