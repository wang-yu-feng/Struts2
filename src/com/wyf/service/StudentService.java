package com.wyf.service;

import java.util.List;

import com.wyf.entity.Student;

public interface StudentService {
	/**
	 * 查询所有学生
	 * @return
	 */
	public List<Student> findStudentList();
}
