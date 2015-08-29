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
<script type="text/javascript">
    $(function () {
		$('#add').click(function(){
			var val = $("#table_tool input[name='bianhao']").val();
			window.location.href="/user/add.jsp?bianhao="+val;
		 });
		$('#mod').click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
			}else if(len >= 2){
				alert("只能选择一列");
			}else if( len == 1){
				var val = $("input:checked").val();
				var pid = $("#table_tool input[name='bianhao']").val();
				window.location.href="/user/mod.jsp?id="+val+"&bianhao="+pid;
			}
		 });
		$('#del').click(function(){
			var len = $("#mainTable input:checked").length;
			if(len == 0){
				alert("请选择列！");
			}else if(len >= 2){
				alert("只能选择一列");
			}else if( len == 1){
				var val = $("input:checked").val();
				var pid = $("#table_tool input[name='bianhao']").val();
				window.location.href="/userDao?action=del&id="+val+"&bianhao="+pid;
			}
		 });
    });
</script>
</head>
<body>

	<%
		List<CommentObject> userlist = (List<CommentObject>)request.getAttribute("userlist");
		String bianhao = request.getParameter("id");
		System.out.print("bianhao111"+bianhao);
	%>

	<form class="form-inline definewidth m20" id="table_tool" action="" method="post">    
   		 <input type="hidden" name="id" value="">
   		 <input type="hidden" name="bianhao" value="<%=bianhao%>">
		 <input type="hidden" name="action" value="">
   		 <button type="button" class="btn btn-success" id="add">添加</button>
   		 <button type="button" class="btn btn-success" id="mod">修改</button>
   		 <button type="button" class="btn btn-success" id="del">删除</button>
	</form>
	<%
	 System.out.print("bianhao"+bianhao);
		if(userlist.isEmpty()){
			%><h2>无部门</h2><%
		}else{
			%>
			<table class="table table-bordered table-hover definewidth m10" id="mainTable">
			    <thead>
			    <tr>
			        <th>序号</th>
					<th id="checkCol"><input type="checkbox" name="check" />
					<th>编号</th>
					<th>用户名</th>
					<th>用户类型</th>
			    </tr>
			    </thead>
				    <%
				    System.out.print("userlist"+userlist);
				    	for(int i =0;i < userlist.size();i++){
				    		//Object id  = userlist.get(i).getValues().get("id");
				    		Object num = userlist.get(i).getValues().get("编号");
				    		Object name = userlist.get(i).getValues().get("姓名");
				    		Object leibie = userlist.get(i).getValues().get("用户类型");
				    		%>
				    		<tr>
				    		<td><%= i+1 %></td>
							<td><input type="checkbox" name="check" value="<%= num %>" /></td>
							<td><%= num %></td>
							<td><%= name %></td>
							<td><%= leibie %></td>
							</tr>
				    		<%
				    	}
				    %>
			</table>
			<%
		}
	%>
</body>
</html>