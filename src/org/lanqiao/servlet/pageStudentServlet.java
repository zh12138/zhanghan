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
		//获取当前页码
		String curPage=request.getParameter("currentPage");
		
		//判断
		if(curPage ==null) {
			curPage="1";
		}
		int currentPageNo=Integer.parseInt(curPage);
		//调用逻辑层service
		StudentService stuService=new StudentService();
		//获取总数
		int totalCounts=stuService.getTotalCount();
		Page page=new Page();
		page.setPageSize(8);
		page.setTotalCount(totalCounts);
		int totalpages=page.getTotalPage();
		
		//对首页尾页进行控制
		if (currentPageNo < 1)
		  {
		   currentPageNo = 1;
		  }
		  else if (currentPageNo > page.getTotalPage())
		  {
		   currentPageNo = totalpages;
		  }
		
		//设置当前的页码
		page.setCurrentPage(currentPageNo);
		
		//获取学生集合
		List<Student> students=stuService.getStudentListForCurrentPage(page.getCurrentPage(), page.getPageSize());
		//设置每一页显示的集合
		page.setStudents(students);
		
		
		request.setAttribute("pages", page);
		//请求转发
		request.getRequestDispatcher("showAllByNo.jsp").forward(request, response);
		
		
		
		
		
	}

}
