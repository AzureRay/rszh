<%@page import="org.jfree.ui.about.ProjectInfo"%>
<%@ page language="java" import="java.util.*,servlet.beifen_huanyuan.*" pageEncoding="UTF-8"%> 
<%@ page import = "java.util.Properties" %>
<%@ page import = "java.io.InputStream" %>
<%@ page import = "jdbc.*" %>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>数据库备份测试</title>  
    <link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="/css/style.css" />
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">   
  </head>  
    
  <body>  
<% 
request.setCharacterEncoding("utf-8");
Command com = new Command();  
Properties properties = new Properties();
InputStream inStream = JdbcTools.class.getClassLoader()
		.getResourceAsStream("jdbc.properties");
properties.load(inStream);
String username = properties.getProperty("user");//MySQL数据库的用户名  
String password = properties.getProperty("password");//MySQL数据库的密码  
String database = "project";//数据库名字  
//String url = "g:/project.sql";
String urlf =(String)request.getRealPath("/temporaryFile/");
String url=urlf+"\\project.sql";
System.out.print("url"+url); 
//String url = "/temporaryFile/project.sql";
//备份的目的地址  
boolean check = com.backupDatabase(username, password, database, url);
if(check){  
 %>  
 <center>
   <table style="margin-top:200px;">
        <tr><td>如需备份请按确定键：</td></tr>
 <tr><td style="text-align:center;"><form action="/beifenServlet" method = "post">
 <input type = "submit" value = "确定" class="btn btn-success">
 </form>
 </td>
 </tr>
 <%}  %> 
 </table>
 </center>
  </body>  
</html>  