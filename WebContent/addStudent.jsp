<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

<center>
<form action="AddStudentServlet" method="post" enctype="multipart/form-data">
学号：<input type="text" name="sno"><br>
姓名：<input type="text" name="sname"><br>
年龄：<input type="text" name="sage"><br>
年级：<input type="text" name="gname"><br>
上传图片：<input type="file" name="sPicture"><br>
<input type="submit" value="增加">


</form>
</center>
</body>
</html>