<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
 <br>
 
<br> <br> <br> <br> <br> <br>
   <h1 align="center" style=" !important;inherit;italic;margin: px;color:blue;">  导入成功！</h1>
<br><br><br>

<%String id=session.getAttribute("id")+""; 
if(id.equals("1")){ %>
<a href="/showPersonal?current=1" align="left">人员导入后公司人员信息</a>
<%}else {%>
<a href="/jlszServletRequest?current=1" align="left">人事记录导入后全部人事记录信息</a>
<%} %>
</body>
</html>