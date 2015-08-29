package normal;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;
import dao.DepartDao;

/**
 * Servlet implementation class NormalBmszServlet
 */
@WebServlet("/normalBmszServlet")
public class NormalBmszServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String ids = request.getParameter("parentId");
		int id = Integer.parseInt(ids);
		DepartDao dd = new DepartDao();
		List<CommentObject> bumenList = dd.selectDepart(id);
		request.setAttribute("bumenList", bumenList);
		request.getRequestDispatcher("/normal/bmsz.jsp").forward(request, response);
	}

}
