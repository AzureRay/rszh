<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*" import="bean.CommentObject"
	import="dao.*"%>
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
    $(function () {       
		$('#backid').click(function(){
			var pid = $(":input[name='bianhao']").val();
			window.location.href="/userServlet?bianhao="+pid;
		 });
    });
</script>
</head>
<body>
	<%
		String bianhao = request.getParameter("id");
	Activity2Dao a2d = new Activity2Dao();
	TreeMap<String,Object> Personmap = new TreeMap();
		Personmap.put("tableName", "user");
		Personmap.put("num", bianhao);
		Personmap.put("rowName", "*");
		Personmap.put("tiaojian", "编号");
		System.out.println("Personmap^^^^Dao: " + Personmap);
		List<CommentObject> list = a2d.getRowListValue(Personmap);
		System.out.println("list"+list);
		String name= (String)list.get(0).getValues().get("姓名");
		System.out.println("name"+name);
		String leibie1 =(String)list.get(0).getValues().get("用户类型");
	%>
<center>
	<form action="/userDao?action=mod" method="post" class="definewidth m20">
	<table class="table table-bordered table-hover definewidth m10">
    <input type="hidden" name="bianhao" value="<%= bianhao%>">

    <tr>
          <td width="10%" class="tableleft" value="<%= bianhao%>">编号（不可修改）：</td>
        <td><input type="text" name="bianhao" value="<%= bianhao%>"/></td>
        <tr>
         <td width="10%" class="tableleft" value="<%= name%>">姓名（不可修改）：</td>
        <td><input type="text" name="name" value="<%= name%>"/></td>
        <tr>
         <%
        ActivityTableDao atd = new ActivityTableDao();
		TreeMap<String,Object> User = new TreeMap();
		User.put("tableName", "user");
		User.put(" distinct 用户类型  ", "*");
		System.out.println("User: " + User);
		//得到main_table中personal的列
		List<CommentObject> userlist = atd.getRowListValue(User);
		List <String> liebieList =new ArrayList();
		liebieList.add(leibie1);
		 for(int i =0;i < userlist.size();i++){
	    	
	    	liebieList.add((String)userlist.get(i).getValues().get("用户类型"));
	  
	    	}
	    %>
	             <td width="10%" class="tableleft">用户类别：</td>
				<td><select name="leibie"><%
			for (int k = 0; k < liebieList.size(); k++) {
				String leibie = liebieList.get(k);
				%><option value="<%=leibie%>"><%=leibie%></option><%
			}%></select></td>
				</tr>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" type="button" id="save">保存</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
	</table>
	</form>
</center>
</body>
</html>