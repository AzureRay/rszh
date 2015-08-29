<%@page import="dao.ActivityTableDao"%>
<%@page import="dao.DepartDao"%>
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
				window.location.href="/rsbdServlet?tableName=personal";
		 });
    });
</script>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		List<CommentObject> list = atd.getListWithWhere(request);
		DepartDao dd = new DepartDao();
	    List<CommentObject> departList = dd.selectDepart();
	%>
<center>
	<form action="/rowDao?tableName=personal" method="post" class="definewidth m20">
	<table class="table table-bordered table-hover definewidth m10">
	<input type="hidden" name="id" value="<%= list.get(0).getValues().get("id") %>" />
    <tr>
         		<td>部门：</td>
         		<td><select name="部门">
         		<%
        		 for(int i= 0;i<departList.size();i++){
        	 		String bumen = departList.get(i).getValues().get("depart")+"";
        	 	%>
        	 		<option value="<%= bumen%>"><%= bumen%></option>
        	 	<%
         		}
         		%>
         		</select></td>
         	</tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button">保存</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid">返回记录列表</button>
        </td>
    </tr>
	</table>
	</form>
</center>
</body>
</html>