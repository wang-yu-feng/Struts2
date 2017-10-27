package com.wyf.service;

import java.util.List;

import com.wyf.dao.StudentDao;
import com.wyf.dao.StudentDaoImpl;
import com.wyf.entity.Student;

public class StudentServiceImpl implements StudentService {

	@Override
	public List<Student> findStudentList() {
		StudentDao dao = new StudentDaoImpl();
		return dao.findStudentList();
	}

}
