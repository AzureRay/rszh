package servlet.renshishezhi.dasz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Fenye;
import dao.MainTableDao;

/**
 * 添加行：addRow?tableName=personal&target=dasz
 */
@WebServlet("/daszAddRow")
public class AddRow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MainTableDao mtd = new MainTableDao();
		String tableName = "personal";
		String rowName = request.getParameter("row_name");
		String rowType = request.getParameter("row_type");
		String comment = request.getParameter("comment");
		mtd.addNewRow(tableName, rowName, rowType, 0, comment);
		//获取最后的分页
		int current = Fenye.getPageForRow("personal");
		request.getRequestDispatcher("/daszServlet?tableName=personal&current="+current).forward(request, response);
	}
}
