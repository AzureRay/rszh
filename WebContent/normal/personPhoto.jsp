<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.*"%>
<%@page import="java.util.*" import="bean.CommentObject"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action=" " method="post">
		<button type="submit" class="btn btn-success" >添加照片</button>
		
	<% request.setCharacterEncoding("utf-8");
		Object id = request.getParameter("id");
	     session.setAttribute("id", id);
		System.out.println("^^^^^获取到的id:" + id);
		Activity2Dao atd = new Activity2Dao();
		TreeMap<String, Object> Personmap = new TreeMap();
		Personmap.put("tableName", "personal");
		Personmap.put("num", id);
		Personmap.put("rowName", "*");
		Personmap.put("tiaojian", "id");
		System.out.println("Personmap: " + Personmap);
		//得到main_table中personal的列
		List<CommentObject> Valueslist = atd.getRowListValue(Personmap);
		String photo = (String) Valueslist.get(0).getValues().get("照片");
		System.out.println("Valueslist"+Valueslist);
		System.out.println("photo"+photo);
		if(photo.equals("0")){
			%>
			<h1>您当前的照片</h1>
			<img src="/images/1cun.jpg" width="160px" height="200px" />
			<%
		}
		else{
			String filePath="/photo/"+photo;%>
			<img src="<%=filePath%>" width="160px" height="200px" /><%
		}
		%>
		
		<%
	%>
	</form> 
</body>
</html>