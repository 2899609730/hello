package com.fox.service.impl;

import com.fox.config.StatusCode;
import com.fox.dao.CourseDao;
import com.fox.dao.impl.CourseDaoImpl;
import com.fox.pojo.Course;
import com.fox.service.CourseService;
import com.fox.utils.DateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fox.config.StatusCode.FAIL;
import static com.fox.config.StatusCode.SUCCESS;

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

    @Override
    public String saveCourseSalesInfo(Course course) {
        //1.补全课程信息
        String dateFormart = DateUtils.getDateFormart();
        course.setCreate_time(dateFormart);
        course.setUpdate_time(dateFormart);
        course.setStatus(0);

        //2.调用Dao进行插入
        int i = courseDao.saveCourseSalesInfo(course);
        if (i > 0) {
            //保存成功
            String result = SUCCESS.toString();
            return result;

        } else {
            //保存失败
            String result = FAIL.toString();
            return result;
        }
    }

    @Override
    public Course findCourseById(int id) {
        return courseDao.findCourseById(id);
    }

    @Override
    public String updateCourseSalesInfo(Course course) {

        //调用dao
        int i = courseDao.updateCourseSalesInfo(course);

        //根据插入是否成功,封装对应信息

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
    public Map<String, Integer> updateCourseStatus(Course course) {

        //调用dao
        int row = courseDao.updateCourseStatus(course);

        Map<String, Integer> map = new HashMap<>();

        if (row > 0) {

            if (course.getStatus() == 0) {
                map.put("status", 0);
            } else {
                map.put("status", 1);
            }
        }

        return map;
    }
}