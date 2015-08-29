package xinzeng;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ActivityTableDao;

@WebServlet("/xiugaiCompleteServlet")
public class XiugaiCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Object currentPage = request.getSession().getAttribute("currentPage");
		request.getSession().removeAttribute("currentPage");
		ActivityTableDao atd = new ActivityTableDao();
		atd.alter(request);
		request.getRequestDispatcher("/showPersonal?current="+currentPage).forward(request, response);
	}

}
