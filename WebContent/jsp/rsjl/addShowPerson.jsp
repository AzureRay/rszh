<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" import="bean.*"
    import="dao.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="/js/dtree.js"></script>
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
	/* Enable use of floated navbar text */di
		padding-right: 5px;
	}
}
</style>
<script type="text/javascript">
$(function(){
	$("#queding").click(function(){
		var len = $("#mainTable input:checked").length;
		if(len == 0){
			alert("请选择列！");
			return false;
		}else if(len >= 2){
			alert("只能选择一列");
			return false;
		}else if( len == 1){
			var val = $("input:checked").val();
			$("#bianhao").val(val);
		}
	});
});
</script>
</head>
<body>
<%      request.setCharacterEncoding("UTF-8");
		String tableName=(String)request.getParameter("tableName");
		System.out.println("tableName"+tableName);
		List jibenList = new ArrayList();
        ActivityTableDao atDao=new ActivityTableDao();
        Map<String,Object> map=new HashMap<String,Object> ();
		map.put("tableName", "personal");
		map.put("编号","编号");
		map.put("姓名","姓名");
		map.put("部门","部门");
		System.out.println("map"+map);
		List<CommentObject> dataList=atDao.getRowListValue(map);
		System.out.println("dataList"+dataList); %>
		
		<div id="contentRight" style="font-size: 14px; border: 1px solid gray; overflow:scroll;">
	<table class="table table-bordered table-hover definewidth m10" id="mainTable">
    <thead>
    <tr>
        <th>序号</th>
		<th id="checkCol"><input type="checkbox" name="check" />
		        <th>编号</th>
		        <th>姓名</th>
		        <th>部门</th>
    </tr>
    </thead>
	    <%
					for (int i = 0; i < dataList.size(); i++) {
						String bianhao = (String)dataList.get(i).getValues().get("编号");
						String name = (String)dataList.get(i).getValues().get("姓名");
						String bumen = (String)dataList.get(i).getValues().get("部门");
				%>
				
		<tr>
			<td><%= i+1 %></td>
			<td><input type="checkbox"  value="<%=bianhao%>" /></td>
			<td name="bianhao"><%=bianhao%></td>
			<td name="name"><%=name%></td>
			<td name="bumen"><%=bumen%></td>
		</tr>
		<%
		}
	%>
	<form method="post" action="/jsp/rsjl/add.jsp" id="tools">
	<input type="hidden" name="tableName" value="<%=tableName%>">
	<input type="hidden" name="bianhao" value="" id="bianhao">
	<input type="submit" class="btn btn-success" id="queding" value="确定">
	</form>
		</table>
</body>
</html>