package com.fox.web.servlet;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import static java.util.Objects.nonNull;

public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String methodName = req.getParameter("methodName");
        String methodName = null;
        //2.获取POST请求的 Content-Type类型
        String contentType = req.getHeader("Content-Type");

        //3.判断传递的数据是不是JSON格式
        if ("application/json;charset=utf-8".equals(contentType)) {
            //是JOSN格式 调用getPostJSON
            String postJSON = getPostJSON(req);
            //将JSON格式的字符串转化为map
            Map<String, Object> map = JSON.parseObject(postJSON, Map.class);
            //从map集合中获取 methodName
            methodName = (String) map.get("methodName");
            //将获取到的数据,保存到request域对象中
            req.setAttribute("map", map);
        } else {
            methodName = req.getParameter("methodName");
        }

        if (nonNull(methodName)) {
            Class servletClass = this.getClass();
            try {
                Method method = servletClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
                method.invoke(this, req, resp);
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

    /**
     * POST请求格式为 application/json;charset=utf-8
     * 在这个方法中我们使用流的方式,获取到POST请求的数据
     */
    public String getPostJSON(HttpServletRequest request) {
        try {
            //1.从request中获取 字符缓冲输入流对象
            BufferedReader reader = request.getReader();
            //2.创建 StringBuffer,用来保存读取出的数据
            StringBuffer sb = new StringBuffer();
            //3.循环读取
            String line = null;
            while ((line = reader.readLine()) != null) {
                //追加到 StringBuffer中
                sb.append(line);
            }
            //4.将读取到的内容转换为字符串,并返回
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

