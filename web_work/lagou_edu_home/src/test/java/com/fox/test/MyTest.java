package com.fox.test;

import com.fox.dao.CourseDao;
import com.fox.dao.impl.CourseDaoImpl;
import com.fox.pojo.Course;
import com.fox.service.impl.CourseServiceImpl;
import org.junit.Test;

import java.util.List;

public class MyTest {
    CourseDao courseDao = new CourseDaoImpl();
    @Test
    public void test(){
        List<Course> courses = courseDao.findByCourseNameAndStatus("微服务", null);
        for (Course cours : courses) {
            System.out.println(cours.getId());
        }
    }
}
