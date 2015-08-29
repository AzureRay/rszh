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
 <%String path = request.getRealPath("/photo");
   System.out.println(path);
%>
       <tr>
        <% for(int i=0;i<photoList.size();i++){%>
        <td><img src= "<%="/photo/"+photoList.get(i).getValues().get("photo")%>" width="100" height="100"/></td>
       <%} %>
       </tr>
</body>
</html>