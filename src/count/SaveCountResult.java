package count;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

@WebServlet("/saveCountResult")
public class SaveCountResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//将统计设置与统计结果保存到相应的表中
		System.out.println("保存统计结果与统计设置");
		List<CommentObject> list = (List<CommentObject>)request.getSession().getAttribute("CountResult");
		List<String> countRow = (List<String>)request.getSession().getAttribute("CountRow");
		List<String> countTj = (List<String>)request.getSession().getAttribute("CountTj");
		List<String> countRowValue = (List<String>)request.getSession().getAttribute("CountRowValue");
		List<String> countLj = (List<String>)request.getSession().getAttribute("CountLj");
		request.getSession().removeAttribute("CountResult");
		request.getSession().removeAttribute("CountRow");
		request.getSession().removeAttribute("CountTj");
		request.getSession().removeAttribute("CountRowValue");
		request.getSession().removeAttribute("CountLj");
		//获取统计的表
		String tableName = request.getSession().getAttribute("CountTable")+"";
		request.getSession().removeAttribute("CountTable");
		//获取统计的名称
		String countName = request.getParameter("countName");
		//将统计设置保存到统计设置表中
		SaveCount saveCount = new SaveCount();
		saveCount.saveCountSet(getServletName(), countName, countRow, countTj, countRowValue, countLj);
		//将统计结果保存到统计结果中
		saveCount.saveCountResult(countName, list.size());
		//跳转到统计结果页面
		response.sendRedirect("/count/showCountResult.jsp");
	}

}
