package servlet.modPass;

import java.io.IOException;
import java.sql.Connection;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;


import jdbc.JdbcTools;
import dao.Dao;

@WebServlet("/modpass")
public class Modpass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	String bianhao=request.getParameter("user");
	String oldpass=request.getParameter("oldPass");
	String newpass=request.getParameter("newPass");
	String newPassAgain=request.getParameter("newPassAgain");
	System.out.println(bianhao);
	System.out.println(oldpass);
	System.out.println(newpass);
	System.out.println(newPassAgain);
	Dao dao = new Dao();
	Connection connection=null;
	List<CommentObject>  list =new ArrayList<CommentObject> ();
	try {
		connection = JdbcTools.getConnection();
		String sql="select 密码  from 匹配  where 编号=? ";
		System.out.println(sql);
		list=(List<CommentObject> ) dao.get(connection, sql, bianhao);
		System.out.println("list:"+list);

		System.out.println("得到的密码："+list.get(0).getValues().get("密码"));
		System.out.println("oldpass："+oldpass);
		if(!list.get(0).getValues().get("密码").equals(oldpass)){
			System.out.print(!list.get(0).getValues().get("密码").equals(oldpass));
			String fail="用户账户或密码错误";
			request.getRequestDispatcher("/modpass/password.jsp?fail="+fail).forward(request, response);
		}
		else{
			if(newpass.equals(newPassAgain)){
				String sql1="update 匹配   set 密码= ? where 编号=?";
				dao.write(connection, sql1, newpass,bianhao);
				String fail="密码修改成功";
				request.getRequestDispatcher("/modpass/password.jsp?fail="+fail).forward(request, response);
			}
			else{

				String fail="您输入的两次密码不相同，请重新输入";
				request.getRequestDispatcher("/modpass/password.jsp?fail="+fail).forward(request, response);
			}
			
		}
	
	} catch (Exception e) {

		e.printStackTrace();
	}
}

}
