package com.fox.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

import static java.util.Objects.nonNull;

@WebServlet("/test")
public class TestServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    /**
     * 2.模块对应的功能部分
     */
    public void addCourse(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("新建课程");
    }

    public void findByStatus(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("根据状态查询");
    }

    public void findByName(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("根据课程名称查询");
    }
}
