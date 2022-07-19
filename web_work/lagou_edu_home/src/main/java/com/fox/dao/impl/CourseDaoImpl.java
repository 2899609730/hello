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

    /**
     * 根据课程名称,课程状态 查询课程信息
     *
     * @param courseName
     * @param status
     * @return
     */
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

}
