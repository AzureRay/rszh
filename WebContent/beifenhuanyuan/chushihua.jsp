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
      
    <title>数据恢复测试</title>  
      
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">  
    <!--  
    <link rel="stylesheet" type="text/css" href="styles.css">  
    -->  
  
  </head>  
    
  <body>  
<%  
request.setCharacterEncoding("utf-8");
String filePathf =(String)request.getRealPath("/temporaryFile/");
String filepath = filePathf+"\\"+"project.sql";

//String filepath="g:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/rszh/mysqlFile/project.sql";
System.out.println("filepath  ^^"+filepath);
Recover com = new Recover();
//String url = "e:/project.sql"; 
Properties properties = new Properties();
		 InputStream inStream = JdbcTools.class.getClassLoader()
		 		.getResourceAsStream("jdbc.properties");
		 properties.load(inStream);
		 String username = properties.getProperty("user");//MySQL数据库的用户名  
		 String password = properties.getProperty("password");//MySQL数据库的密码 
		 %>
		 <%
  boolean check = com.loadDelete();  
if(check){
	
	Recover com1 = new Recover(); 
	//String url = "e:/project.sql";  
	boolean check1 = com1.load(username,password,filepath); 
	System.out.println("check1"+check1);
	if(check1){  
	 %> 
	 数据库初始化成功！ 
	 <%} 
	
 %> 
 
  
 <%} %>
		      <%  
				%>  
 
  </body>  
</html> 