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
import com.tesco.retail.dao.implementation.ForumTopicDao;
import com.tesco.retail.domain.entities.ForumCategory;
import com.tesco.retail.domain.entities.ForumCustomer;
import com.tesco.retail.domain.entities.ForumTopic;

public class ForumTopicApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ForumTopicApproveServlet() {
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
		
		String topicID = (String) joTopic.get("topID");
		Integer topic = Integer.parseInt(topicID);
		int i = (int) topic;
		
	
		
		ForumTopicDao dao = new ForumTopicDao();
		ForumTopic topicToApprove = dao.getTopicByID(i);
		dao.approveTopic(topicToApprove);
		
		
		response.setContentType("text/html");
		response.setHeader("Access-Control-Allow-Origin",
				"http://localhost:8085");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods",
				"POST,GET,HEAD,OPTIONS");
		response.setHeader(
				"Access-Control-Allow-Headers",
				"Origin,Accept,x-auth-token,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers");
		
		PrintWriter out = response.getWriter();
		out.println("Topic successfully approved");

		
	}

}
