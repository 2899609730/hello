package com.fox.dao;

import com.fox.pojo.Course;

import java.util.List;

public interface CourseDao {
    //查询课程列表信息
    public List<Course> findCourseList();
}
