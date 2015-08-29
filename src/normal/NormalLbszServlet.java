package normal;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ActivityTableDao;

/**
 * Servlet implementation class NormalLbszServlet
 */
@WebServlet("/normalLbszServlet")
public class NormalLbszServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		List<String> list = atd.getSelectRowValueList("人员类别");
		request.setAttribute("rylb", list);
		request.getRequestDispatcher("/normal/lbsz.jsp").forward(request, response);
	}

}
