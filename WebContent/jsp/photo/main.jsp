<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<body>
	文件上传成功！
	<br>

	<%  request.setCharacterEncoding("utf-8");
		String file = request.getParameter("file");
	%>
	<img alt="" src="<%=file%>">

</body>
</html>
