<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<%String information=request.getParameter("fail");
%>
<a href="/modpass/modPass.html?current=1" align="left">重新修改密码</a>
<br> <br> <br> <br> <br> 

<br>
<%if(information.equals("密码修改成功")){ %>
   <h1 align="center" style=" !important;inherit;italic;margin: px;color:blue;"><%=information %></h1>
<%}else {%>
   <h1 align="center" style=" !important;inherit;italic;margin: px;color:red;"><%=information %></h1>
<%} %>


</body>
</html>