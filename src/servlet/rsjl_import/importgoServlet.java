package servlet.rsjl_import;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

import bean.CommentObject;
import dao.DepartDao;


@WebServlet("/importgoServlet1")
public class importgoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String flag = request.getParameter("flag");
		DepartDao dd = new DepartDao();
		List<CommentObject> bumenList = dd.selectDepart();
		//String name=request.getParameter("部门");	
		//request.setAttribute("departName",name);
		//request.setAttribute("bumenList", bumenList);
		System.out.println("importgoServlet.java");
		request.getRequestDispatcher("/lastimportServlet1").forward(request,response);
	}

}
