package com.fox.service.impl;

import com.fox.config.StatusCode;
import com.fox.dao.CourseContentDao;
import com.fox.dao.impl.CourseContentDaoImpl;
import com.fox.pojo.Course;
import com.fox.pojo.Course_Section;
import com.fox.service.CourseContentService;
import com.fox.utils.DateUtils;

import java.util.List;

public class CourseContentServiceImpl implements CourseContentService {
    CourseContentDao contentDao = new CourseContentDaoImpl();

    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        List<Course_Section> sections =
                contentDao.findSectionAndLessonByCourseId(courseId);
        return sections;
    }

    @Override
    public Course findCourseById(int courseId) {
        Course course = contentDao.findCourseByCourseId(courseId);
        return course;
    }

    @Override
    public String saveSection(Course_Section section) {
        //1.补全章节信息
        section.setStatus(2); //状态，0:隐藏；1：待更新；2：已发布
        String date = DateUtils.getDateFormart();
        section.setCreate_time(date);
        section.setUpdate_time(date);
        //2.调用Dao进行插入
        int i = contentDao.saveSection(section);
        //3.根据插入是否成功,封装对应信息
        if (i > 0) {
            //保存成功
            String result = StatusCode.SUCCESS.toString();
            return result;
        } else {
            //保存失败
            String result = StatusCode.FAIL.toString();
            return result;
        }
    }

    @Override
    public String updateSection(Course_Section section) {
        //1.补全章节信息
        String date = DateUtils.getDateFormart();
        section.setUpdate_time(date);
        int i = contentDao.updateSection(section);
        if (i > 0) {
            String result = StatusCode.SUCCESS.toString();
            return result;
        } else {
            String result = StatusCode.FAIL.toString();
            return result;
        }
    }

    @Override
    public String updateSectionStatus(int id, int status) {
//调用Dao 修改状态
        int i = contentDao.updateSectionStatus(id, status);
//3.根据修改是否成功,封装对应信息
        if (i > 0) {
            String result = StatusCode.SUCCESS.toString();
            return result;
        } else {
            String result = StatusCode.FAIL.toString();
            return result;
        }

    }
}