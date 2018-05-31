package org.lanqiao.service;

import java.util.List;

import org.lanqiao.Dao.StudentDao;
import org.lanqiao.entity.Student;

public class StudentService {
	//业务逻辑层
	public boolean addStudent(Student stu) {
		StudentDao stuDao=new StudentDao();
		//判断学生是否存在，
		if(stuDao.isExistByNo(stu.getStudentNo())) {
			
			System.out.println("学生存在，不能重复增加");
			return false;
		}
		//不存在调用addStudent方法
		return stuDao.addStudent(stu);
	}
	
	public List<Student> queryAllStudents(){
		StudentDao dao=new StudentDao();
		return dao.findAllStudents();
	}
	
	//根据学号删除学生
	public boolean DelStudentByNo(int stuNo) {
		StudentDao dao=new StudentDao();
		if(!dao.isExistByNo(stuNo)) {
			System.out.println("查无此人，无法删除");
			return false;
		}
		
		return dao.deleteStudentByNo(stuNo);
	}
	//根据学号查询某一个学生
	public Student queryStudentByNo(int stuNo) {
		StudentDao dao=new StudentDao();
		
		
		return dao.queryStudentByNo(stuNo);
	}
	
	//修改学生信息
	public boolean updateStudent(Student stu,int stuNo) {
		StudentDao dao=new StudentDao();
		if(!dao.isExistByNo(stuNo)) {
			System.out.println("查无此人，无法修改");
		}
		
		
		return dao.updateStudent(stu, stuNo);
	}
	
	//分页
	public List<Student> getStudentListForCurrentPage(int currentPage,int pageSize){
		
		StudentDao dao=new StudentDao();
		
		return dao.getStudentListForCurrentPage(pageSize, currentPage);
	}
	//获取页面总数
	public int getTotalCount() {
		StudentDao dao=new StudentDao();
		
		return dao.getTotalCount();
	}

}
