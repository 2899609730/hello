package com.fox.dao.impl;

import com.fox.dao.CourseDao;
import com.fox.pojo.Course;
import com.fox.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    @Override
    public List<Course> findCourseList() {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "SELECT id,course_name,price,sort_num,STATUS FROM course where is_del =  ?";
        try {
            List<Course> courseList = queryRunner.query(sql, new BeanListHandler<Course>(Course.class), 0);
            return courseList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
