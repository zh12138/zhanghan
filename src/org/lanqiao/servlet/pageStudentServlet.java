package org.lanqiao.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lanqiao.entity.Page;
import org.lanqiao.entity.Student;
import org.lanqiao.service.StudentService;


public class pageStudentServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//��ȡ��ǰҳ��
		String curPage=request.getParameter("currentPage");
		
		//�ж�
		if(curPage ==null) {
			curPage="1";
		}
		int currentPageNo=Integer.parseInt(curPage);
		//�����߼���service
		StudentService stuService=new StudentService();
		//��ȡ����
		int totalCounts=stuService.getTotalCount();
		Page page=new Page();
		page.setPageSize(8);
		page.setTotalCount(totalCounts);
		int totalpages=page.getTotalPage();
		
		//����ҳβҳ���п���
		if (currentPageNo < 1)
		  {
		   currentPageNo = 1;
		  }
		  else if (currentPageNo > page.getTotalPage())
		  {
		   currentPageNo = totalpages;
		  }
		
		//���õ�ǰ��ҳ��
		page.setCurrentPage(currentPageNo);
		
		//��ȡѧ������
		List<Student> students=stuService.getStudentListForCurrentPage(page.getCurrentPage(), page.getPageSize());
		//����ÿһҳ��ʾ�ļ���
		page.setStudents(students);
		
		
		request.setAttribute("pages", page);
		//����ת��
		request.getRequestDispatcher("showAllByNo.jsp").forward(request, response);
		
		
		
		
		
	}

}
