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
   $(function(){
	   $("#add").click(function(){
		   window.location.href="#";
	   });
	   $("#del").click(function(){
		   var val = $("input:checked").val();
		   window.location.href="#";
	   });
   });
		
</script>
</head>
<body>

	<%
		List<String> select = (List<String>)request.getAttribute("select");
	%>

	<table class="table table-bordered table-hover definewidth m10" id="mainTable">
    <thead>
    <tr>
        <th>序号</th>
		<th>选择列</th>
		<th>操作</th>
    </tr>
    </thead>
	     <%
			for(int i = 0;i < select.size();i++){
				String name = select.get(i);
				%>
					<tr id="<%= name %>" name="<%= name %>">
						<td><%= i+1 %></td>
						<td><%= name %></td>
						<td><a href="#">查看选择项</a>&nbsp;&nbsp;
						</td>
					</tr>
				<%
			}
		%>
	</table>
</body>
</html>