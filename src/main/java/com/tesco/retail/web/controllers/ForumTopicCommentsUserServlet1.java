package com.tesco.retail.web.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.tesco.retail.dao.implementation.ForumTopicDao;
import com.tesco.retail.domain.entities.ForumComment;

public class ForumTopicCommentsUserServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ForumTopicCommentsUserServlet1() {
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
		JSONObject joTopic = null;
		try {
			joTopic = (JSONObject) parser.parse(sb.toString());
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		Long topicID = (Long) joTopic.get("topID");
		Integer i = (int)(long) topicID;
				
	
		
		response.setContentType("text/html");
		response.setHeader("Access-Control-Allow-Origin",
				"http://localhost:8085");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"POST,GET,HEAD,OPTIONS");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"Origin,Accept,x-auth-token,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers");
		
		ForumTopicDao dao=new ForumTopicDao();

		List<ForumComment> comments = dao.getCommentsByTopic(i);
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		String jsonTopicList = gson.toJson(comments); 
		out.println(jsonTopicList);

	}

}
