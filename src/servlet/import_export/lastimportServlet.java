package servlet.import_export;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import jdbc.JdbcTools;
import dao.DepartDao;


@WebServlet("/lastimportServlet")
public class lastimportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{      String tableName=null;
		request.setCharacterEncoding("UTF-8");
		tableName=request.getSession().getAttribute("tableName")+"";
		 String realpath =request.getRealPath("/temporaryFile");
		String departname = request.getParameter("departname");
		String rylb = request.getParameter("人员类别");
		System.out.println("rylb!!!!"+rylb);
		DepartDao dd = new DepartDao();
	
	
		List<Vector> data = (List<Vector>) request.getSession().getAttribute(
				"data");
		System.out.println("session:"
				+ request.getSession().getAttribute("data"));
	
		List<Vector> columnName = (List<Vector>) request.getSession()
				.getAttribute("columnName");
		System.out.println("session:"
				+ request.getSession().getAttribute("columnName"));
		
		String sql3 = "insert into "+tableName+"(编号) values(?)";
		System.out.println(sql3);
		try {
			Connection connection = null;
			connection = JdbcTools.getConnection();
			for (int i = 0; i < data.size(); i++) {
				DepartDao dd1 = new DepartDao();
				dd1.write(connection, sql3, data.get(i).get(0));
			}

			connection = JdbcTools.getConnection();
			for (int j = 0; j < data.size(); j++) {
				String sql2 = "update "+tableName+" set 部门=? where 编号=? ";
				String sql5= "update "+tableName+" set 人员类别=? where 编号=? ";
				dd.write(connection, sql2, departname, data.get(j).get(0));
				dd.write(connection, sql5, rylb, data.get(j).get(0));
				for (int m1 = 1; m1 < columnName.size(); m1++) {
					String sql = "update "+tableName+" set " + columnName.get(m1)
							+ "=? where 编号=? ";
					System.out.println(sql);
					dd.write(connection, sql, data.get(j).get(m1), data.get(j)
							.get(0));
				}
			}
		importDao idd=new importDao();
	        idd.removeAll( new File(realpath));
			request.getRequestDispatcher("/import_export/last.jsp").forward(request,response);

		} catch (Exception e) {
			request.getRequestDispatcher("/import_export/fail.jsp?last=导入的数据不正确，请重新选择数据").forward(request,response);

			e.printStackTrace();
		}
	}
}
