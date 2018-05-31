<%@page import="org.lanqiao.entity.Student"%>
<%@page import="org.lanqiao.Dao.StudentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
background-color: #efefef;
}

</style>
</head>
<body>
<h2>增加成功</h2>
<% Student stu=(Student)request.getAttribute("stu");
%>
学号：<%=stu.getStudentNo()%>
姓名：<%=stu.getStudentName() %>
年龄：<%=stu.getStudentAge() %>
年级：<%=stu.getGradeName() %><br>
<a href="pageStudentServlet">返回首页</a>

</body>
</html>