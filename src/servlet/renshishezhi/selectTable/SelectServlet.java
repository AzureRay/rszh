package servlet.renshishezhi.selectTable;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

import com.sun.accessibility.internal.resources.accessibility;

import dao.ActivityTableDao;
import dao.MainTableDao;

/**
 * 类别设置
 * 1.获取数据库中类别
 * @author Tianci
 *
 */
@WebServlet("/selectServlet")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		List<String> list = atd.getSelectRowValueList();
		request.setAttribute("select", list);
		request.getRequestDispatcher("/jsp/rssz/select/select.jsp").forward(request, response);
	}

}
