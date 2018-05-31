package org.lanqiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.User;
import org.lanqiao.service.UserService;

public class LoginServlet extends HttpServlet {
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String uname=request.getParameter("uname");
		String upwd=request.getParameter("upwd");
		//·â×°ÐÅÏ¢
		User user =new User();
		user.setUsername(uname);
		user.setPassword(upwd);
		
		UserService service =new UserService();
		
		User user2=service.findUserByUsername(uname);
		
		
		if(user2 !=null) {
			if(user2.getPassword().equals(upwd)) {
				request.getRequestDispatcher("pageStudentServlet").forward(request, response);
			}
			
		}else {
			response.sendRedirect("login.jsp");
		}
		
	}

}
