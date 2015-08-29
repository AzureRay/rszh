package servlet.renshishezhi.dasz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainTableDao;

@WebServlet("/daszModRow")
public class DaszModRow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String tableName = request.getParameter("tableName");
		String oldRowName = request.getParameter("oldRowName");
		String newRowName = request.getParameter("rowName");
		String rowType = request.getParameter("rowType");
		String comment = request.getParameter("comment");
		MainTableDao mtd = new MainTableDao();
		mtd.alterRow(tableName, oldRowName, newRowName, rowType, comment);
		//获取要修改的列的所在页
		Object current = request.getSession().getAttribute("daszCurrent");
		request.getRequestDispatcher("/daszServlet?tableName=personal&current="+current).forward(request, response);
	}

}
