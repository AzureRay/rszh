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
		
	});
</script>
<body>
<%
	List<CommentObject> rowValuelist = (List<CommentObject>)request.getAttribute("list");
	String tableName = request.getParameter("tableName");
	if(tableName == null){
		tableName = session.getAttribute("CountTable")+"";
	}
	ActivityTableDao atd = new ActivityTableDao();
	List<CommentObject> rownamelist = atd.getRowNameList(tableName);
%>
	<div id="contentRight" style="width: 100%; height: 440px; font-size: 14px; margin-left: 0px; margin-bottom: 0px; border: 1px solid #DDDDDD; overflow: scroll;">
		<form action="/saveCountResult" id="">
			请输入统计名称：<input type="text"  name="countName" value="统计名称"/>
			<input type="submit" class="btn btn-success" name="" id="" value="保存统计结果"/>
			<table class="table table-bordered table-hover definewidth m10" id="mainTable">
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
				if(!tableName.equals("personal")){
					%><th>姓名</th><%
					%><th>部门</th><%
				}
 				for (int i = 0; i < rownamelist.size(); i++) {
 				String rowName = (String) rownamelist.get(i).getValues()
 						.get("row_name");
 				%><th><%=rowName%></th><%
				}%></tr>
				<%
				for (int i = 0; i < rowValuelist.size(); i++) {
					String id = rowValuelist.get(i).getValues().get("id") + "";
			%>
			<tr>
				<td><%=i + 1%></td>
				<td><input type="checkbox" name="check+<%=id%>" value="<%=id%>" /></td>
				<%
					if(!tableName.equals("personal")){
						Object name = rowValuelist.get(i).getValues().get("姓名");
						Object bumen = rowValuelist.get(i).getValues().get("部门");
						%><td><%=name%></td><%
						%><td><%=bumen%></td><%
					}
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
			%>
			</table>
		</form>
	</div>
</body>
</html>