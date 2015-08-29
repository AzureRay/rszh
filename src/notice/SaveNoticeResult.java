package notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

@WebServlet("/saveNoticeResult")
public class SaveNoticeResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//将统计设置与统计结果保存到相应的表中
		System.out.println("保存统计结果与统计设置");
		List<CommentObject> list = (List<CommentObject>)request.getSession().getAttribute("NoticeResult");
		request.getSession().removeAttribute("NoticeResult");
		//获取统计的名称
		String countName = request.getParameter("noticeName");
		SaveNotice saveNotice = new SaveNotice();
		String noticeTime = request.getParameter("noticeTime");
		//将统计结果保存到统计结果中
		saveNotice.saveNoticeResult(countName, list.size(),noticeTime);
		//跳转到统计结果页面
		response.sendRedirect("/notice/showNoticeResult.jsp");
	}

}
