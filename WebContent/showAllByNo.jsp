<%@page import="org.lanqiao.entity.Student"%>
<%@page import="java.util.List"%>
<%@page import="org.lanqiao.entity.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息</title>
<style type="text/css">

body {
	background-color: #ADD8E6;
}

</style>

</head>
<body>
<div>
<img alt="picture" src="images/top.png" width="1350px" height="100px" ></div>
<%
Page pages=(Page)request.getAttribute("pages");
int totalpages=pages.getTotalPage();
int curpage=pages.getCurrentPage();
List<Student> stus=pages.getStudents();

%><center>
<h1>学生信息表</h1>

<table border="1" width="50%">

<tr>

<th>学号</th>
<th>姓名</th>
<th>年龄</th>
<th>年级</th>
<th>删除</th>
<th>修改</th>

</tr>
<%
if(stus !=null){
	for(int i=0;i<stus.size();i++){
		out.print("<tr>");
		out.print("<td>"+stus.get(i).getStudentNo()+"</td>");
		out.print("<td>"+stus.get(i).getStudentName()+"</td>");
		out.print("<td>"+stus.get(i).getStudentAge()+"</td>");
		out.print("<td>"+stus.get(i).getGradeName()+"</td>");
		out.print("<td><a href='DelByStuNoServlet?stuNo="+stus.get(i).getStudentNo()+"'>删除</a></td>");
		out.print("<td><a href='QueryStudentByNoServlet?stuNo="+stus.get(i).getStudentNo()+"'>修改</a></td>");
		out.print("</tr>");
	}
}



%>

</table>
<br>
<br>
当前页数：[<%=curpage %>/<%=totalpages %>]


<%
if(curpage >1){%>

<a href="pageStudentServlet?currentPage=1">首页</a>
<a href="pageStudentServlet?currentPage=<%=curpage-1%>">上一页</a>

<%
}


if(curpage <totalpages){%>
<a href="pageStudentServlet?currentPage=<%=curpage+1%>">下一页</a>
<a href="pageStudentServlet?currentPage=<%=totalpages%>">尾页</a> 


<% 
}
%>

<a href="addStudent.jsp">增加</a><br>

<form action="pageStudentServlet" method="get">
<input type="text" value="<%=curpage %>" name="currentPage" style="width: 30px">
<input type="submit" value="跳转">
</form>
</center>

	



</body>
</html>