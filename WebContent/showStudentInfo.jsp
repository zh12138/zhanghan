<%@page import="org.lanqiao.entity.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<div>
<img alt="picture" src="images/top.png" width="1350px" height="100px" ></div>
<%
Student stu=(Student)request.getAttribute("stu");
if(stu !=null){

%>
<form action="UpdateStudentServlet" method="post">
学号：<input type=" text" name="sno" readonly="readonly" value="<%=stu.getStudentNo()%>"><br>
姓名：<input type="text" name="sname" value="<%=stu.getStudentName()%>"><br>
年龄：<input type=" text" name="sage" value="<%=stu.getStudentAge()%>"><br>
年级：<input type=" text" name="gname" value="<%=stu.getGradeName()%>"><br>
<input type="submit" value="修改">


</form>
<%
}

%>




</body>
</html>