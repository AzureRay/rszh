<%@page import="dao.MainTableDao"%>
<%@page import="com.sun.rowset.internal.Row"%>
<%@page import="org.apache.tomcat.jni.Stdlib"%>
<%@page import="dao.ActivityTableDao"%>
<%@page import="bean.CommentObject"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="/js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	//val() 可以直接获取 select 的被选择的值. 
	$(function() {
		var i = 1;
		//alert("当前最后的一个父节点的name:"+$("tr:last").attr("name"));
		$("#tab").find(".lj").change(function() {
			var res = $(this).val();
			//alert("选择的父节点的name:"+$(this).closest("tr").attr("name"));
			//alert("当前最后的一个父节点的name:"+$("tr:last").attr("name"));
			if($(this).closest("tr").attr("name")==$("tr:last").attr("name")){
				if (res == "and") {	
					$("tr:first").clone(true).appendTo($("#tab"));
					$("tr:last").attr("name",i);
					$("tr:last").find(".tj").attr("name","xuanze_"+i);
					$("tr:last").find(".ys").attr("name","ysf_"+i);
					$("tr:last").find(".sr").attr("name","shuru_"+i);
					$("tr:last").find(".lj").attr("name","luoji_"+i);
					$("tr:last").find(".lj").attr("id","lj_"+i);
					i++;
					//alert("当前的i:"+i);
					//将当前的i赋值给input
					$("#jishu").val(i);
				}
				if (res == "or") {
					$("tr:first").clone(true).appendTo($("#tab"));
					$("tr:last").attr("name",i);
					$("tr:last").find(".tj").attr("name","xuanze_"+i);
					$("tr:last").find(".ys").attr("name","ysf_"+i);
					$("tr:last").find(".sr").attr("name","shuru_"+i);
					$("tr:last").find(".lj").attr("name","luoji_"+i);
					$("tr:last").find(".lj").attr("id","lj_"+i);					
					i++;
					//alert("当前的i:"+i);
					//将当前的i赋值给input
					$("#jishu").val(i);
				}
			}
			if (res == "ok") {
				var name = $(this).closest("tr").attr("name");
				//alert("当前的name 赋值给i:"+name+1);
				if(name == "0"){
					i = 1;
				}else{
					i = name+1;
				}
				//将当前的i赋值给input
				$("#jishu").val(i);
				$(this).closest("tr").nextAll().remove();
			}
		});
	});
</script>
<style type="text/css">
body {
	padding-bottom: 40px;
}
.sidebar-nav {
	padding: 9px 0;
}
@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
#one{
width:96%;
height:400px;
overflow:auto;
margin:0 auto;
}
#two{
width:96%;
height:35px;
margin:3px auto 0;
text-align:center;
}
</style>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String tableName = request.getParameter("tableName");
	if(tableName == null){
		tableName = "personal";
	}
	ActivityTableDao atd = new ActivityTableDao();
	List<CommentObject> list = atd.getRowNameList(tableName);
	for(int i = 0;i<list.size();i++){
		String rowName = ""+list.get(i).getValues().get("row_name");
		if(rowName.equals("id")){
			list.remove(i);
			break;
		}
	}
%>
<center>
	<form action="/noticeServlet" method="post" class="definewidth m20">    
	  <div id="one">
		<table id="tab">
			<input type="hidden" name="jishu" id="jishu" value="1"/>
			<input type="hidden" name="tableName" id="tableName" value="<%=tableName%>"/>
			<tr name="0">
 				<td><select name="xuanze_0" class="tj">
 					<%
					if(tableName != "personal"){
						%><option value="姓名">姓名</option><%
						%><option value="部门">部门</option><%
 					}
 					for(int i = 0;i<list.size();i++){
 						String rowName = ""+list.get(i).getValues().get("row_name");
 						%><option value="<%= rowName %>"><%= rowName %></option><%
 					}
 					%>
				</select></td>
				<td><select name="ysf_0" class="ys">
						<option value="等于">等于</option>
						<option value="左匹配">左匹配</option>
						<option value="右匹配">右匹配</option>
						<option value="大于">大于</option>
						<option value="小于">小于</option>
						<option value="不等于">不等于</option>
						<option value="包含"  selected="selected">包含</option>
				</select></td>
				<td><input type="text" name="shuru_0" class="sr"/></td>
				<td><select name="luoji_0" id="lj_0" class="lj">
						<option value="and">并且</option>
						<option value="or">或者</option>
						<option selected="selected" value="ok">完成</option>
				</select></td>
			</tr>
		</table>
		</div>
		<div id="two">
		   <input type="submit" name="queding" class="btn btn-success" value="确定"/>
		</div>
	</form>
</center>
</body>
</html>