package tongji;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

import dao.ActivityTableDao;
import dao.DepartDao;

@WebServlet("/tongjiServlet")
public class TongjiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("统计");
		List<String> rowNames = new ArrayList<String>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String val = request.getParameter(name);
			rowNames.add(val);
		}
		System.out.println(rowNames);
		for(int i =0;i<rowNames.size();i++){
			if(rowNames.get(i).equals("人员类别")){
				rowNames.remove(i);
				continue;
			}
			if(rowNames.get(i).equals("部门")){
				rowNames.remove(i);
				continue;
			}
		}
		//将人员类别插到第一个位置
		rowNames.add(0, "人员类别");
		//判断选择的列中是否有选择列，如果有则从数据库中获取选择列值
		ActivityTableDao atd = new ActivityTableDao();
		//存放选择列的值
		List<CommentObject> selectValue = new ArrayList<CommentObject>();
		//用于判断选择列的Map
		Map<String, String> isSelectMap = new HashMap<String, String>();
 		for(int i =0;i<rowNames.size();i++){
			String name = rowNames.get(i);
			System.out.println("^^:"+name);
			if(atd.isSelectRow(name)){
				List<CommentObject> list = atd.getSelectRowValueListWithName(name);
				for(int j=0;j<list.size();j++){
					selectValue.add(list.get(j));
				}
				isSelectMap.put(name, name);
				System.out.println("^^^^:"+isSelectMap);
			}
		}
		//部门
		DepartDao ddDao = new DepartDao();
		List<CommentObject> bumenList = ddDao.selectDepart();
		request.setAttribute("bumenList", bumenList);
		request.setAttribute("selectValue", selectValue);
		request.setAttribute("rowNames", rowNames);
		request.setAttribute("isSelectMap", isSelectMap);
		request.getRequestDispatcher("/jsp/tongji/tongji.jsp").forward(request, response);
	}

}
