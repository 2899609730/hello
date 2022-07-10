package com.fox.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

import static java.util.Objects.nonNull;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName = req.getParameter("methodName");

        if (nonNull(methodName)){
            Class testServletClass = this.getClass();
            try {
                Method method = testServletClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                method.invoke(this,req,resp);
            } catch (Exception e) {
                System.out.println("请求的功能不存在! !");
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
