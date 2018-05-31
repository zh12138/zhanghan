package org.lanqiao.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.lanqiao.entity.Student;
import org.lanqiao.service.StudentService;


public class AddStudentServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  this.doPost(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		//�����ύ������
		int stuNo=Integer.parseInt(request.getParameter("sno"));
		String stuName=request.getParameter("sname");
		int stuAge=Integer.parseInt(request.getParameter("sage"));
		String graName=request.getParameter("gname");
		//��װ
		Student stu =new Student(stuNo,stuName,stuAge,graName);
		//����ҵ����߼����� service
		StudentService stuservice=new StudentService();
		boolean result=stuservice.addStudent(stu);
		if(result) {
			System.out.println("���ӳɹ�");
			request.setAttribute("stu", stu);
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}else {
			System.out.println("����ʧ��");
			

			response.sendRedirect("addStudent.jsp");
		}
		
		
		
		
	}

}
