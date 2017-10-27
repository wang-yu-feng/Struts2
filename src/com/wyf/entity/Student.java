package com.wyf.entity;

public class Student {
	private Integer studentId;
	private String studentName;
	private Integer classesId;
	
	public Student(Integer studentId, String studentName, Integer classesId) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.classesId = classesId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Integer getClassesId() {
		return classesId;
	}
	public void setClassesId(Integer classesId) {
		this.classesId = classesId;
	}
	
}
