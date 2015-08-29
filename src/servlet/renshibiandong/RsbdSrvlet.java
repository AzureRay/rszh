package servlet.renshibiandong;

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
import dao.Fenye;
import dao.MainTableDao;

/**
 * 人事变动
 * @author Tianci
 *
 */
@WebServlet("/rsbdServlet")
public class RsbdSrvlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doPost(request, response);
		}
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			ActivityTableDao atd = new ActivityTableDao();
			String tableName=request.getParameter("tableName");
			List<CommentObject> list1 = atd.getRowNameList(tableName);
			String bumen = request.getParameter("部门");
			if(bumen == null){
				bumen = "公司";
			}
			List<CommentObject> list = null;
			List<CommentObject> datalist = null;
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("tableName", tableName);
			System.out.println("BUmen :"+bumen);
			if(bumen.equals("公司")){
				list = atd.getListWithWhere(map);
				datalist = atd.getListWithWhere(map);
			}else{
				list = atd.getListWithWhere(request);
				datalist = atd.getListWithWhere(request);
			}
			request.setAttribute("list1", list1);
			request.setAttribute("list", list);
			System.out.println("list"+list);
			System.out.println("datalist"+datalist);
			request.getRequestDispatcher("/jsp/rsbd/bmbd/rsbd.jsp?tableName=personal").forward(request, response);
		}


}
