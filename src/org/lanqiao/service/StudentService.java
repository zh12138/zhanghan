package org.lanqiao.service;

import java.util.List;

import org.lanqiao.Dao.StudentDao;
import org.lanqiao.entity.Student;

public class StudentService {
	//ҵ���߼���
	public boolean addStudent(Student stu) {
		StudentDao stuDao=new StudentDao();
		//�ж�ѧ���Ƿ���ڣ�
		if(stuDao.isExistByNo(stu.getStudentNo())) {
			
			System.out.println("ѧ�����ڣ������ظ�����");
			return false;
		}
		//�����ڵ���addStudent����
		return stuDao.addStudent(stu);
	}
	
	public List<Student> queryAllStudents(){
		StudentDao dao=new StudentDao();
		return dao.findAllStudents();
	}
	
	//����ѧ��ɾ��ѧ��
	public boolean DelStudentByNo(int stuNo) {
		StudentDao dao=new StudentDao();
		if(!dao.isExistByNo(stuNo)) {
			System.out.println("���޴��ˣ��޷�ɾ��");
			return false;
		}
		
		return dao.deleteStudentByNo(stuNo);
	}
	//����ѧ�Ų�ѯĳһ��ѧ��
	public Student queryStudentByNo(int stuNo) {
		StudentDao dao=new StudentDao();
		
		
		return dao.queryStudentByNo(stuNo);
	}
	
	//�޸�ѧ����Ϣ
	public boolean updateStudent(Student stu,int stuNo) {
		StudentDao dao=new StudentDao();
		if(!dao.isExistByNo(stuNo)) {
			System.out.println("���޴��ˣ��޷��޸�");
		}
		
		
		return dao.updateStudent(stu, stuNo);
	}
	
	//��ҳ
	public List<Student> getStudentListForCurrentPage(int currentPage,int pageSize){
		
		StudentDao dao=new StudentDao();
		
		return dao.getStudentListForCurrentPage(pageSize, currentPage);
	}
	//��ȡҳ������
	public int getTotalCount() {
		StudentDao dao=new StudentDao();
		
		return dao.getTotalCount();
	}

}
