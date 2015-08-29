<%@page import="dao.Fenye"%>
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
		$('#add').click(function(){
			window.location.href="addNewRowRequest";
		 });
		$("#del").click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
				return false;
			}else if(len >= 2){
				alert("只能选择一列");
				return false;
			}else if( len == 1){
				var val = $("input:checked").val();
				window.location.href="daszDelRow?tableName=personal&rowName="+val;
			}
		});
		$("#mod").click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
				return false;
			}else if(len >= 2){
				alert("只能选择一列");
				return false;
			}else if( len == 1){
				var val = $("input:checked").val();
				var rowName = $("#"+val+" td[name='rowName']").text();
				var rowType = $("#"+val+" td[name='rowType']").text();
				var comment = $("#"+val+" td[name='comment']").text();
				window.location.href="modRowRequest?tableName=personal&rowName="+val+"&rowType="+rowType+"&comment="+comment;
			}
		});
		$("#up").click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
				return false;
			}else if(len >= 2){
				alert("只能选择一列");
				return false;
			}else if( len == 1){
				var val = $("input:checked").val();
				var rowName = $("#"+val+" td[name='rowName']").text();
				$("#table_tool :input[name='rowName']").val(rowName);
				$("#table_tool :input[name='action']").val("up");
			}
		});
		
		$("#down").click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
				return false;
			}else if(len >= 2){
				alert("只能选择一列");
				return false;
			}else if( len == 1){
				var val = $("input:checked").val();
				var rowName = $("#"+val+" td[name='rowName']").text();
				$("#table_tool :input[name='rowName']").val(rowName);
				$("#table_tool :input[name='action']").val("down");
			}
		});
    });
</script>
</head>
<body>

	<%
		List<CommentObject> list = (List<CommentObject>)request.getAttribute("list");
		System.out.print("personal："+list);
		List<String> sjlx = (List<String>)request.getAttribute("sjlx");
		session.setAttribute("daszCurrent", request.getParameter("current"));
	%>
	<form class="form-inline definewidth m20" id="table_tool" action="upAndDown?tableName=personal" method="post">    
   		 <input type="hidden" name="rowName" value="">
		 <input type="hidden" name="action" value="">
   		 <button type="button" class="btn btn-success" id="add">添加</button>
   		 <button type="button" class="btn btn-success" id="mod">修改</button>
   		 <button type="button" class="btn btn-success" id="del">删除</button>
   		 <input type="submit" class="btn btn-success" value="上移"  id="up" />
   		 <input type="submit" class="btn btn-success" value="下移" id="down" />
	</form>
	<table class="table table-bordered table-hover definewidth m10" id="mainTable">
    <thead>
    <tr>
        <th>序号</th>
		<th id="checkCol"><input type="checkbox" name="check" />
		<th>列名</th>
		<th>数据类型</th>
		<th>备注</th>
    </tr>
    </thead>
	     <%
			for (int i = 0; i < list.size(); i++) {
				String rowName = (String)list.get(i).getValues().get("row_name");
				String rowType = (String)list.get(i).getValues().get("row_type");
				String comment = (String)list.get(i).getValues().get("comment");
				%>
		<tr id="<%=rowName%>">
			<th><%= i +1 %></th>
			<td><input type="checkbox" value="<%=rowName%>"/></td>
			<td name="rowName"><%=rowName%></td>
			<td name="rowType"><%=rowType%></td>
			<td name="comment"><%
				if(comment == null|| comment ==""){
					%><%
				}else{
					%><%=comment%><%
				}
			%></td>
		</tr>
		<%
		}
	%>
	</table>
	<%
		int pageCount = Integer.parseInt(request.getParameter("pageCount")+"");
		System.out.println("^^总页数："+pageCount);
		//获得当前页的页码
		int current = Integer.parseInt(request.getParameter("current"));
	%>
	<div class="inline pull-right page">
      	   当前&nbsp;<%= current %>/<%= pageCount %>&nbsp;页 
      	 &nbsp;<a href='daszServlet?tableName=personal&current=1'>第一页</a>
      	 <%
      	 if(current > 1){
      		 %> &nbsp;<a href='daszServlet?tableName=personal&current=<%= current - 1%>'>上一页</a><%
      	 }
      	 %>
      	 <%
      	 if(current < pageCount){
      		 %> &nbsp;<a href='daszServlet?tableName=personal&current=<%= current + 1%>'>下一页</a><%
      	 }
      	 %>
         &nbsp;<a href='daszServlet?tableName=personal&current=<%= pageCount %>' >最后一页</a> 
         <form action="daszServlet?tableName=personal" method="post">
         	跳转到：<select name="current">
         	<%
         	for(int i =1;i < pageCount+1;i++){
         		%><option value='<%=i %>'><%=i %></option><%
         	}
         	%>
         	</select>
         	<input type="submit" value="跳转">
         </form>
    </div>
</body>
</html>