<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	Object fail = request.getAttribute("fail");
%>
   <h1 align="center" style=" !important;inherit;italic;margin: px;color:blue;">  导入失败！</h1>
<br><br><br>
          <%=fail+""%>

</body>
</html>