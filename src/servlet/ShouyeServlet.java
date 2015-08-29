package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.GetMapUtil;

import bean.CommentObject;

import dao.ActivityTableDao;
import dao.DepartDao;
import dao.MainTableDao;

/**
 * 用于获取首页信息bumenId、tableName
 * 1.获取部门信息
 * 2.获取具体显示信息
 * @author Tianci
 *
 */
@WebServlet("/shouyeServlet")
public class ShouyeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		//获取部门列表
		DepartDao dd = new DepartDao();
		List<CommentObject> bumenList = dd.selectDepart();
		//获取列表显示的具体信息
		//默认的显示人事表信息(具体待讨论)
		//获取指定表的列名集合(有显示顺序)
		ActivityTableDao atd = new ActivityTableDao();
		List<CommentObject> rowNames= atd.getRowNameList(request);
		List<CommentObject> list = atd.getList(request);
		//查找表信息
		request.setAttribute("list", list);
		request.setAttribute("bumenList", bumenList);
		request.setAttribute("rowNames", rowNames);
		request.getRequestDispatcher("/shouye.jsp").forward(request, response);
	}

}
