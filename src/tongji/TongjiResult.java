package tongji;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

import util.GetMapUtil;

import dao.ActivityTableDao;
import dao.MainTableDao;

@WebServlet("/tongjiResult")
public class TongjiResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		Map<String, Object> params= GetMapUtil.getRequestMap(request);
		//移除统计名称
		params.remove("tongjiName");
		//添加指定表
		params.put("tableName", "personal");
		//查询指定记录
		List<CommentObject> list = atd.getList(params);
		//将统计设置的字段插入到统计设置里面
		Tongji tj = new Tongji();
		tj.insertTongjiSet(request);
		//将personal表中的列得到
		MainTableDao mtd = new MainTableDao();
		List<CommentObject> names = atd.getRowNameList("personal");
		//转发
		request.setAttribute("list", list);
		request.setAttribute("names", names);
		request.getRequestDispatcher("/jsp/tongji/tongjiResult.jsp").forward(request, response);
	}

}
