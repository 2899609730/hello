<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
    表单提交必须是POST ,
    表单的enctype属性:必须设置为   multipart/form-data.
    input的type类型必须指定为: file, 一定要有name属性
    --%>
<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
    <input type="file" name="upload">
    <br>
    <input type="text" name="name">
    <input type="text" name="password">
    <input type="submit" value="文件上传">
</form>
</body>
</html>