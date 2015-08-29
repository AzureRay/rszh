<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="dao.MainTableDao"%>
    <%@page import="bean.CommentObject"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	//获取数据库中德表
	MainTableDao mtd = new MainTableDao();
	List<CommentObject> tableNames = mtd.getTableNameList();
%>
<center>
<form action="/notice/notice.jsp" method="post">
<table style="margin-top:200px;">
<tr>
<td>请选择表：</td>
<td><select name="tableName">
<%
			for(int i = 0;i < tableNames.size();i++){
				String name = tableNames.get(i).getValues().get("table_name")+"";
				%><option value="<%=name%>"><%=name%></option><%
			}
			%>
</select></td>
			
</tr>
<tr style="text-align:center;">
<td colspan="2">
	<input type="submit" name="tijiao" value="确定" class="btn btn-success">
</td>
</tr>
</table>
</form>
</center>
</body>
</html>