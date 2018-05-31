package org.lanqiao.Dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.lanqiao.entity.Student;
import org.lanqiao.util.DButil;

public class StudentDao {
	
	//增加
	public boolean addStudent(Student stu) {
         boolean flag=false;
			String sql="insert into student(stuNo,stuName,stuAge,graName) values(?,?,?,?)";
			QueryRunner runner=new QueryRunner(DButil.getDataSourceWithC3p0ByXML());
			Object[] os= {stu.getStudentName(),
					stu.getStudentAge(),
					stu.getStudentNo(),
					stu.getGradeName()};
			
			try {
				if(runner.update(sql,os) >0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

;
			return flag;
	}
	
	
	//根据学号查询
	public Student queryStudentByNo(int stuNo) {
		
		QueryRunner runner=new QueryRunner(DButil.getDataSourceWithC3p0ByXML());
			String sql="select * from student where stuNo=?";
			Object []os= {stuNo};
			Student stu = null;
			try {
				 stu=runner.query(sql, new BeanHandler<Student>(Student.class),os);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			ResultSet rs=DButil.executeQuery(sql, os);
//			
//			try {
//				while(rs.next()) {
//					int sNo=rs.getInt("stuNo");
//					String sName=rs.getString("stuName");
//					int sAge=rs.getInt("stuAge");
//					String gName=rs.getString("graName");
//					//封装
//				 stu=new Student(sNo,sName,sAge,gName);
//					
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		
		return stu;
	}
	//根据学号判断一个学生是否存在
	public boolean isExistByNo(int stuNo) {
		boolean isExit=false;
		Student stu=this.queryStudentByNo(stuNo);
		isExit=((stu ==null)? false :true);
		return isExit;

	}
	
	
	public List<Student> findAllStudents(){
		QueryRunner runner=new QueryRunner(DButil.getDataSourceWithC3p0ByXML());
			String sql="select * from student";
			List<Student> list =new ArrayList<Student>();
			try {
				list=runner.query(sql, new BeanListHandler<Student>(Student.class));
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			Object[] os=null;
//			ResultSet rs =DButil.executeQuery(sql, os);
//			try {
//				while(rs.next()) {
//					int stuNo = rs.getInt(1);
//					String stuName = rs.getString(2);
//					int stuAge = rs.getInt(3);
//					String gName = rs.getString(4);
//					Student stu = new Student(stuNo,stuName,stuAge,gName);
//					list.add(stu);
//					
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			return list;
		
	}
	//根据学号删除学生
	public boolean deleteStudentByNo(int stuNo) {
		boolean flag =false;
		QueryRunner runner=new QueryRunner(DButil.getDataSourceWithC3p0ByXML());
			String sql="delete from student where stuNo=?";
			
			Object []os= {stuNo};
			try {
				if(runner.update(sql,os)>0) {
					flag=true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//return DButil.executeAddOrUpdateOrDelete(sql, os);
		return flag;
	}
	
	
	public boolean updateStudent(Student stu,int stuNo) {
		boolean falg=false;
		QueryRunner runner=new QueryRunner(DButil.getDataSourceWithC3p0ByXML());
				String sql="update student set stuNo=?,stuName=?,stuAge=?,graName=? where stuNo=?";
				
				Object[]os= {stu.getStudentNo(),
						stu.getStudentName(),
						stu.getStudentAge(),
						stu.getGradeName()};
				try {
					if(runner.update(sql,os)>0) {
						return true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//return DButil.executeAddOrUpdateOrDelete(sql, os);
				return false;
	}
	
	
	//分页
	public List<Student> getStudentListForCurrentPage(int pageSize,int currentPage){
		
		
		
		List<Student> list=new ArrayList<Student>();
			
			String sql="select t1.stuNo,t1.stuName,t1.stuAge,t1.graName from  (select rownum r,t.* from (select * from student ) t where rownum<= ?) t1 where r>= ?";
			Object [] os= {currentPage*pageSize,(currentPage-1)*pageSize+1};
			ResultSet rs = DButil.executeQuery(sql, os);
			try {
				while(rs.next()) {
					int sNo=rs.getInt("stuNo");
					String sName=rs.getString("stuName");
					int sAge=rs.getInt("stuAge");
					String gName=rs.getString("graName");
					//封装对象
					Student stu= new Student(sNo,sName,sAge,gName);
					
					list.add(stu);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		
		
		
		
		return list;
	
}
	
	//获取总页数
	public  int getTotalCount( ) {
		QueryRunner runner=new QueryRunner(DButil.getDataSourceWithC3p0ByXML());
			String sql="select count(*) from student";
			
			return DButil.getTotalCount(sql);
			
			
		
	}
	
}
