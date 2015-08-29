package servlet.piliang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;
import dao.ActivityTableDao;

@WebServlet("/moreRecordDao")
public class MoreRecordDao extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		Object personalCurrent = request.getParameter("personalCurrent");
		System.out.println("personalCurrent:"+personalCurrent);
		//对表记录的批量修改某一字段的值
		//1.获取要修改的人员的id
		List<Object> idList = new ArrayList<Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			if(name.equals("personalCurrent")){
				continue;
			}
			Object val = request.getParameter(name);
			idList.add(val);
		}
		System.out.println("人员的id："+idList);
		for(int i = 0;i<idList.size();i++){
			String nnString = idList.get(i)+"";
			if(nnString.equals("批量修改")){
				idList.remove(i);
				break;
			}
		}
		for(int i = 0;i<idList.size();i++){
			String nnString = idList.get(i)+"";
			if(nnString.equals("删除")){
				idList.remove(i);
				break;
			}
		}
		//批量删除的操作
		String delString = request.getParameter("del");
		if(delString != null){
			System.out.println("删除前确认删除的id： "+idList);
			System.out.println("进行删除操作");
			atd.delMoreByOnce(idList, "personal");
			request.getRequestDispatcher("/showPersonal?current="+personalCurrent).forward(request, response);
		}else{
			//若不是删除则进行修改的批量操作
			request.getSession().setAttribute("personalCurrent", personalCurrent);
			System.out.println("^^人员的id："+idList);
			request.getSession().setAttribute("idList", idList);
			//1.获取personal表中所有的列信息
			List<CommentObject> rowList = atd.getRowNameList("personal");
			request.setAttribute("rowList",rowList );
			request.getRequestDispatcher("/jsp/modMoreSelectRow.jsp").forward(request, response);
		}
	}
}
