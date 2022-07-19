package com.fox.test;

import com.fox.dao.CourseDao;
import com.fox.dao.impl.CourseDaoImpl;
import com.fox.pojo.Course;
import com.fox.utils.DateUtils;
import org.junit.Test;

import java.util.List;

public class MyTest {
    CourseDao courseDao = new CourseDaoImpl();

    @Test
    public void test() {
        List<Course> courses = courseDao.findByCourseNameAndStatus("微服务", null);
        for (Course cours : courses) {
            System.out.println(cours.getId());
        }
    }

    //测试保存课程营销信息
    @Test
    public void testSaveCourseSalesInfo() {

        //1.创建course对象
        Course course = new Course();
        course.setCourse_name("爱情36计");
        course.setBrief("学会去找对象");
        course.setTeacher_name("药水哥");
        course.setTeacher_info("人人都是药水哥");
        course.setPreview_first_field("共10讲");
        course.setPreview_second_field("每周日更新");
        course.setDiscounts(88.88);
        course.setPrice(188.0);
        course.setPrice_tag("最新优惠价");
        course.setShare_image_title("哈哈哈");
        course.setShare_title("嘻嘻嘻");
        course.setShare_description("天天向上");
        course.setCourse_description("爱情36计,就像一场游戏");
        course.setCourse_img_url("https://www.xx.com/xxx.jpg");
        course.setStatus(1); //1 上架 ,0 下架
        String formart = DateUtils.getDateFormart();
        course.setCreate_time(formart);
        course.setUpdate_time(formart);

        int i = courseDao.saveCourseSalesInfo(course);
        System.out.println(i);
    }
}
