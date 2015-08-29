package xinzeng;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import photo.photoDao;

import rsjl.LHCX;
import servlet.import_export.DaochuJluDao;
import bean.CommentObject;

/**
 * Servlet implementation class PhotoSc
 */
@WebServlet("/photoSc")
public class PhotoSc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String realpath =(String)request.getRealPath("/photo");
		String path=request.getParameter("path");
		PrintWriter out = response.getWriter();
		//
		String nameString = null;
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		System.out.println(isMultipart);
		if (isMultipart) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Configure a repository (to ensure a secure temp location is used)
			ServletContext servletContext = this.getServletConfig()
					.getServletContext();
			File repository = (File) servletContext
					.getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(repository);
			System.out.println("filename2:");
			// Set factory constraints
			factory.setSizeThreshold(1024*1024);
			//factory.setRepository(yourTempDirectory);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Set overall request size constraint
			upload.setSizeMax(1024*1024*1024);
			// Parse the request
			try {
				List<FileItem> items = upload.parseRequest(request);
				// Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = iter.next();
				    // Process a regular form field
				    if (item.isFormField()) {
				  //      processFormField(item);
				    	
				    	 String name = item.getFieldName();
					     String value = item.getString();
					     System.out.println(name+":"+value);
				    } else {
				   //     processUploadedFile(item);
				    	
				    	// Process a file upload
				    	
				    	    String fieldName = item.getFieldName();
				    	    String fileName = item.getName();
				    	    String contentType = item.getContentType();
				    	    boolean isInMemory = item.isInMemory();
				    	    long sizeInBytes = item.getSize();
				    	    System.out.println(fieldName+":"+fileName+":"+contentType+":"+isInMemory+":"+sizeInBytes);
				    	    if (fileName!=null) {
				    	    	String id= request.getParameter("id");
					    		List<CommentObject> list1 = photoDao.queryList(id);
					    			 String bianhao =list1.get(0).getValues().get("编号")+"";
					    	
					    		fileName = fileName.replaceAll("\\w*(\\.\\w*)", "$1");
					    		fileName =bianhao + fileName;
					    	    File uploadedFile = new File(realpath,fileName);
				    	        try {
									item.write(uploadedFile);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				    	    } else {
				    	    	out.println("请选择文件");
				    	    }
				    	   
				    		
				    	    System.out.println(nameString);
				    	   request.getRequestDispatcher("/xinzengPhoto?fileName="+fileName).forward(request, response);
				    	}
				    }
				
				
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
