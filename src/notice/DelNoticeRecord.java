package notice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delNoticeRecord")
public class DelNoticeRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		List<Object> list = new ArrayList<Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			Object val = request.getParameter(name);
			list.add(val);
		}
		System.out.println("要删除的id："+list);
		//删除操作
		SaveNotice saveNotice = new SaveNotice();
		saveNotice.delNoticeRecord(list);
		//转发到
		response.sendRedirect("/notice/showNoticeResult.jsp");
		
	}

}
