package com.fox.service.impl;

import com.fox.dao.CourseDao;
import com.fox.dao.impl.CourseDaoImpl;
import com.fox.pojo.Course;
import com.fox.service.CourseService;
import com.fox.utils.DateUtils;

import java.util.List;

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
}
