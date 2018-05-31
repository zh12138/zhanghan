package org.lanqiao.entity;

import java.util.List;

public class Page {
	//总页数
	private int totalPage;
	//总数据
	private int totalCount;
	//页面大小
	private int pageSize;
	//当前页面
	private int currentPage;
	//存储信息
	private List<Student> students;
	
	public Page() {
		
	}
	
	
	
	public int getTotalPage() {
		return totalPage;
	}
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		totalPage=this.totalCount%pageSize ==0 ? this.totalCount / pageSize :this.totalCount/pageSize+1;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	

}
