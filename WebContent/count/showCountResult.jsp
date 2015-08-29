<%@page import="count.SaveCount"%>
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
	});
</script>
<body>
<%
	SaveCount sc = new SaveCount();
	List<CommentObject> list = sc.getCountRecord();
%>
	<div id="contentRight" style="width: 100%; height: 440px; font-size: 14px; margin-left: 0px; margin-bottom: 0px; border: 1px solid #DDDDDD; overflow: scroll;">
		<form action="/delCountRecord" id="">
			<a href="/count/selectTable.jsp" id="add" class="btn btn-success">添加</a>
			<input type="submit" class="btn btn-success" name="del" id="del" value="删除"/>
			<table class="table table-bordered table-hover definewidth m10"
			id="mainTable">
			<tr>
				<th>序号</th>
				<th>选择</th>
				<th>统计内容</th>
				<th>统计数量</th>
			</tr>
			<%
			for(int i=0;i<list.size();i++){
				%><tr><td><%= i+1 %></td>
				<td><input type="checkbox" name="<%= "name"+i %>" id="countId" value="<%= list.get(i).getValues().get("id")%>"></td>
				<td><%= list.get(i).getValues().get("count_content") %></td>
				<td><%= list.get(i).getValues().get("count_num") %></td></tr><%
			}
			%>
			</table>
		</form>
	</div>
</body>
</html>