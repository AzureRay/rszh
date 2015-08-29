<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="bean.CommentObject"%>
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
<script>
    
</script>
</head>
<body>

	<%
		List<CommentObject> list = (List<CommentObject>)request.getAttribute("list");
		System.out.print("result："+list);
		List<CommentObject> names = (List<CommentObject>)request.getAttribute("names");
	%>

	<table class="table table-bordered table-hover definewidth m10" id="mainTable">
    <thead>
    <tr>
       <%
       for(int i = 1;i<names.size();i++){
    	   %><th><%=names.get(i).getValues().get("row_name") %></th><%
       }
       %>
    </tr>
    </thead>
	     <%
	     for(int j =0;j<list.size();j++){
	    	 %><tr>
	    	 <%
	    	 for(int i = 1;i<names.size();i++){
	      	   %><td><%=list.get(j).getValues().get(names.get(i).getValues().get("row_name")) %></td><%
	         }
	    	 %>
	    	 </tr><%
	     }
	     %>
	</table>
</body>
</html>