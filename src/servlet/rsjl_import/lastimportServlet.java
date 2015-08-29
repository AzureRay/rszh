package servlet.rsjl_import;

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


@WebServlet("/lastimportServlet1")
public class lastimportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{      String tableName=null;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		tableName=request.getSession().getAttribute("tableName")+"";
		 String realpath =request.getRealPath("/temporaryFile");
		DepartDao dd = new DepartDao();
		Connection connection = null;
	
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
			connection = JdbcTools.getConnection();
			for (int i = 0; i < data.size(); i++) {
				DepartDao dd1 = new DepartDao();
				dd1.write(connection, sql3, data.get(i).get(0));
			}

			connection = JdbcTools.getConnection();
			for (int j = 0; j < data.size(); j++) {
				String  sql6="select 部门  from personal where 编号=?";
				 System.out.println("qqq"+sql6);
           List bumen=dd.get(connection, sql6, data.get(j).get(0));

				String sql2 = "update "+tableName+" set 部门=? where 编号=? ";
				dd.write(connection, sql2, bumen.get(0), data.get(j).get(0));
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
			request.getRequestDispatcher("/rsjl_import/last.jsp").forward(request,response);

		} catch (Exception e) {
			request.getRequestDispatcher("/rsjl_import/fail.jsp?last=导入的数据不正确，请重新选择数据").forward(request,response);

			e.printStackTrace();
		}
	}
}
