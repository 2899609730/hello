package com.fox.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fox.pojo.Course;
import com.fox.pojo.Course_Section;
import com.fox.service.CourseContentService;
import com.fox.service.impl.CourseContentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/courseContent")
public class CourseContentServlet extends BaseServlet {
    public void findSectionAndLessonByCourseId(HttpServletRequest request,
                                               HttpServletResponse response) {
        try {
            String course_id = request.getParameter("course_id");

            //2.业务处理
            CourseContentService contentService = new
                    CourseContentServiceImpl();
            List<Course_Section> sectionList =
                    contentService.findSectionAndLessonByCourseId(Integer.parseInt(course_id));
            //3.返回结果
            String result = JSON.toJSONString(sectionList);
            response.getWriter().println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findCourseById(HttpServletRequest request, HttpServletResponse
            response) {
        try {
            String courseId = request.getParameter("course_id");
            CourseContentService contentService = new CourseContentServiceImpl();
            Course course = contentService.findCourseById(Integer.parseInt(courseId));
            //3.返回数据,将对象转换为JSON,只转换需要的字段
            SimplePropertyPreFilter filter = new
                    SimplePropertyPreFilter(Course.class, "id", "course_name");
            String result = JSON.toJSONString(course, filter);
            response.getWriter().println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存&修改 章节信息
     */
    public void saveOrUpdateSection(HttpServletRequest request
            , HttpServletResponse response) {
        try {
            //1.获取参数 从域对象中获取
            Map<String, Object> map = (Map) request.getAttribute("map");
            //2.创建Course_Section
            Course_Section section = new Course_Section();
            //3.使用BeanUtils工具类,将map中的数据封装到 section
            BeanUtils.populate(section, map);
            //4.业务处理
            CourseContentService contentService = new
                    CourseContentServiceImpl();
            String result = null;
            if (section.getId() != 0) {
                //修改操作
                result = contentService.updateSection(section);

            } else {
                //添加操作
                result = contentService.saveSection(section);
            }
            response.getWriter().println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}