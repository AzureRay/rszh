package LoginMacth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

import dao.Activity2Dao;
import dao.ActivityTableDao;

/**
 * Servlet implementation class LoginMatch
 */
@WebServlet("/loginMatch")
public class LoginMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		TreeMap<String,Object> User = new TreeMap();
		User.put("tableName", "匹配");
		User.put("*", "*");
		System.out.println("User: " + User);
		//得到main_table中personal的列
		List<CommentObject> Valueslist = atd.getRowListValue(User);
		System.out.println("Valueslist"+Valueslist);
		List<String> accountList =new ArrayList<String>();
		List<String> passwordList =new ArrayList<String>();
		for(int i=0 ;i<Valueslist.size();i++){
			accountList.add((String)Valueslist.get(i).getValues().get("编号"));
			passwordList.add((String)Valueslist.get(i).getValues().get("密码"));
		}
		System.out.println("accountList"+accountList);
		System.out.println("passwordList"+passwordList);
		String answer="false";
		String account =(String)request.getParameter("account");
		System.out.println("account"+account);
		String password =(String)request.getParameter("password");
		System.out.println("password"+password);
		/*if(account==null){
			account=request.getParameter("account");
		}*/
		/*if(password==null){
			password="1234";
		}*/
		System.out.println("account"+account);

		for(int i=0;i<Valueslist.size();i++){
			if(Valueslist.get(i).getValues().get("编号").equals(account)){
				if(Valueslist.get(i).getValues().get("密码").equals(password)){
					//System.out.println("qq1111"+Valueslist.get(i).getValues().get("密码"));
					answer="true";
					break;
				}
				else{
					answer="false";
				}
			}

				else {
				answer="false";
			}
		
		}
		System.out.println("answer!!!"+answer);
		
		//获取类别
		Activity2Dao a2d = new Activity2Dao();
		TreeMap<String,Object> Personmap = new TreeMap();
		String leibie="1";
		if(answer.equals("true")){
			Personmap.put("tableName", "匹配");
			Personmap.put("num", account);
			Personmap.put("rowName", "类别");
			Personmap.put("tiaojian", "编号");
			System.out.println("Personmap: " + Personmap);
			//得到main_table中personal的列
			List<CommentObject> list = a2d.getRowListValue(Personmap);
			leibie= (String)list.get(0).getValues().get("类别");
		}
		
		//将密码匹配的结果返回至首页
		request.setAttribute("answer", answer);
		request.setAttribute("leibie", leibie);
		//拼接json
		StringBuilder result = new StringBuilder();
		result.append("{")
			  .append("\"answer\":"+ answer)
			  .append(",")
			  .append("\"leibie\":"+ leibie)
			  .append("}");
		//request.getRequestDispatcher("login.jsp").forward(request, response);
		request.getSession().setAttribute("uesrName", account);
		response.setContentType("text/javascript");
		response.getWriter().print(result.toString());
	}

}
