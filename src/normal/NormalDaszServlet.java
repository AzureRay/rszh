package normal;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;
import dao.ActivityTableDao;
import dao.Fenye;

/**
 * Servlet implementation class NormalDaszServlet
 */
@WebServlet("/normalDaszServlet")
public class NormalDaszServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取personal表中的所有列信息
		ActivityTableDao atd = new ActivityTableDao();
		int pageCount = Fenye.getPageForRow("personal");
		System.out.println("总页数："+pageCount);
		List<CommentObject> list = atd.getRowNameList(request);
		//获取数据类型
		List<String> sjlx = atd.getSelectRowValueList("数据类型");
		request.setAttribute("list", list);
		request.setAttribute("sjlx", sjlx);
		request.getRequestDispatcher("/normal/dasz.jsp?pageCount="+pageCount).forward(request, response);
	}
}
