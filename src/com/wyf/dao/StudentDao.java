package com.wyf.dao;

import java.util.List;

import com.wyf.entity.Student;

public interface StudentDao {
	/**
	 * ��������ѧ����Ϣ
	 * @return ѧ����Ϣ����
	 */
	public List<Student> findStudentList();
}
