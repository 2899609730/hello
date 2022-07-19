package com.fox.service.impl;

import com.fox.dao.CourseDao;
import com.fox.dao.impl.CourseDaoImpl;
import com.fox.pojo.Course;
import com.fox.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    CourseDao courseDao = new CourseDaoImpl();

    @Override
    public List<Course> findCourseList() {
        return courseDao.findCourseList();
    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {
        return courseDao.findByCourseNameAndStatus(courseName, status);
    }
}
