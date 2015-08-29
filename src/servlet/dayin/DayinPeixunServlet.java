package servlet.dayin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class DayinPeixunServlet
 */
@WebServlet("/dayinPeixunServlet")
public class DayinPeixunServlet extends HttpServlet {
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
		
		//从前一页面获取编号
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
		//得到培训记录中培训时间和培训名称所单独对应的值
		TreeMap<String,Object> PeiNamemap = new TreeMap();
		//得传参
		PeiNamemap.put("tableName", "培训记录 ");
		PeiNamemap.put("num", bianhao);
		PeiNamemap.put("rowName", "*");
		PeiNamemap.put("tiaojian", "编号");
		System.out.println("PeiNamemap: " + PeiNamemap);
		//得到main_table中personal的列
		//修改了
		List<CommentObject> Valueslist = atd.getRowListValue(Personmap);
		System.out.println("Valueslist: " + Valueslist);
		List<String> peixunNameList = new ArrayList<String>();
		List<String> peixunTimeList = new ArrayList<String>();
		//新加的
		List<CommentObject> Valueslist1 = atd.getRowListValue(PeiNamemap);
		for(int i=0;i<Valueslist1.size();i++){
			peixunNameList.add((String)Valueslist1.get(i).getValues().get("培训名称"));
			peixunTimeList.add((String)Valueslist1.get(i).getValues().get("培训时间"));
		}
		System.out.println("peixunNameList: " + peixunNameList);
		System.out.println("peixunNameList: " + peixunNameList);
		List<String> Mainlist = atd.getRowNamesOrderByRowIndex("personal");
		System.out.println("Mainlist: " + Mainlist);
		//调用Activity2Dao中的getPerson(),与Mainlist中的值相比，如果存在列值，则添加对应的值
		List<String> getPersonList = atd.getPerson();
		List<String> getDanweiList = atd.getDanwei();
		List<String> getPeixunList = atd.getPeixun();
		response.reset();
		response.setContentType("application/pdf");
		//Document doc = new Document();
		Document doc = new Document(PageSize.A4); 
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PdfWriter.getInstance(doc, os);
		doc.open();
		Paragraph par =new Paragraph("个人简历",new Font(chinese,14, Font.NORMAL));
		par.setLeading(50f);
		par.setAlignment(Element.ALIGN_CENTER);
		doc.add(par);
		doc.add(new Paragraph(" "));
		PdfPTable table = new PdfPTable(5);
		float[] width={0.1464f,0.207f,0.155f,0.20f,0.15f};
		table.setWidths(width);
		PdfPCell cell = new PdfPCell();
		par = new Paragraph("<个人情况>",cn);
		cell.setBackgroundColor(BaseColor.GRAY); 
		cell.addElement(par);
		cell.setColspan(5);
		cell.setPadding(2);
		table.addCell(cell);
		for(int i=0;i<2;i++){
			for(int j=0;j<Mainlist.size();j++){
				if(getPersonList.get(i).equals(Mainlist.get(j))){
					    cell =new PdfPCell();
					    cell.setPadding(3);
					    cell.setUseAscender(true);
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						par = new Paragraph(getPersonList.get(i), cn);
						par.setLeading(14f);
                        cell.addElement(par);
						table.addCell(cell);
						String valueString =(String)Valueslist.get(0).getValues().get(getPersonList.get(i));
						par = new Paragraph(valueString, cn);
						par.setLeading(14f);
						System.out.println("valueString"+valueString);
;						table.addCell(par);
					
				}
			}
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
		
		
		for(int i=3;i<14;i++){
			cell =new PdfPCell();
			par = new Paragraph(getPersonList.get(i), cn);
			par.setLeading(14f);
			cell.addElement(par);
			table.addCell(cell);
			for(int j=0;j<Mainlist.size();j++){
				if((!getPersonList.get(i).equals(Mainlist.get(j)))&&(j==(Mainlist.size()))){
					par = new Paragraph(" ", cn);
					table.addCell(par);	
				}
			}
			String valueString =(String)Valueslist.get(0).getValues().get(getPersonList.get(i));
			par = new Paragraph(valueString, cn);
			//System.out.println("valueString"+valueString);
			table.addCell(par);
			}
		doc.add(table);
		table = new PdfPTable(4);
		float[] widths={0.10f,0.14f,0.105f,0.238f};
		table.setWidths(widths);
		for(int i=14;i<getPersonList.size();i++){
			par = new Paragraph(getPersonList.get(i), cn);
			par.setLeading(14f);
			//System.out.println("getPersonList"+i+getPersonList.get(i));
			//System.out.println("getPersonList23"+getPersonList.get(23));
			table.addCell(par);
			for(int j=0;j<Mainlist.size();j++){
				if((!getPersonList.get(i).equals(Mainlist.get(j)))&&(j==(Mainlist.size()))){
					par = new Paragraph(" ", cn);
					table.addCell(par);	
				}
			}
			String valueString =(String)Valueslist.get(0).getValues().get(getPersonList.get(i));
			par = new Paragraph(valueString, cn);
			System.out.println("valueString"+valueString);
			table.addCell(par);
			}
			
		
		par = new Paragraph("家庭地址", cn);
		par.setLeading(14f);
		table.addCell(par);
		par = new Paragraph((String)Valueslist.get(0).getValues().get(getPersonList.get(23)), cn);
		par.setLeading(14f);
		cell = new PdfPCell();
		cell.addElement(par);
		cell.setColspan(3);
		table.addCell(cell);
		doc.add(table);
		//单位信息
		table = new PdfPTable(4);
		float[] wid={0.10f,0.14f,0.105f,0.238f};
		table.setWidths(wid);
		cell = new PdfPCell();
		par = new Paragraph("<单位信息>",cn);
		par.setAlignment(Element.ALIGN_MIDDLE);
		cell.setBackgroundColor(BaseColor.GRAY); 
		par.setLeading(14f);
		cell.addElement(par);
		cell.setColspan(4);
		table.addCell(cell);
		for(int i=0;i<getDanweiList.size();i++){
			par = new Paragraph(getDanweiList.get(i), cn);
			par.setLeading(14f);
			//System.out.println("getDanweiList"+i+getDanweiList.get(i));
			//System.out.println("getDanweiList"+getPersonList.get(23));
			table.addCell(par);
			for(int j=0;j<Mainlist.size();j++){
				if((!getDanweiList.get(i).equals(Mainlist.get(j)))&&(j==(Mainlist.size()))){
					par = new Paragraph(" ", cn);
					table.addCell(par);	
				}
			}
			String valueString =(String)Valueslist.get(0).getValues().get(getDanweiList.get(i));
			par = new Paragraph(valueString, cn);
			//System.out.println("valueString"+valueString);
			table.addCell(par);
			
		}
		
		
		doc.add(table);
	
		table = new PdfPTable(4);
		float[] widt={0.14f,0.20f,0.31f,0.17f};
		table.setWidths(widt);
		cell = new PdfPCell();
		par = new Paragraph("<培训记录>",cn);
		par.setAlignment(Element.ALIGN_MIDDLE);
		par.setLeading(13f);
		cell.addElement(par);
		cell.setColspan(4);
		cell.setBackgroundColor(BaseColor.GRAY); 
		table.addCell(cell);
		table.addCell(new Paragraph(" "));
		for(int i=0;i<getPeixunList.size();i++){
			cell =new PdfPCell();
			par = new Paragraph(getPeixunList.get(i), cn);
			par.setLeading(14f);
			par.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(par);
			table.addCell(cell);
		}
		
		
		table.addCell(new Paragraph(" "));
		cell =new PdfPCell();
		table.addCell(cell);
		
		int leng=peixunNameList.size();
		if(leng>3)
		{
			leng=3;
			for(int i=0;i<leng;i++){
				cell=new PdfPCell();
				Paragraph p1 = new Paragraph(" ", cn);
				Paragraph p2 = new Paragraph(peixunNameList.get(i), cn);
				Paragraph p3 =new Paragraph(peixunTimeList.get(i), cn);
				Paragraph p4 = new Paragraph(" ", cn);
				cell.addElement(p2);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.addElement(p2);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.addElement(p1);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.addElement(p4);
				table.addCell(cell);
			}
			
		}else{
			for(int i=0;i<peixunNameList.size();i++){
				cell=new PdfPCell();
				Paragraph p1 = new Paragraph(" ", cn);
				Paragraph p2 = new Paragraph(peixunNameList.get(i), cn);
				Paragraph p3 =new Paragraph(peixunTimeList.get(i), cn);
				Paragraph p4 = new Paragraph(" ", cn);
				cell.addElement(p2);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.addElement(p2);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.addElement(p1);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.addElement(p4);
				table.addCell(cell);
			}
			for(int i=peixunNameList.size();i<3;i++){
				cell=new PdfPCell();
				Paragraph p1 = new Paragraph(" ", cn);
				Paragraph p2 = new Paragraph(" ", cn);
				Paragraph p3 =new Paragraph(" ", cn);
				Paragraph p4 = new Paragraph(" ", cn);
				cell.addElement(p2);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.addElement(p2);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.addElement(p1);
				table.addCell(cell);
				cell = new PdfPCell();
				cell.addElement(p4);
				table.addCell(cell);
			}
		}
		
		//修改了的
		/*for(int i=0;i<leng;i++){
			cell=new PdfPCell();
			Paragraph p1 = new Paragraph(" ", cn);
			Paragraph p2 = new Paragraph(peixunNameList.get(i), cn);
			Paragraph p3 =new Paragraph(peixunTimeList.get(i), cn);
			Paragraph p4 = new Paragraph(" ", cn);
			cell.addElement(p2);
			table.addCell(cell);
			cell = new PdfPCell();
			cell.addElement(p2);
			table.addCell(cell);
			cell = new PdfPCell();
			cell.addElement(p1);
			table.addCell(cell);
			cell = new PdfPCell();
			cell.addElement(p4);
			table.addCell(cell);
		}*/
		
		
		
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
