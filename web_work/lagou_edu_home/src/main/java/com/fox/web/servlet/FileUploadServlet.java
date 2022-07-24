package com.fox.web.servlet;

import com.fox.utils.UUIDUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.util.Objects.nonNull;

@WebServlet("/upload")
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setHeaderEncoding("utf-8");
        boolean multipartContent = ServletFileUpload.isMultipartContent(req);
        if (multipartContent) {
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                if (nonNull(list)) {
                    for (FileItem fileItem : list) {
                        boolean formField = fileItem.isFormField();
                        if (formField) {
                            //普通表单项, 当 enctype="multipart/form-data"时, request的getParameter()方法 无法获取参数
                            String fieldName = fileItem.getFieldName();
                            String value = fileItem.getString("utf-8");//设置编码
                            System.out.println(fieldName + "=" + value);
                        } else {
                            //文件名
                            String fileName = fileItem.getName();

                            //避免图片名重复 拼接UUID
                            String newFileName = UUIDUtils.getUUID() + "_" + fileName;
                            //获取输入流
                            InputStream inputStream = fileItem.getInputStream();
                            String realPath = this.getServletContext().getRealPath("/");
                            //创建输出流 输出到H盘
                            FileOutputStream outputStream = new FileOutputStream("C:/Users/Mr.Fox/Desktop/upload/" + newFileName);
                            IOUtils.copy(inputStream, outputStream);
                            outputStream.close();
                            inputStream.close();
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
