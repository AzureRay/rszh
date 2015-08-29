<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
 $(function () {
	
});
</script>
<html>

<style type="text/css">
#mainTable th{
min-width:100px;
white-space:nowrap;
}
#mainTable td{
min-width:100px;
white-space:nowrap;
}
</style>
 <body>
 <% request.setCharacterEncoding("utf-8");
 	String id = request.getParameter("id");
    String tableName = request.getParameter("tableName");
 %>
 <form method="POST" enctype="multipart/form-data" action="/upload_photo?id=<%=id%>&tableName=<%=tableName%>">
  图片上传: <input type="file" name="upfile"><br/>
  <br/>
  <input type="submit" class="btn btn-success" value="确定" />
</form>
</body>
</html>