
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*"
	import="bean.*"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="/css/bootstrap-responsive.css" />
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
        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>
    <script type="text/javascript">
    	$(function(){
    		$("#open-btn").click(function(){
    			var path = $("#path").val();
    			if(path == ""){
    				alert("请选择文件！");
    				return false;
    			}else{
    				return true;
    			}
    		});
    	});
    </script>
</head>
<body>
 <%request.setCharacterEncoding("utf-8");
 String tableName=request.getParameter("tableName");
 String id=request.getParameter("id");

 System.out.println(tableName);
 session.setAttribute("tableName", tableName);

 %>
  <p><span style="font-size:18px;font-weight:bold;">请选择要导入的档案</span></p>
  <form class="form-inline definewidth m20" action="/upload1" method="post" enctype="multipart/form-data">
    <input type="file" id="path" name="path" value=""/>
    <input class="btn btn-success" id="open-btn" type="submit" value="确定"/><br>
  </form>
</body>