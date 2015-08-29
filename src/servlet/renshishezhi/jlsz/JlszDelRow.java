package servlet.renshishezhi.jlsz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainTableDao;

/**
 * Servlet implementation class JlszDelRow
 */
@WebServlet("/jlszDelRow")
public class JlszDelRow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String tableName = request.getParameter("tableName");
		String rowName = request.getParameter("rowName");
		MainTableDao mtd = new MainTableDao();
		mtd.dropRow(tableName, rowName);
		Object current = request.getSession().getAttribute("daszCkxqCurrent");
		System.err.println("得到的current:"+current);
		request.getRequestDispatcher("showDetialsServlet?tableName="+tableName+"&current="+current).forward(request, response);
	}
}
