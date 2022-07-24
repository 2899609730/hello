package com.fox.dao;

import com.fox.pojo.Course;

import java.util.List;

public interface CourseDao {
    //查询课程列表信息
    public List<Course> findCourseList();

    /**
     * 根据课程名称,课程状态 查询课程信息
     */
    public List<Course> findByCourseNameAndStatus(String courseName, String status);

    //保存课程营销信息
    public int saveCourseSalesInfo(Course course);

    //根据课程ID 查询课程信息
    public Course findCourseById(int id);

    //修改课程营销信息
    public int updateCourseSalesInfo(Course course);
    //修改课程状态
    int updateCourseStatus(Course course);
}
