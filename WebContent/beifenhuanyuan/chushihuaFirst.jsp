<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
</head>
<body>
<%request.setCharacterEncoding("utf-8"); %>
<center>
   <table style="margin-top:200px;font-size:18px;">
       <tr>
         <td>如需还原数据库，请按确定按钮。</td>
        </tr>
      <tr><td style="text-align:center;"><form action="/beifenhuanyuan/chushihua.jsp" method = "post">
          <input type = "submit" value = "确定" class="btn btn-success">
 </form>
 </td>
 </tr>
 </table>
 </center>
</body>
</html>