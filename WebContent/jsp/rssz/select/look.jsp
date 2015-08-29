<%@page import="dao.ActivityTableDao"%>
<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="bean.CommentObject"%>
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
    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }
        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>
    <script>
    $(function () {       
		$('#backid').click(function(){
			window.location.href="/selectServlet";
		 });
    });
</script>
</head>
<body>
<%
		request.setCharacterEncoding("utf-8");
		String rowName = request.getParameter("rowName");
		ActivityTableDao atd = new ActivityTableDao();
		List<String> values = atd.getSelectRowValueList(rowName);
		System.out.println(rowName);
		System.out.println(values);
	%>
<center>
	<form action="/selectAdd" method="post" class="definewidth m20">
	
	<table class="table table-bordered table-hover definewidth m10">
       <thead>
       <tr>
			<td>序号</td>
			<td>列名</td>
			<td>列值</td>
			<td>操作</td>
		</tr>
		</thead>
	<%
	for(int i =0;i < values.size();i++){
		%><tr>
			<td><%= i+1 %></td>
			<td><%= rowName %></td>
			<td><%= values.get(i) %></td>
			<td><a href="/delSelectRow?rowName=<%= rowName %>&rowValue=<%= values.get(i) %>">删除</a>&nbsp;&nbsp;</td>
		</tr><%
	}
	%>
    <tr>
        <td colspan="4">
        <center>
        	<a href="/jsp/rssz/select/add.jsp?rowName=<%= rowName %>" class="btn btn-primary">添加</a>
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </center>
        </td>
    </tr>
	</table>
	</form>
</center>
</body>
</html>