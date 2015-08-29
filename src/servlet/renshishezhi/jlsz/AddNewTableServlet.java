package servlet.renshishezhi.jlsz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainTableDao;

@WebServlet("/addNewTableServlet")
public class AddNewTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MainTableDao mtd = new MainTableDao();
		String tableName = request.getParameter("tableName");
		mtd.createTable(tableName);
		//获得
		Object current = request.getSession().getAttribute("jlszCurrent");
		request.getRequestDispatcher("/showDetialsServlet?current="+current).forward(request, response);
	}

}
