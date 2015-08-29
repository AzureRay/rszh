package servlet.rsjl_import;

import servlet.import_export.importDao.ExcelOperationUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;



//import org.apache.poi.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.CommentObject;
import dao.DepartDao;


@WebServlet("/importServlet1")
public class importServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean flag = true;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String filePath = request.getAttribute("src")+"";
		//获得路径
		String realpath =request.getRealPath("/temporaryFile");
		  
		System.out.println("^^^realpath:"+realpath);
		System.out.println("^^"+realpath+"/"+filePath);
		String fileType = filePath.substring(filePath.length() - 3,
				filePath.length());
		System.out.println(fileType);
		
		if ("xls".equals(fileType) || "lsx".equals(fileType)) {
			List<String> columnName = ExcelOperationUtil
					.readExcelColumnData(realpath+"/"+filePath);
			List<Vector> data = ExcelOperationUtil.readExcelData(realpath+"/"+filePath);
			if(columnName==null||data==null){
				String fail ="导入的数据不正确，请重新选择数据";
				request.setAttribute("fail", fail);
				request.getRequestDispatcher("/rsjl_import/fail.jsp").forward(request,response);
			} else{
				request.setAttribute("columnName", columnName);
				request.setAttribute("data", data);
				request.getRequestDispatcher("/rsjl_import/importShow.jsp").forward(request,response);
			}
			System.out.println(columnName);
			System.out.println(data);
		} else {
			flag = true;
			String fail ="导入文件的格式不正确，请重新导入xls文件";
			request.setAttribute("fail", fail);
			request.getRequestDispatcher("/rsjl_import/fail.jsp").forward(request,response);
		}
	}
}
