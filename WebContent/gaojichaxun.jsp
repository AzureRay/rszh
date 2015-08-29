<%@page import="bean.CommentObject"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(function(){
		var clone = $("#mouban").clone(true);
		clone.insertBefore($("#before"));
		clone.addClass("mou");
		clone.show();
	});
	
	function addNew(){
		$("#mouban").clone(true).insertBefore($("#before")).addClass("mou").show();
		var length = $("#main_serch_area .mou").size();
		alert("length:"+length);
		var value = $("#main_serch_area .mou ");
	};
</script>
</head>
<body>

	<%
		List<CommentObject> list = (List<CommentObject>) request
				.getAttribute("list");
		System.out.println("list :" + list);
	%>
	<div id="mouban" style="display:none">
		<select name="ziduanName">
			<%
				for (int i = 0; i < list.size(); i++) {
					String ziduanName = "" + list.get(i).getValues().get("row_name");
				%>
					<option value="<%=ziduanName%>"><%=ziduanName%></optiopn>
				<%
				}
			%>
			</select>
			<select name="tiaojian">
				<option value="=">等于</optiopn>
				<option value="like">包含</optiopn>
				<option value=">">大于</optiopn>
				<option value="<">小于</optiopn>
			</select>
			<input type="text" name="keyword" value=""> 
			<select class="xuanze" onchange="addNew()" name="ser_fangfa">
				<option value="and">并且</optiopn>
				<option value="or">或者</optiopn>
			</select>
			<button>完成</button>
	</div>
	<form id="main_serch_area" action="gaojiSerch" method="post">
		<br id="before">
		<input type="submit" value="提交">
	</form>
</body>
</html>