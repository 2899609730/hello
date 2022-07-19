package com.fox.dao.impl;

import com.fox.dao.CourseDao;
import com.fox.pojo.Course;
import com.fox.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    @Override
    public List<Course> findCourseList() {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "SELECT id,course_name,price,sort_num,STATUS FROM course where is_del =  ?";
        try {
            List<Course> courseList = queryRunner.query(sql, new BeanListHandler<>(Course.class), 0);
            return courseList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course> findByCourseNameAndStatus(String courseName, String status) {
        try {
            //1.创建QueryRunner
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            //2.编写SQL 当前的查询为多条件不定项查询
            //2.1 创建StringBuffer 对象,将SQL字符串 添加进缓冲区
            StringBuffer sb = new StringBuffer("SELECT id,course_name,price,sort_num,STATUS FROM course WHERE is_del = ? ");
            //2.2 创建list集合 保存参数
            List<Object> list = new ArrayList<>();
            list.add(0);

            //2.3 判断传入的参数是否为空
            if (courseName != null && courseName != "") {
                sb.append(" AND course_name LIKE ?");
                //like查询 需要拼接 %
                courseName = "%" + courseName + "%";
                //将条件放进list集合
                list.add(courseName);
            }

            if (status != null && status != "") {
                sb.append("AND STATUS = ?");
                //将status 转换为 int
                int i = Integer.parseInt(status);
                list.add(i);
            }

            //执行查询
            List<Course> courseList = qr.query(sb.toString(), new BeanListHandler<Course>(Course.class), list.toArray());
            //返回结果
            return courseList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int saveCourseSalesInfo(Course course) {

        try {
            //1.创建QueryRunner
            QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

            //2.编写SQL
            String sql = "INSERT INTO course(\n" +
                    "course_name,\n" +
                    "brief,\n" +
                    "teacher_name,\n" +
                    "teacher_info,\n" +
                    "preview_first_field,\n" +
                    "preview_second_field,\n" +
                    "discounts,\n" +
                    "price,\n" +
                    "price_tag,\n" +
                    "share_image_title,\n" +
                    "share_title,\n" +
                    "share_description,\n" +
                    "course_description,\n" +
                    "course_img_url,\n" +
                    "STATUS,\n" +
                    "create_time,\n" +
                    "update_time\n" +
                    ")VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            //3.准备参数
            Object[] param = {course.getCourse_name(), course.getBrief(), course.getTeacher_name(), course.getTeacher_info(),
                    course.getPreview_first_field(), course.getPreview_second_field(), course.getDiscounts(), course.getPrice(),
                    course.getPrice_tag(), course.getShare_image_title(), course.getShare_title(), course.getShare_description(),
                    course.getCourse_description(), course.getCourse_img_url(), course.getStatus(), course.getCreate_time(), course.getUpdate_time()};

            //4.执行插入操作
            int row = qr.update(sql, param);

            return row;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
