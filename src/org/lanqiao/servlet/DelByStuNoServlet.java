package org.lanqiao.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.service.StudentService;

public class DelByStuNoServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("Utf-8");
		//response.setContentType("text/html;charset=utf-8");
		System.out.println("2222");
		int studentNo=Integer.parseInt(request.getParameter("stuNo"));
		
		StudentService service=new StudentService();
		boolean result=service.DelStudentByNo(studentNo);
		if(result) {
			System.out.println("É¾³ý³É¹¦");
			request.setAttribute("msg", "É¾³ý³É¹¦");
			request.getRequestDispatcher("pageStudentServlet").forward(request, response);
		}else {
			System.out.println("É¾³ýÊ§°Ü");
			//response.sendRedirect("nodelete.jsp");
			
		}
		
		
		
		
		
	}

}
