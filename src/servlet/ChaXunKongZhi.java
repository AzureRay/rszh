package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

import dao.ActivityTableDao;
/**
 * 查询控制
 * @author Tianci
 *
 */
@WebServlet("/chaXunKongZhi")
public class ChaXunKongZhi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1.获取personal表中所有的列信息
		ActivityTableDao atd = new ActivityTableDao();
		List<CommentObject> rowList = atd.getRowNameList("personal");
		request.setAttribute("rowList",rowList );
		request.getRequestDispatcher("/jsp/tongji/chaxunkongzhi.jsp").forward(request, response);
	}
}
