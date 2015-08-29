package servlet.renshishezhi.jlsz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainTableDao;

@WebServlet("/jlszModRow")
public class JlszModRow extends HttpServlet {
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
		//获取
		Object current = request.getSession().getAttribute("daszCkxqCurrent");
		System.err.println("得到的current:"+current);
		//response.sendRedirect("/showDetialsServlet?tableName="+tableName+"&current="+current);
		request.getRequestDispatcher("/showDetialsServlet?tableName="+tableName+"&current="+current).forward(request, response);
	}

}
