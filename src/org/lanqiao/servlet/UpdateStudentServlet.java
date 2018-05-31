package org.lanqiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Student;
import org.lanqiao.service.StudentService;


public class UpdateStudentServlet extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int studentNo=Integer.parseInt(request.getParameter("sno"));
		//接受
		
		String studentName=request.getParameter("sname");
		int studentAge=Integer.parseInt(request.getParameter("sage"));
		String gradeName=request.getParameter("gname");
		//封装信息
		Student student=new Student(studentNo,studentName,studentAge,gradeName);
		
		StudentService service=new StudentService();
		boolean result=service.updateStudent(student, studentNo);
		
		if(!result) {
			request.getRequestDispatcher("pageStudentServlet").forward(request,response);
			//response.sendRedirect("pageStudentServlet");
			
		}else {
			response.sendRedirect("showStudentInfo.jsp");
			//request.getRequestDispatcher("showStudentInfo.jsp").forward(request,response);
		}
	}

}
