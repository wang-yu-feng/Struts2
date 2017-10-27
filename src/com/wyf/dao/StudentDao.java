package com.wyf.dao;

import java.util.List;

import com.wyf.entity.Student;

public interface StudentDao {
	/**
	 * 查找所有学生信息
	 * @return 学生信息集合
	 */
	public List<Student> findStudentList();
}
