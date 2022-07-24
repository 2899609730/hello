package com.fox.dao.impl;

import com.fox.dao.CourseContentDao;
import com.fox.pojo.Course;
import com.fox.pojo.Course_Lesson;
import com.fox.pojo.Course_Section;
import com.fox.utils.DateUtils;
import com.fox.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CourseContentDaoImpl implements CourseContentDao {
    @Override
    public List<Course_Section> findSectionAndLessonByCourseId(int courseId) {
        try {
            //1.创建QueryRunner
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            //2.编写SQL
            String sql = "SELECT \n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_name,\n" +
                    "description,\n" +
                    "order_num,\n" +
                    "STATUS\n" +
                    "FROM course_section WHERE course_id = ?";
            //3.执行查询
            List<Course_Section> sectionList = qr.query(sql, new
                    BeanListHandler<Course_Section>(Course_Section.class), courseId);

            //4.根据章节ID查询课时信息
            for (Course_Section section : sectionList) {
                //调用方法 获取章节对应的课时
                List<Course_Lesson> lessonList =
                        findLessonBySectionId(section.getId());
                //将课时数据封装到 章节对象中
                section.setLessonList(lessonList);
            }
            return sectionList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course_Lesson> findLessonBySectionId(int sectionId) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            String sql = "SELECT \n" +
                    "id,\n" +
                    "course_id,\n" +
                    "section_id,\n" +
                    "theme,\n" +
                    "duration,\n" +
                    "is_free,\n" +
                    "order_num,\n" +
                    "STATUS\n" +
                    "FROM course_lesson WHERE section_id = ?";
            List<Course_Lesson> lessonList = qr.query(sql, new
                    BeanListHandler<Course_Lesson>(Course_Lesson.class), sectionId);
            return lessonList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Course findCourseByCourseId(int courseId) {
        try {
            //1.创建QueryRunner
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            //2.编写SQL
            String sql = "SELECT id,course_name FROM course WHERE id = ?";
            //3.执行查询
            Course course = qr.query(sql, new BeanHandler<Course>(Course.class),
                    courseId);
            //4.返回结果
            return course;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public int saveSection(Course_Section section) {
        try {
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            String sql = "INSERT INTO course_section(course_id,section_name,description,order_num,STATUS,create_time,u pdate_time)\n" + "VALUES(?,?,?,?,?,?,?);";
            Object[] param =
                    {section.getCourse_id(), section.getSection_name(), section.getDescription(),
                            section.getOrder_num(), section.getStatus(), section.getCreate_time(), section.getUpdate_time()};
            int i = qr.update(sql, section.getCourse_id(), param);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateSection(Course_Section section) {
        try {
            //1.创建QueryRunner
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            //2.编写SQL
            String sql = "UPDATE course_section SET\n" +
                    "section_name = ?,\n" +
                    "description = ?,\n" +
                    "order_num = ?,\n" +
                    "update_time = ? WHERE id = ?;";
            //3.准备参数
            Object[] param = {section.getSection_name(), section.getDescription(), section.getOrder_num(),
                    section.getUpdate_time(), section.getId()};
            //4.执行修改操作
            int row = qr.update(sql, param);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateSectionStatus(int id, int status) {
        try {
            //1.创建QueryRunner
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
            //2.编写SQL
            String sql = "UPDATE course_section SET STATUS = ?,update_time = ? WHERE id = ?;";
            //3.准备参数
            Object[] param = {status, DateUtils.getDateFormart(), id};
            //4.执行修改操作
            int row = qr.update(sql, param);
            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}