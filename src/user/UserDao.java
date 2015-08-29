package user;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

import dao.Activity2Dao;
import dao.ActivityTableDao;
import dao.DepartDao;

/**
 * Servlet implementation class UserDao
 */
@WebServlet("/userDao")
public class UserDao extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String bianhao = request.getParameter("bianhao");
		String name = request.getParameter("name");
		String leibie = request.getParameter("leibie");
		System.out.println("Personmap^^^^action: " + action);
		System.out.println("Personmap^^^^bianhao: " + bianhao);
		System.out.println("Personmap^^^^name: " + name);
		System.out.println("Personmap^^^^leibie: " + leibie);
		ActivityTableDao atd = new ActivityTableDao();
		DepartDao dd = new DepartDao();
		
		if(action.equals("add")){
			System.out.println("bianhao^^^^Dao: " + bianhao);
			leibie = request.getParameter("leibie");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", "user");
			map.put("编号", bianhao);
			map.put("姓名", name);
			map.put("用户类型", leibie);
			atd.add(map);
		}else if(action.equals("del")){
			action = request.getParameter("action");
			bianhao = request.getParameter("id");
			System.out.println("delete^^^^Daoaction: " + action);
			System.out.println("delete^^^^Daobianhao: " + bianhao);
			Activity2Dao a2d = new Activity2Dao();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName","user");
			map.put("编号", bianhao);
			a2d.delete(map);
		}else if(action.equals("mod")){
			 bianhao = request.getParameter("bianhao");
			Activity2Dao a2d = new Activity2Dao();
			TreeMap<String,Object> Personmap = new TreeMap();
				Personmap.put("tableName", "user");
				Personmap.put("num", bianhao);
				Personmap.put("rowName", "用户类型");
				Personmap.put("tiaojian", "编号");
				System.out.println("Personmap^^^^Dao: " + Personmap);
				name= request.getParameter("name");
				leibie = request.getParameter("leibie");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", "user");
			map.put("编号", bianhao);
			map.put("用户类型", leibie);
			a2d.alter(map);
		}
		request.getRequestDispatcher("/userServlet?bianhao="+bianhao).forward(request, response);
	}

}
