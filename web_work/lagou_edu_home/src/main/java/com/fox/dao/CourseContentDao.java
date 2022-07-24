package com.fox.dao;

import com.fox.pojo.Course;
import com.fox.pojo.Course_Lesson;
import com.fox.pojo.Course_Section;

import java.util.List;

public interface CourseContentDao {
    //根据课程ID查询章节相关信息
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId);

    //根据章节ID 查询课时相关的课时信息
    public List<Course_Lesson> findLessonBySectionId(int sectionId);

    //添加章节时进行数据回显
    public Course findCourseByCourseId(int courseId);

    //保存章节信息
    public int saveSection(Course_Section section);

    //修改章节信息
    public int updateSection(Course_Section section);

    //修改章节的状态
    public int updateSectionStatus(int id, int status);
}
