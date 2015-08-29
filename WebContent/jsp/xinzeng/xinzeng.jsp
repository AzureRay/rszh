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
</style>
<script>
	$(function() {

	});
</script>
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
<body>

	<%
		List<String> rowNames = (List<String>) request
			.getAttribute("rowNames");
			System.out.println("列：" + rowNames);
			List<CommentObject> selectValue = (List<CommentObject>) request
			.getAttribute("selectValue");
			System.out.println("选择列值：" + selectValue);
			List<CommentObject> bumenList = (List<CommentObject>) request
			.getAttribute("bumenList");
			System.out.println("部门：" + bumenList);
			Map<String, String> isSelectMap = (Map<String, String>) request
			.getAttribute("isSelectMap");
			System.out.println("是否是选择列：" + isSelectMap);
	%>

	<form class="form-inline definewidth m20" id="table_tool"
		action="/xinZengServlet" method="post">
		<input type="hidden" name="tableName" value="personal">
		<div id="contentRight" style="width: 100%; font-size: 14px; overflow:scroll;">
			<table cellpadding="5px"
				class="table table-bordered table-hover definewidth m10"
				id="mainTable">
				<%
					for (int i = 0; i < rowNames.size(); i++) {
							String name = rowNames.get(i);
							if (i % 3 == 0) {
				%><tr>
					<%
						}
								if (isSelectMap.containsKey(name)) {//判断该列是否是选择列
					%>
					<td><%=name%>：</td>
					<td><select name="<%=name%>">
							<%
								for (int j = 0; j < selectValue.size(); j++) {
												String rowName = selectValue.get(j).getValues()
																	.get("row_name")+ "";
												if (rowName.equals(name)) {
													String rowValue = selectValue.get(j).getValues().get("row_value")+ "";
							%><option value="<%=rowValue%>"><%=rowValue%></option>
							<%
								}
											}
							%>
					</select></td>
					<%
						}else if(name.equals("部门")){
					%><td>部门：</td>
					<td><select name="部门">
							<%
								for (int k = 0; k < bumenList.size(); k++) {
												String bmname = bumenList.get(k).getValues().get("depart")+ "";
							%><option value="<%=bmname%>"><%=bmname%></option>
							<%
								}
							%>
					</select></td>
					<%
						} else {
					%><td><%=name%>：</td>
					<td><input type="text" name="<%=name%>" value=""></td>
					<%
						}if ((i + 1) % 3 == 0) {
					%>
				</tr>
				<%
					}
						}
				%>
			</table>
		</div>
		<center>
			<input type="submit" value="确定" class="btn btn-primary"> <input
				type="reset" value="重置" class="btn btn-primary">
		</center>
	</form>
</body>
</html>