package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ActivityTableDao;

/**
 * 添加选择列
 * @author Tianci
 *
 */
@WebServlet("/selectDao")
public class SelectDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		String action = request.getParameter("action");
		String rowName = request.getParameter("rowName");
		String rowValue = request.getParameter("rowValue");
		String target = request.getParameter("target");
		
		if(action.equals("del")){
			atd.delSelectRowValue(rowName, rowValue);
		}else if(action.equals("add")){
			atd.addNewSelectRow(rowName, rowValue);
		}else if(action.equals("mod")){
			String old = request.getParameter("old");
			atd.alterSelectRow(rowName, old, rowValue);
		}
		
		if(target.equals("lbsz")){
			request.getRequestDispatcher("lbszServlet").forward(request, response);
		}
	}

}
