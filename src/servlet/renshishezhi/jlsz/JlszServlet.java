package servlet.renshishezhi.jlsz;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.Introspection;

import bean.CommentObject;
import dao.ActivityTableDao;
import dao.Fenye;
import dao.MainTableDao;

/**
 * 记录设置
 * @author Tianci
 *
 */
@WebServlet("/jlszServlet")
public class JlszServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//获取数据库中除personal之外的表的集合
		MainTableDao mtd = new MainTableDao();
		int pageCount = Fenye.getPagesForTable();
		int current = Integer.parseInt(request.getParameter("current"));
		List<CommentObject> tableInfo = mtd.getTableNameList(current);
		request.setAttribute("tableInfo", tableInfo);
		request.getRequestDispatcher("/jsp/rssz/jlsz/jlsz.jsp?pageCount="+pageCount).forward(request, response);
	}
}
