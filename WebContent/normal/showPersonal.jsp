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
<link rel="stylesheet" type="text/css"
	href="/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery.sorted.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/ckform.js"></script>
<script type="text/javascript" src="/js/common.js"></script>
<style type="text/css">
#mainTable th {
	min-width: 100px;
	white-space: nowrap;
}

#mainTable td {
	min-width: 100px;
	white-space: nowrap;
}
</style>
</head>
<script>
	$(function() {
		$("#mod").click(function() {
			var len = $("#mainTable input:checked").length;
			if (len == 0) {
				alert("请选择列！");
				return false;
			} else if (len >= 2) {
				alert("只能选择一列");
				return false;
			} else if (len == 1) {
				var val = $("input:checked").val();
				var currentPage = $("#currentPage").val();
				$("#id").val(val);
				window.location.href="#";
			}
		});
		$('#del').click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
				return false;
			}else if(len >= 1){
				var flag = confirm("确定要删除"+len+"条记录吗？");
				if(flag){
				}else{
					return false;
				}
			}
		 });
		$("#photo").click(function() {
			var len = $("#mainTable input:checked").length;
			if (len == 0) {
				alert("请选择列！");
				return false;
			} else if (len >= 2) {
				alert("只能选择一列");
				return false;
			} else if (len == 1) {
				var val = $("input:checked").val();
				var currentPage = $("#currentPage").val();
				$("#id").val(val);
				window.location.href="#";
			}
		});
	});
</script>
<body>
<%
	List<CommentObject> rownamelist = (List<CommentObject>) request
			.getAttribute("rowNames");
	System.out.println("rownamelist:" + rownamelist);
	List<CommentObject> rowValuelist = (List<CommentObject>) request
			.getAttribute("list");
	System.out.println("rowValuelist:" + rowValuelist);
%>
<%
	int pageCount = Integer.parseInt(request.getParameter("pageCount")+"");
	System.out.println("^^总页数："+pageCount);
	//获得当前页的页码
	int current = Integer.parseInt(request.getParameter("current"));
	//将当业10条记录的id获取，并且放入一个list中
	List<Object> ids = new ArrayList<Object>();
%>
	<div id="contentRight" style="width: 100%; height: 440px; font-size: 14px; margin-left: 0px; margin-bottom: 0px; border: 1px solid #DDDDDD; overflow: scroll;">
		<form action="" id="">
			<input type="hidden" name="personalCurrent" value="<%=current%>" id="currentPage"/>
			<a href="#" id="add" class="btn btn-success">添加</a>
			<button type="button" class="btn btn-success" id="mod">修改</button>
			<button type="button" class="btn btn-success" id="photo">查看照片</button>
			<table class="table table-bordered table-hover definewidth m10"
			id="mainTable">
			<tr>
				<th>序号</th>
				<th id="checkCol"><input type="checkbox" name="check" /> <%
				//移除id
				for(int i =0;i<rownamelist.size();i++){
					String rowName = (String) rownamelist.get(i).getValues()
	 						.get("row_name");
					if(rowName.equals("id")){
						rownamelist.remove(i);
						break;
					}
				}
 				for (int i = 0; i < rownamelist.size(); i++) {
 				String rowName = (String) rownamelist.get(i).getValues()
 						.get("row_name");
 				%><th><%=rowName%></th><%
				}%></tr>
				<%
				for (int i = 0; i < rowValuelist.size(); i++) {
					String id = rowValuelist.get(i).getValues().get("id") + "";
					ids.add(rowValuelist.get(i).getValues().get("id"));
			%>
			<tr>
				<td><%=i + 1%></td>
				<td><input type="checkbox" name="check+<%=id%>" value="<%=id%>" /></td>
				<%
					for (int j = 0; j < rownamelist.size(); j++) {
				%>
				<%
					String rowValue = ""+ rowValuelist.get(i).getValues().get(""+ rownamelist.get(j).getValues().get("row_name"));
					
				%>
				<td><%
				if(rowValue.equals("null")){
					%><%
				}else{
					%><%=rowValue%><%
				}
				%>
				</td>
				<%
					}
				%>
			</tr>
			<%
				}
				session.setAttribute("ids", ids);
			%>
			</table>
		</form>
	</div>
	<div class="inline pull-right page">
      	   当前&nbsp;<%= current %>/<%= pageCount %>&nbsp;页 
      	 &nbsp;<a href='/normalShowPersonal?current=1'>第一页</a>
      	 <%
      	 if(current > 1){
      		 %> &nbsp;<a href='/normalShowPersonal?current=<%= current - 1%>'>上一页</a><%
      	 }
      	 %>
      	 <%
      	 if(current < pageCount){
      		 %> &nbsp;<a href='/normalShowPersonal?current=<%= current + 1%>'>下一页</a><%
      	 }
      	 %>
         &nbsp;<a href='/normalShowPersonal?current=<%= pageCount %>' >最后一页</a> 
         <form action="/normalShowPersonal" method="post">
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