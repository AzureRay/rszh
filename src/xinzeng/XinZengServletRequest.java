package xinzeng;

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

@WebServlet("/xinzengServletRequest")
public class XinZengServletRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println("新增");
		List<String> rowNames = new ArrayList<String>();
		ActivityTableDao atd = new ActivityTableDao();
		List<CommentObject> pRows = atd.getRowNameList("personal");
		//去除id
		for(int i=0;i<pRows.size();i++){
			String nameString = pRows.get(i).getValues().get("row_name")+"";
			if(nameString.equals("id")){
				pRows.remove(i);
				break;
			}
		}
		for(int i =0;i<pRows.size();i++){
			rowNames.add(pRows.get(i).getValues().get("row_name")+"");
		}
		System.out.println(rowNames);
		//判断选择的列中是否有选择列，如果有则从数据库中获取选择列值
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
		request.getRequestDispatcher("/jsp/xinzeng/xinzeng.jsp").forward(request, response);
	}

}
