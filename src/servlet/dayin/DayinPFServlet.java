package servlet.dayin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;
import dao.ActivityTableDao;

/**
 * Servlet implementation class DayinPFServlet
 */
@WebServlet("/dayinPFServlet")
public class DayinPFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		String tableName=request.getParameter("tableName");
		List<CommentObject> list1 = atd.getRowNameList(tableName);
		List<CommentObject> list = atd.getListWithWhere(request);
		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("tableName", tableName);
		List<CommentObject> datalist = atd.getListWithWhere(map);
		request.setAttribute("list1", list1);
		request.setAttribute("list", list);
		System.out.println("list"+list);
		System.out.println("datalist"+datalist);
		request.getRequestDispatcher("/jsp/dayin/rsbdpx.jsp?tableName=personal").forward(request, response);
	}

}
