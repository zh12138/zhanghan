package org.lanqiao.entity;

public class Student {
	private int studentNo;
	private String studentName;
	private int studentAge;
	private String gradeName;
	
	public Student() {
		
		
	}

	 public Student(int studentNo, String studentName, int studentAge, String gradeName){
			   this.studentNo = studentNo;
			   this.studentName = studentName;
			   this.studentAge = studentAge;
			   this.gradeName = gradeName;
			  }
	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentAge() {
		return studentAge;
	}

	public void setStudentAge(int studentAge) {
		this.studentAge = studentAge;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
}
