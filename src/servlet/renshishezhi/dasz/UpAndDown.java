package servlet.renshishezhi.dasz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainTableDao;

@WebServlet("/upAndDown")
public class UpAndDown extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MainTableDao mtd = new MainTableDao();
		String tableName = request.getParameter("tableName");
		String rowName = request.getParameter("rowName");
		String action = request.getParameter("action");
		if(action.equals("up")){
			mtd.upSmallMove(tableName, rowName);
		}else if(action.equals("down")){
			mtd.downSmallMove(tableName, rowName);
		}
		//
		Object current = request.getSession().getAttribute("daszCurrent");
		request.getRequestDispatcher("daszServlet?tableName=personal&current="+current).forward(request, response);
	}

}
