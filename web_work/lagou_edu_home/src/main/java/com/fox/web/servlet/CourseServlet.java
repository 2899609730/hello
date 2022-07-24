package com.fox.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fox.pojo.Course;
import com.fox.service.CourseService;
import com.fox.service.impl.CourseServiceImpl;
import com.fox.utils.DateUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "courseServlet", value = "/course")
public class CourseServlet extends BaseServlet {
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

    //根据条件查询课程信息
    public void findByCourseNameOrStatus(HttpServletRequest request, HttpServletResponse response) {


        //1.接收参数
        String courseName = request.getParameter("course_name");
        String status = request.getParameter("status");

        //2.业务处理
        CourseService cs = new CourseServiceImpl();
        List<Course> courseList = cs.findByCourseNameAndStatus(courseName, status);

        //3.返回结果 响应JSON格式数据
        //使用 SimplePropertyPreFilter,指定要转换为JSON的字段
        SimplePropertyPreFilter filter =
                new SimplePropertyPreFilter(Course.class, "id", "course_name", "price", "sort_num", "status");

        String result = JSON.toJSONString(courseList, filter);
        try {
            response.getWriter().println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据课程ID查询课程营销信息
     */
    public void findCourseById(HttpServletRequest request, HttpServletResponse response) {

        try {
            //1.接收参数
            String id = request.getParameter("id");

            //2.业务处理
            CourseService cs = new CourseServiceImpl();
            Course course = cs.findCourseById(Integer.parseInt(id));

            //3.返回结果 响应JSON格式数据
            //使用 SimplePropertyPreFilter,指定要转换为JSON的字段
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Course.class, "id", "course_name", "brief", "teacher_name",
                    "teacher_info", "preview_first_field", "preview_second_field", "discounts", "price", "price_tag", "share_image_title", "share_title", "share_description", "course_description");

            String result = JSON.toJSONString(course, filter);
            response.getWriter().println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCourseStatus(HttpServletRequest request, HttpServletResponse response) {

        try {
            //1.获取参数
            String id = request.getParameter("id");

            //2.业务处理
            CourseService cs = new CourseServiceImpl();

            //3.根据课程id 查询课程信息
            Course course = cs.findCourseById(Integer.parseInt(id));

            //4.判断课程信息状态,进行取反设置
            int status = course.getStatus();
            if (status == 0) {
                //如果是0 设置为1
                course.setStatus(1);
            } else {
                course.setStatus(0);
            }

            //5.设置更新时间
            course.setUpdate_time(DateUtils.getDateFormart());

            //6.修改状态
            Map<String, Integer> map = cs.updateCourseStatus(course);

            //7.响应结果
            String result = JSON.toJSONString(map);

            response.getWriter().print(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
