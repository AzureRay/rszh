package servlet.renshishezhi.dasz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainTableDao;

/**
 * 删除列
 * @author Tianci
 */
@WebServlet("/daszDelRow")
public class DelRow extends HttpServlet {
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
		//获取
		Object current = request.getSession().getAttribute("daszCurrent");
		request.getRequestDispatcher("daszServlet?tableName=personal&current="+current).forward(request, response);
	}
}
