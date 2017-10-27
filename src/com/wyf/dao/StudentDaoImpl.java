package com.wyf.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wyf.base.util.DBUtil;
import com.wyf.entity.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public List<Student> findStudentList() {
		DBUtil dbUtil = new DBUtil();
		String sql = "select * from student";
		List<Student> stuList = new ArrayList<Student>();
		ResultSet rs = dbUtil.select(sql);
		try {
			while(rs.next()){
				Student stu = new Student(rs.getInt(1), rs.getString(2), rs.getInt(3));
				stuList.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stuList;
	}

}
