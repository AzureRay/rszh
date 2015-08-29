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
				$("#id").val(val);
			}
		});
	});
</script>

<%
	DepartDao dd = new DepartDao();
	List<CommentObject> departList = dd.selectDepart();
	List<CommentObject> rownamelist = (List<CommentObject>) request
			.getAttribute("list1");
	System.out.println("rownamelist:" + rownamelist);
	List<CommentObject> rowValuelist = (List<CommentObject>) request
			.getAttribute("list");
	System.out.println("rowValuelist:" + rowValuelist);
	
%>
<body>
	<table cellpadding="5px">

		<tr>
			<td><form id="table_tool" action="/normalRsbdServlet" method="post">
					<input type="hidden" name="tableName" value="personal">部门：
					<select name="部门">
						<%
							for (int i = 0; i < departList.size(); i++) {
								String bumen = departList.get(i).getValues().get("depart") + "";
						%>
						<option value="<%=bumen%>"><%=bumen%></option>
						<%
							}
						%>
					</select>
			
			<input type="submit" class="btn btn-success" value="搜索" />
			</form>
			</td>
			<td>
				<form id="table_tool1" action="#"
					method="post">
					<input type="hidden" name="tableName" value="personal"> <input
						type="hidden" name="id" value="" id="id"> <input
						type="submit" class="btn btn-success" id="mod" value="修改" />
				</form>
			</td>
		</tr>
	</table>
	<div id="contentRight"
		style="width: 100%; height: 440px; font-size: 14px; margin-left: 0px; margin-bottom: 0px; border: 1px solid #DDDDDD; overflow: scroll;">
		<table class="table table-bordered table-hover definewidth m10"
			id="mainTable">
			<tr>
				<th>序号</th>
				<th id="checkCol"><input type="checkbox" name="check" /> <%
 	for (int i = 0; i < rownamelist.size(); i++) {
 		String rowName = (String) rownamelist.get(i).getValues()
 				.get("row_name");
 		System.out.println("rowName:" + rowName);
 %>
				<th><%=rowName%></th>

				<%
					}
				%>
			</tr>
			<%
				for (int i = 0; i < rowValuelist.size(); i++) {
					String id = rowValuelist.get(i).getValues().get("id") + "";
			%>
			<tr>
				<td><%=i + 1%></td>
				<td><input type="checkbox" name="check" value="<%=id%>" /></td>
				<%
				for (int j = 0; j < rownamelist.size(); j++) {
				%>
				<%
					String rowValue = ""+ rowValuelist.get(i).getValues().get(""+ rownamelist.get(j).getValues().get("row_name"));
					if(rowValue.equals("null")){
						%>
						<td></td>
						<%
					}else{
						%>
						<td><%=rowValue%></td>
						<%
					}
				}
				%>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>