package com.wyf.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.wyf.entity.Student;
import com.wyf.service.StudentService;
import com.wyf.service.StudentServiceImpl;

public class WelcomeAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<Student> stuList;
	
	public String findStudentList(){
		StudentService studentService = new StudentServiceImpl();
		stuList = studentService.findStudentList();
		return SUCCESS;
	} 
	
	
	public List<Student> getStuList() {
		return stuList;
	}
}
