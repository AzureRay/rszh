<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%String id = request.getParameter("id");%>
 <form action="/photoSc?tableName=personal&id=<%=id%>" method ="post" enctype="multipart/form-data">
   <input type="file" name ="path" />
   <input type ="submit" value ="确定" />
   <%System.out.println("fasdaf"+request.getParameter("path")); %> 
 </form>
</body>
</html>
