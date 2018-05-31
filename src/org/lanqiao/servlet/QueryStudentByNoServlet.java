package org.lanqiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Student;
import org.lanqiao.service.StudentService;


public class QueryStudentByNoServlet extends HttpServlet {
	
    //根据学号查询某一个学生的信息
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int studentNo=Integer.parseInt(request.getParameter("stuNo"));
		//调用业务层service
		StudentService service =new StudentService();
		Student stu=service.queryStudentByNo(studentNo);
		
		request.setAttribute("stu", stu);
		
		request.getRequestDispatcher("showStudentInfo.jsp").forward(request, response);
	}

}
