package servlet.renshishezhi.bmsz;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import bean.CommentObject;
import dao.DepartDao;
/**
 * 部门设置：对部门的增删改
 * 注意：需要获得部门的ID,动作类型(增，删，改)
 * 改：新的部门名
 * @author Tianci
 *
 */
@WebServlet("/bmszServlet")
public class BmszServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String ids = request.getParameter("parentId");
		int id = Integer.parseInt(ids);
		DepartDao dd = new DepartDao();
		List<CommentObject> bumenList = dd.selectDepart(id);
		request.setAttribute("bumenList", bumenList);
		request.getRequestDispatcher("/jsp/rssz/bmsz/bmsz.jsp").forward(request, response);
	}
}
