<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="bean.CommentObject"
	import="dao.*"%>
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
<script type="text/javascript">
$(function () {       
	$('#backid').click(function(){
		var tableName = $("#tableName").val();
		window.location.href="/jlszServletRequest?tableName="+tableName;
	 });
});
</script>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String tableName = request.getParameter("tableName");
		String id = request.getParameter("id");
		ActivityTableDao atDao = new ActivityTableDao();
		List<CommentObject> rowNames = atDao.getRowNameList(tableName);
		for(int i = 0; i < rowNames.size();i++){
			String nameString = rowNames.get(i).getValues().get("row_name")+"";
			if(nameString.equals("id")){
				rowNames.remove(i);
				break;
			}
		}
		for(int i = 0; i < rowNames.size();i++){
			String nameString = rowNames.get(i).getValues().get("row_name")+"";
			if(nameString.equals("编号")){
				rowNames.remove(i);
				break;
			}
		}
	%>
<center>
	<form action="/updateJl" method="post" class="definewidth m20">
	<table class="table table-bordered table-hover definewidth m10">
	<input type="hidden" name="tableName" id="tableName" value="<%= tableName %>"/>
	<input type="hidden" name="id" value="<%= id %>">
	<%
	ActivityTableDao atd = new ActivityTableDao();
	List<CommentObject> list = atd.getListWithWhere(request);
	CommentObject com = list.get(0);
	for(int i= 0;i<rowNames.size();i++){
		String rowName = rowNames.get(i).getValues().get("row_name")+"";
		%>
		<tr>
        <td width="10%" class="tableleft"><%= rowName %></td>
        <%
        String rowName1 = com.getValues().get(rowName)+"";
        if(rowName1.equals("null")){
        	%><td><input type="text" name="<%= rowName %>" value=""/></td><%
        }else{
        	%><td><input type="text" name="<%= rowName %>" value="<%=rowName1%>"/></td><%
        }
        %>
    	</tr>
		<%
	}
	%>
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