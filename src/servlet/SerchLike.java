package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

import dao.ActivityTableDao;
import dao.DepartDao;
import dao.SerchLikeDao;

/**
 * 模糊查询
 * @author Tianci
 *
 */
@WebServlet("/serchLike")
public class SerchLike extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String keyword = request.getParameter("keywords");
		String tableName = request.getParameter("tableName");
		SerchLikeDao sDao = new SerchLikeDao();
		List<CommentObject> list = sDao.serchLikeKey(tableName, keyword);
		
		DepartDao dd = new DepartDao();
		List<CommentObject> bumenList = dd.selectDepart();
		
		ActivityTableDao atd = new ActivityTableDao();
		List<CommentObject> rowNames= atd.getRowNameList(tableName);
		
		request.setAttribute("list", list);
		request.setAttribute("rowNames", rowNames);
		request.getRequestDispatcher("/showPersonal.jsp").forward(request, response);
	}

}
