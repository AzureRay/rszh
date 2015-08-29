package servlet.renshishezhi.jlsz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainTableDao;

/**
 * Servlet implementation class jlszUpandDown
 */
@WebServlet("/jlszUpandDown")
public class JlszUpandDown extends HttpServlet {
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
		//获取
		Object current = request.getSession().getAttribute("daszCkxqCurrent");
		System.err.println("得到的current:"+current);
		request.getRequestDispatcher("showDetialsServlet?tableName="+tableName+"&current="+current).forward(request, response);
	}
}
