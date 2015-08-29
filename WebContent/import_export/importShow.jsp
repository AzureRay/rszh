<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>导入</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<%
	 	//获得传过来的值
        request.setCharacterEncoding("utf-8");
		String flag = request.getParameter("flag");
		List<String> columnName = (List<String>) request
				.getAttribute("columnName");
		System.out.println("1"+columnName);
		List<Vector> data = (List<Vector>) request.getAttribute("data");
		System.out.println("2"+data);

		 session.setAttribute("columnName",columnName);
		 session.setAttribute("data",data);
			System.out.println("3"+session.getAttribute("columnName"));
			System.out.println("5"+session.getAttribute("data"));

	%>
	
	   <form  action="/importgoServlet"  method="post">
	    <input  type="submit"   value="Import"/>
	    </form> 
	    
	 	
</body>
</html>