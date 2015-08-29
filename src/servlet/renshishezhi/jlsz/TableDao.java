package servlet.renshishezhi.jlsz;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MainTableDao;

/**
 * 对记录表的增删改
 * @author Tianci
 *
 */
@WebServlet("/tableDao")
public class TableDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MainTableDao mtd = new MainTableDao();
		String tableName = request.getParameter("tableName");
		String action = request.getParameter("action");
		if(action.equals("up")){
			System.out.println("上移");
			mtd.bigUpMove(tableName);
		}else if(action.equals("down")){
			System.out.println("下移");
			mtd.bigdownMove(tableName);
		}else if(action.equals("mod")){
			String oldTableName = request.getParameter("oldTableName");
			System.out.println("修改表名"+" :"+oldTableName+" "+tableName);
			mtd.alterTable(oldTableName, tableName);
		}else if(action.equals("del")){
			System.out.println("删除表 ："+tableName);
			mtd.dropTable(tableName);
		}
		System.out.println("tableDao被调用");
		//获取
		Object current = request.getSession().getAttribute("jlszCurrent");
		request.getRequestDispatcher("/jlszServlet?current="+current).forward(request, response);
		
	}

}
