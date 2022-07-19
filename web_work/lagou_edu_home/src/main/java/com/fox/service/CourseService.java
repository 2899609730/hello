package com.fox.service;

import com.fox.pojo.Course;

import java.util.List;

public interface CourseService {
    public List<Course> findCourseList();

    public List<Course> findByCourseNameAndStatus(String courseName, String status);
}
