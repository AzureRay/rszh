package servlet.renshishezhi.bmsz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DepartDao;
import dao.MainTableDao;

@WebServlet("/bmDao")
public class BmDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		DepartDao dd = new DepartDao();
		String parentId = request.getParameter("parentId");
		System.out.println("部门操作");
		if(action.equals("add")){
			int id = Integer.parseInt(parentId);
			String departName = request.getParameter("name");
			dd.addDepart(id, departName);
		}else if(action.equals("del")){
			String ids = request.getParameter("id");
			int id = Integer.parseInt(ids);
			dd.deleteDepart(id);
		}else if(action.equals("mod")){
			String ids = request.getParameter("id");
			int id = Integer.parseInt(ids);
			String departName = request.getParameter("departName");
			dd.updateDao(id, departName);
		}else if(action.equals("up")){
			String ids = request.getParameter("id");
			int id = Integer.parseInt(ids);
			System.out.println("上移");
			dd.upMove(id);
		}else if(action.equals("down")){
			String ids = request.getParameter("id");
			int id = Integer.parseInt(ids);
			dd.downMove(id);
		}
		request.getRequestDispatcher("/bmszServlet?parentId="+parentId).forward(request, response);
	}
}
