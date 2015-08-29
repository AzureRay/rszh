<%@page import="bean.CommentObject"%>
<%@page import="dao.DepartDao"%>
<%@page import="dao.ActivityTableDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/jquery.sorted.js"></script>
    <script type="text/javascript" src="/js/bootstrap.js"></script>
    <script type="text/javascript" src="/js/ckform.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
</head>
<body>
	<%
	//获得字段
	request.setCharacterEncoding("utf-8");
	String rowName = request.getParameter("rowName");
	%>
	<form action="/modValueRecordGJCX" method="post">
	<center>
		<table style="margin-top:200px;">
		<tr>
		<%
		//判断该字段是否是选择列
		ActivityTableDao atd = new ActivityTableDao();
		if(atd.isSelectRow(rowName)){
			List<String> rowValues = atd.getSelectRowValueList(rowName);
			%>
			<td><%= rowName %></td>
			<select name="<%= rowName %>">
			<%
			for(int i =0;i<rowValues.size();i++){
				%><option value="<%= rowValues.get(i)%>"><%= rowValues.get(i)%></option><%
			}
			%>
			</select>
			<%
		}else if(rowName.equals("部门")){
			DepartDao dd = new DepartDao();
			List<CommentObject> departList = dd.selectDepart();
			%>
			<td><%= rowName %></td>
			<select name="<%= rowName %>">
			<%
			for(int i =0;i<departList.size();i++){
				String dep = departList.get(i).getValues().get("depart")+"";
				%><option value="<%= dep %>"><%= dep %></option><%
			}
			%>
			</select>
			<%
		}else{
			%><td><%= rowName %></td>
			<td><input type="text" name="<%= rowName %>" value=""></td>
			<%
		}
		%>
		</tr>
		<tr>
		<td>&nbsp;</td>
		<td style="text-align:center;">
		    <input type="submit" value="确定" class="btn btn-success">
		&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重置" class="btn btn-success"></td>
		</tr>
		</table>
	</center>
	</form>
</body>
</html>