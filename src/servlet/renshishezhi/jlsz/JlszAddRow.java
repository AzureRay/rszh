package servlet.renshishezhi.jlsz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainTableDao;

/**
 * Servlet implementation class JlszAddRow
 */
@WebServlet("/jlszAddRow")
public class JlszAddRow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MainTableDao mtd = new MainTableDao();
		String tableName = request.getParameter("tableName");
		String rowName = request.getParameter("row_name");
		String rowType = request.getParameter("row_type");
		String comment = request.getParameter("comment");
		mtd.addNewRow(tableName, rowName, rowType, 0, comment);
		//获取
		Object current = request.getSession().getAttribute("daszCkxqCurrent");
		request.getRequestDispatcher("/showDetialsServlet?tableName="+tableName+"&current="+current).forward(request, response);
	}

}
