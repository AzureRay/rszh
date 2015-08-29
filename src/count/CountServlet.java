package count;

import gaojichaxun.GaoJiChaXun;

import java.io.IOException;
import java.sql.Connection;
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

import dao.GRT;

@WebServlet("/countServlet")
public class CountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//将所有参数放入map中
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String val = request.getParameter(name);
			map.put(name, val);
		}
		System.out.println(map);
		//获取tableName
		String tableName = request.getParameter("tableName");
		request.getSession().setAttribute("CountTable", tableName);
		
		//整理map
		ArrayList<String> rowNames = new ArrayList<String>();
		ArrayList<String> tiaojians = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		ArrayList<String> luojis = new ArrayList<String>();
		int jishu = Integer.parseInt(request.getParameter("jishu"));
		System.out.println("当前计数："+jishu);
		for(int i = 0;i <jishu;i++){
			String rowName = map.get("xuanze_"+i)+"";
			String ysf = map.get("ysf_"+i)+"";
			String value = map.get("shuru_"+i)+"";
			String luoji = map.get("luoji_"+i)+"";
			rowNames.add(rowName);
			tiaojians.add(ysf);
			values.add(value);
			luojis.add(luoji);
		}
		//输出
		System.out.println(rowNames);
		System.out.println(tiaojians);
		System.out.println(values);
		System.out.println(luojis);
		//保存到session中
		request.getSession().setAttribute("CountRow", rowNames);
		request.getSession().setAttribute("CountTj", tiaojians);
		request.getSession().setAttribute("CountRowValue", values);
		request.getSession().setAttribute("CountLj", luojis);
		//拼接where子句后面的查询条件
		StringBuilder whereString = new StringBuilder();
		for(int i = 0; i < jishu-1;i++){
			//获取字段在数据库中的类型，并作相应的处理
			String rowName = rowNames.get(i);
			String rowType = null;
			if(rowName.equals("姓名")||rowName.equals("部门")){
				rowType = GRT.getRowType("personal", rowName);
			}else{
				rowType = GRT.getRowType(tableName, rowName);
			}
			if(rowType.endsWith("文本型")||rowType.endsWith("系统型")||rowType.endsWith("选择型")){
				if(rowName.equals("编号")){
					whereString.append(" "+tableName+"."+rowNames.get(i)+" ");
				}else{
					whereString.append(" "+rowNames.get(i)+" ");
				}
				if(tiaojians.get(i).equals("等于")){
					whereString.append(" = '").append(values.get(i)).append("' ").append(luojis.get(i));
				}else if(tiaojians.get(i).equals("左匹配")){
					whereString.append(" like '").append(values.get(i)).append("%' ").append(luojis.get(i));
				}else if(tiaojians.get(i).equals("右匹配")){
					whereString.append(" like '%").append(values.get(i)).append("' ").append(luojis.get(i));
				}else if(tiaojians.get(i).equals("不等于")){
					whereString.append(" != '").append(values.get(i)).append("' ").append(luojis.get(i));
				}else {
					whereString.append(" like '%").append(values.get(i)).append("%' ").append(luojis.get(i));
				}
			}else if(rowType.endsWith("整数型")){
				whereString.append(" "+rowNames.get(i)+" ");
				if(tiaojians.get(i).equals("等于")){
					whereString.append(" = ").append(values.get(i)).append(" ").append(luojis.get(i));
				}else if(tiaojians.get(i).equals("大于")){
					whereString.append(" > ").append(values.get(i)).append(" ").append(luojis.get(i));
				}else if(tiaojians.get(i).equals("小于")){
					whereString.append(" < ").append(values.get(i)).append(" ").append(luojis.get(i));
				}else if(tiaojians.get(i).equals("不等于")){
					whereString.append(" != ").append(values.get(i)).append(" ").append(luojis.get(i));
				}else {
					whereString.append(" like %").append(values.get(i)).append("% ").append(luojis.get(i));
				}
			}else if(rowType.endsWith("日期型")){
				whereString.append(" "+rowNames.get(i)+" ");
				if(tiaojians.get(i).equals("等于")){
					whereString.append(" = '").append(values.get(i)).append("' ").append(luojis.get(i));
				}else if(tiaojians.get(i).equals("左匹配")){
					whereString.append(" like '").append(values.get(i)).append("%' ").append(luojis.get(i));
				}else if(tiaojians.get(i).equals("右匹配")){
					whereString.append(" like '%").append(values.get(i)).append("' ").append(luojis.get(i));
				}else if(tiaojians.get(i).equals("不等于")){
					whereString.append(" != '").append(values.get(i)).append("' ").append(luojis.get(i));
				}else {
					whereString.append(" like '%").append(values.get(i)).append("%' ").append(luojis.get(i));
				}
			}
		}
		//拼接最后一条
		String rowName = rowNames.get(jishu-1);
		String rowType = null;
		if(rowName.equals("姓名")||rowName.equals("部门")){
			rowType = GRT.getRowType("personal", rowName);
		}else{
			rowType = GRT.getRowType(tableName, rowName);
		}
		if(rowType.endsWith("文本型")||rowType.endsWith("系统型")||rowType.endsWith("选择型")){
			if(rowName.equals("编号")){
				whereString.append(" "+tableName+"."+rowNames.get(jishu-1)+" ");
			}else{
				whereString.append(" "+rowNames.get(jishu-1)+" ");
			}
			if(tiaojians.get(jishu-1).equals("等于")){
				whereString.append(" = '").append(values.get(jishu-1)).append("' ");
			}else if(tiaojians.get(jishu-1).equals("左匹配")){
				whereString.append(" like '").append(values.get(jishu-1)).append("%' ");
			}else if(tiaojians.get(jishu-1).equals("右匹配")){
				whereString.append(" like '%").append(values.get(jishu-1)).append("' ");
			}else if(tiaojians.get(jishu-1).equals("不等于")){
				whereString.append(" != '").append(values.get(jishu-1)).append("' ");
			}else {
				whereString.append(" like '%").append(values.get(jishu-1)).append("%' ");
			}
		}else if(rowType.endsWith("整数型")){
			whereString.append(" "+rowNames.get(jishu-1)+" ");
			if(tiaojians.get(jishu-1).equals("等于")){
				whereString.append(" = ").append(values.get(jishu-1));
			}else if(tiaojians.get(jishu-1).equals("大于")){
				whereString.append(" > ").append(values.get(jishu-1));
			}else if(tiaojians.get(jishu-1).equals("小于")){
				whereString.append(" < ").append(values.get(jishu-1));
			}else if(tiaojians.get(jishu-1).equals("不等于")){
				whereString.append(" != ").append(values.get(jishu-1));
			}else {
				whereString.append(" like %").append(values.get(jishu-1)).append("% ");
			}
		}else if(rowType.endsWith("日期型")){
			whereString.append(" "+rowNames.get(jishu-1)+" ");
			if(tiaojians.get(jishu-1).equals("等于")){
				whereString.append(" = '").append(values.get(jishu-1)).append("' ");
			}else if(tiaojians.get(jishu-1).equals("左匹配")){
				whereString.append(" like '").append(values.get(jishu-1)).append("%' ");
			}else if(tiaojians.get(jishu-1).equals("右匹配")){
				whereString.append(" like '%").append(values.get(jishu-1)).append("' ");
			}else if(tiaojians.get(jishu-1).equals("不等于")){
				whereString.append(" != '").append(values.get(jishu-1)).append("' ");
			}else {
				whereString.append(" like '%").append(values.get(jishu-1)).append("%' ");
			}
		}
		System.out.println("where子句："+whereString);
		//拼接查询的sql语句
		StringBuilder sql = new StringBuilder();
		if(tableName.equals("personal")){
			sql.append("select * from ")
			   .append(tableName)
			   .append("  where")
			   .append(whereString)
			   .append(";");
		}else{
			sql.append("select 姓名,部门 ,")
			   .append(tableName+".* from personal,")
			   .append(tableName)
			   .append("  where")
			   .append(whereString)
			   .append(" and personal.编号=")
			   .append(tableName+".编号")
			   .append(";");
		}
		System.out.println("sql语句："+sql);
		//获取数据库表中的记录
		GaoJiChaXun gaoJiChaXun = new GaoJiChaXun();
		List<CommentObject> list = gaoJiChaXun.getList(sql+"");
		//转发
		request.setAttribute("list", list);
		request.getSession().setAttribute("CountResult", list);
		request.getRequestDispatcher("/count/showCount.jsp").forward(request, response);
	}

}
