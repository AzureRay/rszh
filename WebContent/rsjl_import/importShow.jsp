<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>导入</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/importShow.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function() {
		$("#mainTable tr:even").css("background", "#F3F3F3");
	});
</script>
</head>
<body>
	<%
	 	//获得传过来的值
        request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		List<String> columnName = (List<String>) request
				.getAttribute("columnName");
		List<Vector> data = (List<Vector>) request.getAttribute("data");
		 session.setAttribute("columnName",columnName);
		 session.setAttribute("data",data);
	%>
	
	   <form  action="/importgoServlet1"  method="post">
	    <input  type="submit"   value="Import"/>
	    </form> 
	
</body>
</html>