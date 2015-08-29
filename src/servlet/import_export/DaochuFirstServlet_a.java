package servlet.import_export;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;
import dao.ActivityTableDao;
import dao.MainTableDao;

/**
 * Servlet implementation class DaochuFirstServlet
 */
@WebServlet("/daochuFirstServlet_a")
public class DaochuFirstServlet_a extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		String tableName = request.getParameter("tableName");
		List<CommentObject> list = atd.getRowNameList(tableName);
		request.setAttribute("list",list);
		request.getRequestDispatcher("/import_export/daochu.jsp?tableName="+tableName).forward(request, response);
	}

}