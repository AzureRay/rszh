package rsjl;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;
import dao.ActivityTableDao;
import dao.DepartDao;
import dao.Fenye;
import dao.MainTableDao;

@WebServlet("/jlszServletRequest")
public class JlszServletRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atDao = new ActivityTableDao();
		MainTableDao mtd = new MainTableDao();
		List<CommentObject> tableNameList = mtd.getTableNameList();
		//删除personal
		for(int i = 0; i < tableNameList.size();i++){
			String nameString = tableNameList.get(i).getValues().get("table_name")+"";
			if(nameString.equals("personal")){
				tableNameList.remove(i);
				break;
			}
		}
		//获取默认的第一个表，获取其中的列信息
		System.out.println("map1");
		String tableName =(String)request.getParameter("tableName");
		
		System.out.println("map2"+tableName );

		if(tableName == null){
			tableName = tableNameList.get(0).getValues().get("table_name")+"";
		}
		Map<String,Object> map=new HashMap<String,Object> ();
		map.put("tableName", tableName);
		List<CommentObject> dataList=atDao.getListWithWhere(map);
		List<CommentObject> rowList = atDao.getRowNameList(tableName);
		//获取select_table中的人员类别
		List<String> renyuanleibieList = atDao.getSelectRowValueList("人员类别");
		//获取部门
		DepartDao dd = new DepartDao();
		List<CommentObject> bumenList = dd.selectDepart();
		//如果有限定条件，则获取
		String leibie = request.getParameter("rylb");
		String depart = request.getParameter("部门");
		if(leibie == null){
			leibie = "所有人员";
		}
		if(depart == null){
			depart = "公司";
		}
		//查询记录
		List<CommentObject> list=null;
		if(leibie.equals("所有人员")){
		      if(depart.equals("公司")){
			 list = LHCX.queryList1(tableName);
			System.out.println("1："+leibie);}
			else
			 list = LHCX.queryList(tableName,depart);	
			System.out.println("2："+leibie);
		}
		else if(leibie.equals("公司")){
	    list = LHCX.queryList3(tableName, leibie);
		}
		else{
		  list = LHCX.queryList(tableName, depart, leibie);
			System.out.println("list3:"+list);}
		
		request.setAttribute("depart", depart);
		request.setAttribute("tableNameList", tableNameList);
		request.setAttribute("renyuanleibieList", renyuanleibieList);
		request.setAttribute("dataList", dataList);
		request.setAttribute("rowList", rowList);
		request.setAttribute("bumenList", bumenList);
		request.setAttribute("list", list);
		request.setAttribute("table", tableName);
		request.getRequestDispatcher("/jsp/rsjl/rsjl.jsp?tableName="+tableName).forward(request, response);
	}
}
