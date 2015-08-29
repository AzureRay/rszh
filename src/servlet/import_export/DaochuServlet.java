package servlet.import_export;

import java.io.IOException;
import java.util.List;
import java.util.*;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import bean.CommentObject;
import dao.ActivityTableDao;

/**
 * Servlet implementation class DaochuServlet
 */
@WebServlet("/daochuServlet")
public class DaochuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		List<CommentObject> list = atd.getRowListValue(request);
		System.out.println("list: " + list);
		List<CommentObject> rowNameList = atd.getRowNameList(request.getParameter("tableName"));
		Date date = new Date();
		response.reset();
		OutputStream os4 = response.getOutputStream();
		response.setContentType("application/msexcel");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String("导出Excel.xls".getBytes("utf-8"), "utf-8"));

		WritableWorkbook wwb = null;
		try {

			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(os4);

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (wwb != null) {
			// 创建一个可写入的工作表
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("sheet1", 0);

			// 下面开始添加单元格

			try {
				for(int j = 0;j<rowNameList.size();j++){
		    		   String rowName = rowNameList.get(j).getValues().get("row_name")+"";
		    		   if(rowName.equals("id")){
		    			   rowNameList.remove(j);
		    			   break;
		    		   }
		    	   }
				for (int i = 0; i < list.size(); i++) {
			            for(int j=0;j<rowNameList.size();j++){
			            	String rowName = rowNameList.get(j).getValues().get("row_name")+"";
			            	Label labelC1 = new Label(j, 0, rowName);
							ws.addCell(labelC1);
							Label labelC = new Label(j, i + 1, (String) list
									.get(i).getValues().get(rowName));
							ws.addCell(labelC);
			            }

							
						}
				wwb.write();
				wwb.close();
				os4.flush();
				os4.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
