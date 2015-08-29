package servlet.dayin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;
import dao.ActivityTableDao;

/**
 * Servlet implementation class DayinBaoFirstServlet
 */
@WebServlet("/dayinBaoFirstServlet")
public class DayinBaoFirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		String tableName = request.getParameter("tableName");
		List<CommentObject> list = atd.getRowNameList(tableName);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/dayin/dayin_a.jsp").forward(request, response);
	}

}
