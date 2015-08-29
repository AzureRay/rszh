package xinzeng;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ActivityTableDao;
import bean.*;
/**
 * Servlet implementation class XinzengPhoto
 */
@WebServlet("/xinzengPhoto")
public class XinzengPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String id=(String)request.getSession().getAttribute("id");
		String photo = (String)request.getParameter("fileName");
		ActivityTableDao atd = new ActivityTableDao();
		TreeMap<String, Object> Personmap = new TreeMap();
		Personmap.put("tableName", "personal");
		Personmap.put("id", id);
		Personmap.put("照片", photo);
		System.out.println("Personmap: " + Personmap);
		//得到main_table中personal的列
		atd.alter(Personmap);
		request.getRequestDispatcher("/jsp/personalPhoto/showPhoto.jsp").forward(request, response);
		//out.print(" load success");
	}

}
