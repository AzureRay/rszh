package servlet.renshishezhi.selectTable;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ActivityTableDao;

/**
 * Servlet implementation class LbszAdd
 */
@WebServlet("/selectAdd")
public class SelectAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String rowName = request.getParameter("rowName");
		String rowValue = request.getParameter("rowValue");
		ActivityTableDao atd = new ActivityTableDao();
		//atd.addNewSelectRow("人员类别", rowValue);
		atd.addNewSelectRow(rowName, rowValue);
		request.getRequestDispatcher("/selectServlet").forward(request, response);
	}

}
