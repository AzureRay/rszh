package servlet.dayin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CommentObject;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.Activity2Dao;
import dao.ActivityTableDao;

/**
 * Servlet implementation class DayinChangServlet
 */
@WebServlet("/dayinChangServlet")
public class DayinChangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BaseFont chinese;
		try {
			chinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font cn = new Font(chinese, 11, Font.NORMAL);
		request.setCharacterEncoding("utf-8");
		Activity2Dao atd = new Activity2Dao();
		//得到personal中各列及其所对应的值
		TreeMap<String,Object> Personmap = new TreeMap();
		
		ActivityTableDao atdd = new ActivityTableDao();
		List<CommentObject> list = atdd.getListWithWhere(request);
		System.out.println("list***********"+list);
		
		String bianhao = (String)list.get(0).getValues().get("编号");
		System.out.println("编号****"+bianhao);
		
		Personmap.put("tableName", "personal");
		Personmap.put("num", bianhao);
		Personmap.put("rowName", "*");
		Personmap.put("tiaojian", "编号");
		System.out.println("Personmap: " + Personmap);
		//得到main_table中personal的列
		List<CommentObject> Valueslist = atd.getRowListValue(Personmap);
		System.out.println("Valueslist: " + Valueslist);
		List<String> Mainlist = atd.getRowNamesOrderByRowIndex("personal");
		System.out.println("Mainlist: " + Mainlist);
		//调用Activity2Dao中的getPerson(),与Mainlist中的值相比，如果存在列值，则添加对应的值
		List<String> getChangList = atd.getChangpai();
		response.reset();
		response.setContentType("application/pdf");
		//Document doc = new Document();
		Document doc = new Document(PageSize.A4,10,120,50,10);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PdfWriter.getInstance(doc, os);
		doc.open();
		PdfPCell cell =new PdfPCell();
		Paragraph par =new Paragraph("公司名称",new Font(chinese,14, Font.NORMAL));
		par.setLeading(50f);
		par.setAlignment(Element.ALIGN_CENTER);
		doc.add(par);
		doc.add(new Paragraph(" "));
		PdfPTable table = new PdfPTable(5);
		float[] width={0.1464f,0.20f,0.17f,0.20f,0.20f};
		table.setWidths(width);
		for(int i=0;i<2;i++){
			cell =new PdfPCell();
			par = new Paragraph(getChangList.get(i), cn);
			par.setLeading(14f);
			cell.addElement(par);
			cell.setPadding(10);
			table.addCell(cell);
			for(int j=0;j<Mainlist.size();j++){
				if((!getChangList.get(i).equals(Mainlist.get(j)))&&(j==(Mainlist.size()))){
					par = new Paragraph(" ", cn);
					cell =new PdfPCell();
					cell.addElement(par);
					cell.setPadding(10);
					table.addCell(cell);	
				}
			}
			String valueString =(String)Valueslist.get(0).getValues().get(getChangList.get(i));
			par = new Paragraph(valueString, cn);
			cell =new PdfPCell();
			cell.addElement(par);
			cell.setPadding(10);
			System.out.println("valueString"+valueString);
			table.addCell(cell);
			}
		
		String Gerenbianhao=(String)Valueslist.get(0).getValues().get("照片");
		if(!Gerenbianhao.equals("0")){
			String filePath=request.getSession().getServletContext().getRealPath("/photo/"+Gerenbianhao);
			Image jpg=Image.getInstance(filePath);
			jpg.scaleAbsolute(72.7f, 108.7f);
			cell=new PdfPCell(jpg);
			cell.setColspan(2);
			cell.setRowspan(6);
			table.addCell(cell);
		}else {
			par = new Paragraph(" ",cn);
			cell=new PdfPCell(par);
			cell.setColspan(2);
			cell.setRowspan(6);
			table.addCell(cell);
		}
		
		for(int i=2;i<getChangList.size();i++){
			cell =new PdfPCell();
			par = new Paragraph(getChangList.get(i), cn);
			cell.addElement(par);
			cell.setPadding(10);
			table.addCell(cell);
			for(int j=0;j<Mainlist.size();j++){
				if((!getChangList.get(i).equals(Mainlist.get(j)))&&(j==(Mainlist.size()))){
					par = new Paragraph(" ", cn);
					cell =new PdfPCell();
					cell.addElement(par);
					cell.setPadding(10);
					table.addCell(par);	
				}
			}
			String valueString =(String)Valueslist.get(0).getValues().get(getChangList.get(i));
			par = new Paragraph(valueString, cn);
			cell =new PdfPCell();
			cell.addElement(par);
			cell.setPadding(10);
			System.out.println("valueString"+valueString);
			table.addCell(cell);
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
