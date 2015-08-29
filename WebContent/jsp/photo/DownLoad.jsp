<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.io.*" %>
    <%@ page import = "java.util.*" %>
    <%@ page import = "servlet.import_export.DaochuJluDao" %>
    <%@ page import = "bean.CommentObject" %>
    <%@ page import = "photo.photoDao" %>
<html>
 <body>
 <% request.setCharacterEncoding("utf-8");
  String tableName = request.getParameter("tableName");
    String id = request.getParameter("id");
    List<CommentObject> list1 = DaochuJluDao.query(tableName,id);
	String bianhao =list1.get(0).getValues().get("编号")+"";
	List<CommentObject> photoList = photoDao.query(bianhao);
 %>
 <%String path = request.getRealPath("\\photo");
   System.out.println(path);
%>
<form method="POST" action="/downloadServlet?path = <%=path%>">
       选择下载图片：<br>
       <select name= "path">
        <% for(int i=0;i<photoList.size();i++){%>
        <option value="<%=path+"\\"+photoList.get(i).getValues().get("photo")%>"><%=photoList.get(i).getValues().get("photo")%>
       <%} %>
       </select><br>
  <input type="submit" value="提交"> 
</form>
</body>
</html>