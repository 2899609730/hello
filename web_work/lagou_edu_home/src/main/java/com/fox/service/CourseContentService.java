package com.fox.service;

import com.fox.pojo.Course;
import com.fox.pojo.Course_Section;

import java.util.List;

public interface CourseContentService {
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId);
    public Course findCourseById(int courseId);
    public String saveSection(Course_Section section);
    public String updateSection(Course_Section section);
    public String updateSectionStatus(int id,int status);
}
