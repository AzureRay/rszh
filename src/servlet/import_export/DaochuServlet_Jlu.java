package servlet.import_export;

import java.io.IOException;
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
@WebServlet("/daochuServlet_Jlu")
public class DaochuServlet_Jlu extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		List<CommentObject> idlist = atd.getRowListValue(request);
		String tableName = request.getParameter("tableName");
		List<CommentObject> rowNameList = atd.getRowNameList(tableName);
		response.reset();
		OutputStream os4 = response.getOutputStream();
		response.setContentType("application/msexcel");
		response.addHeader("Content-Disposition", "attachment;filename="
				+ new String("导出Excel.xls".getBytes("utf-8"), "utf-8"));

		WritableWorkbook wwb = null;
		try {
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
				Label labelC1 = new Label(0, 0, "编号");
				ws.addCell(labelC1);
				Label labelC2 = new Label(1, 0, "姓名");
				ws.addCell(labelC2);
				Label labelC3 = new Label(2, 0, "部门");
				ws.addCell(labelC3);
				for (int j = 0; j < rowNameList.size(); j++) {
					String rowName = rowNameList.get(j).getValues()
							.get("row_name")
							+ "";
					if (rowName.equals("id")) {
						rowNameList.remove(j);
						break;
					}
				}
				for (int j = 0; j < rowNameList.size(); j++) {
					String rowName = rowNameList.get(j).getValues()
							.get("row_name")
							+ "";
					if (rowName.equals("编号")) {
						rowNameList.remove(j);
						break;
					}
				}
				for (int j = 0; j < rowNameList.size(); j++) {
					String rowName = rowNameList.get(j).getValues()
							.get("row_name")
							+ "";
					Label labelC4 = new Label(j + 3, 0, rowName);
					ws.addCell(labelC4);
				}

				for (int n = 0; n < idlist.size(); n++) {
					String id = idlist.get(n).getValues().get("id") + "";
					List<CommentObject> list1 = DaochuJluDao.query(tableName,
							id);
					for (int i = 0; i < list1.size(); i++) {
						CommentObject com = list1.get(i);
						Label labelc1 = new Label(0, n + 1, com.getValues()
								.get("编号") + "");
						ws.addCell(labelc1);
						Label labelc2 = new Label(1, n + 1, com.getValues()
								.get("姓名") + "");
						ws.addCell(labelc2);
						Label labelc3 = new Label(2, n + 1, com.getValues()
								.get("部门") + "");
						ws.addCell(labelc3);
					}
					for (int i = 0; i < list1.size(); i++) {
						for (int j = 0; j < rowNameList.size(); j++) {
							String rowName = rowNameList.get(j).getValues()
									.get("row_name")
									+ "";
							Label labelC = new Label(j + 3, n + 1, list1.get(i)
									.getValues().get(rowName)
									+ "");
							ws.addCell(labelC);
						}
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
