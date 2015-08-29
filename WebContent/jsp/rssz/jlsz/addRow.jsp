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
			var tableName = $("#tableName").val();
			var current = $("#current").val();
			window.location.href="/showDetialsServlet?tableName="+tableName+"&current="+current;
		 });
    });
</script>
</head>
<body>
	<%
		List<String> sjlx = (List<String>)request.getAttribute("sjlx");
		String tableName = request.getParameter("tableName");
		Object current = session.getAttribute("daszCkxqCurrent");
	%>
	<div style="display:none">
	<form>
		<input type="hidden" id="current" value="<%= current %>">
	</form>
	</div>
	<h4>您现在的位置是：记录设置-<%= tableName %>-添加列</h4>
<center>
	<form action="/jlszAddRow" method="post" class="definewidth m20">
	<table class="table table-bordered table-hover definewidth m10">
	<input type="hidden" name="tableName" id="tableName" value="<%= tableName %>">
    <tr>
        <td width="10%" class="tableleft">列名</td>
        <td><input type="text" name="row_name"/></td>
    </tr>
    <tr>
    	<td class="tableleft">数据类型</td>
        <td>
        	<select name="row_type">
			<%
				for(int i = 0;i < sjlx.size();i++){
					%><option value="<%= sjlx.get(i)%>"><%= sjlx.get(i)%></optiopn><%
				}
			%>
			</select>
        </td>
    </tr>
    <tr>
        <td class="tableleft">备注</td>
        <td><input type="text" name="comment"/></td>
    </tr>
   
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button" id="save">保存</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
	</table>
	</form>
</center>
</body>
</html>