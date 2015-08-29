package tongji;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

import dao.ActivityTableDao;
import dao.DepartDao;

@WebServlet("/tongJiRequest")
public class TongJiRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//1.获得人员类别
		ActivityTableDao atd = new ActivityTableDao();
		List<String> renyuanleibie = atd.getSelectRowValueList("人员类别");
		//2.获得部门
		DepartDao ddDao = new DepartDao();
		List<CommentObject> departs = ddDao.selectDepart();
		//3.获得其他字段
		atd.getRowNameList("personal");
	}

}
