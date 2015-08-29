package servlet.dayin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.GetMapUtil;
import bean.CommentObject;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.ActivityTableDao;

/**
 * Servlet implementation class DayinBaoLastServlet
 */
@WebServlet("/dayinBaoLastServlet")
public class DayinBaoLastServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		List<CommentObject> list = atd.getRowListValue(request);
		System.out.println("list: " + list);
		Map<String, Object> rowsMap = GetMapUtil.getRequestMap(request);
		//表名
        String tableName = (String)rowsMap.get("tableName");
		rowsMap.remove("tableName");
		System.out.println("rowsMap: " + rowsMap);
		response.reset();
		response.setContentType("application/pdf");
		Document doc = new Document();
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			BaseFont chinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font cn = new Font(chinese, 12, Font.NORMAL);
			PdfWriter.getInstance(doc, os);
			doc.open();
			List<String> rowValuesList = new ArrayList();
			List<CommentObject> EveryRowValues=new ArrayList();
			PdfPTable table = new PdfPTable(1);
			PdfPCell cell = new PdfPCell();
			Paragraph par = new Paragraph();
			for (String rowName : rowsMap.keySet()) {
				// 添加列名
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("tableName", tableName);
				map.put(rowName, "");
				EveryRowValues = atd.getRowListValue(map);//{[{rowName:zhi}],[{rowName:zhi}],[{rowName:zhi}]}
				table = new PdfPTable(EveryRowValues.size()+1);
				System.out
				.println("EveryRowValues:  ^^ff" + EveryRowValues);
				// 添加列名中相应的值
				// 先将每一列中的值
			}
			for (String rowName : rowsMap.keySet()) {
				// 添加列名
				cell = new PdfPCell();
				System.out.println("row:  ^^" + rowName);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("tableName", tableName);
				map.put(rowName, "");
				EveryRowValues = atd.getRowListValue(map);
				par = new Paragraph(rowName, cn);
				cell.addElement(par);
				table.addCell(par);
					for(int m=0;m<(EveryRowValues.size());m++){
						cell = new PdfPCell();
						par = new Paragraph((String)EveryRowValues.get(m).getValues().get(rowName), cn);
						cell.addElement(par);
						table.addCell(par);
					}
			}
			doc.add(table);
			doc.close();
			ServletOutputStream out = response.getOutputStream();
			os.writeTo(out);
			os.flush();

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
