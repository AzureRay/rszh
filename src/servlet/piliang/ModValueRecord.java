package servlet.piliang;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ActivityTableDao;

import util.GetMapUtil;

@WebServlet("/modValueRecord")
public class ModValueRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		List<Object> idList = (List<Object>)request.getSession().getAttribute("idList");
		Map<String, Object> map = GetMapUtil.getRequestMap(request);
		System.out.println("再次确认信息：" + idList);
		System.out.println("再次确认信息：" + map);
		Object current = request.getSession().getAttribute("personalCurrent");
		//清除session
		request.getSession().removeAttribute("idList");
		request.getSession().removeAttribute("personalCurrent");
		//进行批量修改
		ActivityTableDao atd = new ActivityTableDao();
		atd.modMoreByOnce(idList, map, "personal");
		request.getRequestDispatcher("/showPersonal?current="+current).forward(request, response);
	}

}
