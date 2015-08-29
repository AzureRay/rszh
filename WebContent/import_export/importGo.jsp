<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*" import="bean.CommentObject"
 import="dao.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/dtree.js"></script>
<title>导入到</title>
</head>

<%
	request.setCharacterEncoding("utf-8");
	List<CommentObject>  bumenList = (List<CommentObject>)request.getAttribute("bumenList");
    System.out.println("departList"+bumenList);
  	String departname=request.getParameter("部门");
   %>
<body>
<%String rylb="所有人员",rslb2="所有人员";%>
	<h3>请继续选择类别和部门</h3>
	
	<div class="dtree">
		<script type="text/javascript">
					d = new dTree('d');
					d.add(1, -1, '公司名称'); 
					</script>
			<%
				for (int i = 0; i <bumenList.size(); i++) {
					Object id =bumenList.get(i).getValues().get("id");
					Object parentId =bumenList.get(i).getValues()
							.get("parent_id");
					String name = (String)bumenList.get(i).getValues()
							.get("depart");
			%>
			<script type="text/javascript">
			d.add(<%=id%>,<%=parentId%>,"<%=name%>", 'importgoServlet?部门=<%=name%>');
			</script>
				<%
									}	
			
				%>
				<script type="text/javascript">
					document.write(d);
				</script>
	</div>
	<br><br><br>
	<form method="post" action="/lastimportServlet?departname=<%=departname%>">
		<select name="人员类别">	
		<%ActivityTableDao atd =new ActivityTableDao();
		List<String> renyuanleibieList = atd.getSelectRowValueList("人员类别");%>
		<option value="所有人员" selected="selected">所有人员</option>
           <%for(int i=0;i<renyuanleibieList.size();i++){
				  rslb2 = renyuanleibieList.get(i);} %>
		<%	for(int i=1;i<renyuanleibieList.size();i++){
				String  rslb1 = renyuanleibieList.get(i); %>
					<option value="<%= rslb1%>"><%= rslb1%></option>
		<%}
			%>		
		</select> 
		<input name="btn" type="submit" value="确认" />
	</form>
		
</body>
</html>