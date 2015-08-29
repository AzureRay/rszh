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
		   var len = $("input:checked").length;
		   if(len ==0){
			   alert("请选择列");
			   return false;
		   }else if(len > 1){
			   alert("只能选择一列");
			   return false;
		   }else{
			   var val = $("input:checked").val();
			   window.location.href="#";
		   }
	   });
   });
		
</script>
</head>
<body>

	<%
		List<String> rylbList = (List<String>)request.getAttribute("rylb");
		System.out.print("人员类别："+rylbList);
	%>

	<form class="form-inline definewidth m20" id="table_tool" action="" method="post">    
   		 <input type="hidden" name="" value="">
		 <input type="hidden" name="" value="">
   		 <button type="button" class="btn btn-success" id="add">添加</button>
   		 <button type="button" class="btn btn-success" id="del">删除</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10" id="mainTable">
    <thead>
    <tr>
        <th>序号</th>
		<th id="checkCol"><input type="checkbox" name="check" />
		<th>人员类别</th>
    </tr>
    </thead>
	     <%
			for(int i = 0;i < rylbList.size();i++){
				String name = rylbList.get(i);
				%>
					<tr id="<%= name %>" name="<%= name %>">
						<td><%= i+1 %></td>
						<td><input type="checkbox" value="<%= name %>" /></td>
						<td><%= name %></td>
					</tr>
				<%
			}
		%>
	</table>
</body>
</html>