package rsjl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.GetMapUtil;

import dao.ActivityTableDao;

@WebServlet("/deleteJl")
public class DeleteJl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		System.out.println("12344^: "+GetMapUtil.getRequestMap(request));
		atd.delete(request);
		String tableName = request.getParameter("tableName");
		request.getRequestDispatcher("/jlszServletRequest?tableName="+tableName).forward(request, response);
	}

}
