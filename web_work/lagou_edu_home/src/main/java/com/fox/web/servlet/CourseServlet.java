package com.fox.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fox.pojo.Course;
import com.fox.service.CourseService;
import com.fox.service.impl.CourseServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "courseServlet", value = "/course")
public class CourseServlet extends BaseServlet{
    //查询课程信息列表
    public void findCourseList(HttpServletRequest request, HttpServletResponse response) {

        try {
            //1.接收参数

            //2.业务处理
            CourseService cs = new CourseServiceImpl();
            List<Course> courseList = cs.findCourseList();

            //3.响应结果
            //SimplePropertyPreFilter 指定要转换的JSON字段
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class,
                    "id", "course_name", "price", "sort_num", "status");

            String result = JSON.toJSONString(courseList, filter);
            response.getWriter().print(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
