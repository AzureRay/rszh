<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="bean.CommentObject" import="rsjl.*"%>
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
	$(":input[value='选取全部']").click(function(){
		$(":input[type='checkbox']").attr("checked",true);
	});
});
$(function(){
	$(":input[value='全部不选']").click(function(){
		$(":input[type='checkbox']").attr("checked",false);
	});
});
</script>
</head>
<body>
	<%
	String tableName = request.getParameter("tableName");
	List<CommentObject> list = (List<CommentObject>)request.getAttribute("list");
	%>
    <center>
    <form action="/daochuServlet_a"  method="post">
    <input style="diaplay:none" type="checkbox" checked="checked" name="tableName" value=<%=tableName%>>
    <table width="900px" style="font-size:16px;" class="table table-bordered table-hover definewidth m10">
    	 <tr>
        <!-- <th><input type = "checkbox">编号</th> -->
		<th><input type = "checkbox" >姓名</th>
		<th><input type = "checkbox">部门</th>
		
		<%for(int i=0;i<list.size();i++){
			String rowName = list.get(i).getValues().get("row_name")+"";
			if(rowName.equals("id")){
				list.remove(i);
				break;
			}
			}%>
			<%for(int i = 0; i<list.size();i++){ %>
		<th><input type = "checkbox" name =<%=list.get(i).getValues().get("row_name")%> value = <%=list.get(i).getValues().get("row_name") %>><%=list.get(i).getValues().get("row_name")%></th>
		<%}
		
%>
    </tr>
    </table>
    <input type="button" class="btn btn-success" value="选取全部">
	<input type="button" class="btn btn-success" value="全部不选">
	<input type="submit" class="btn btn-success" value="导出Excel">
    </form>
    </center>
</body>
</html>