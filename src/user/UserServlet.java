package user;

import java.io.IOException;
import java.util.List;
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
 * Servlet implementation class UserServlet
 */
@WebServlet("/userServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String bianhao = request.getParameter("bianhao");
		Activity2Dao a2d = new Activity2Dao();
		TreeMap<String,Object> Personmap = new TreeMap();
			Personmap.put("tableName", "匹配");
			Personmap.put("num", bianhao);
			Personmap.put("rowName", "类别");
			Personmap.put("tiaojian", "编号");
			System.out.println("Personmap: " + Personmap);
			//得到main_table中personal的列
			ActivityTableDao atd = new ActivityTableDao();
			TreeMap<String,Object> User = new TreeMap();
			User.put("tableName", "user");
			User.put("*", "*");
			System.out.println("User: " + User);
			//得到main_table中personal的列
			List<CommentObject> userlist = atd.getRowListValue(User);
			//List<CommentObject> userlist = a2d.getRowListValue(Personmap);
		request.setAttribute("userlist", userlist);
		request.getRequestDispatcher("/user/index.jsp").forward(request, response);
	}

}
