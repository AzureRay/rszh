<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" /> 
</head>
<body>
<center>
   <table style="margin-top:200px;">
 <tr>
   <td>
 <form action="/uploadBH" method ="post" enctype="multipart/form-data">
   <input type="file" name ="path"/>
   <input type ="submit" value ="确定" class="btn btn-success"/>
 </form>
   </td>
 </tr>
 </table>
 </center>
</body>
</html>
