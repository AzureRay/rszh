package gaojichaxun;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

import dao.ActivityTableDao;

@WebServlet("/xiugaiCompleteServletGJCX")
public class XiugaiCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		atd.alter(request);
		//重新获取
		//获取数据库表中的记录
		String sql = request.getSession().getAttribute("GJCXsql")+"";
		GaoJiChaXun gaoJiChaXun = new GaoJiChaXun();
		List<CommentObject> list = gaoJiChaXun.getList(sql+"");
		request.setAttribute("list", list);
		request.getRequestDispatcher("/jsp/gaojichaxun/showPersonal.jsp").forward(request, response);
	}

}
