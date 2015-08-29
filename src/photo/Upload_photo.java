package photo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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

import bean.CommentObject;

import photo.photoDao;
import rsjl.LHCX;
import servlet.import_export.DaochuJluDao;
@WebServlet("/upload_photo")
public class Upload_photo extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String realpath = request.getRealPath("/photo");
		String tableName = request.getParameter("tableName");
		  System.out.println("tableName@@"+tableName);
		photoDao pd = new photoDao();
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
	  if(isMultipart){
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		
		// Set factory constraints
		factory.setSizeThreshold(4*1024);
		//factory.setRepository(yourTempDirectory);

		
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// Set overall request size constraint
		upload.setSizeMax(4*1024*1024);
		
		// Parse the request
		try {
			List<FileItem> items = upload.parseRequest(request);
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
			    FileItem item = iter.next();

			    if (item.isFormField()) {
			       //Process a regular from field
			    	String name = item.getFieldName();
			        String value = item.getString();
			        System.out.println(name+":"+value);
			    } else {
			    	// Process a file upload
			    	    String fieldName = item.getFieldName();
			    	    String fileName = item.getName();
			    	    String contentType = item.getContentType();
			    	    boolean isInMemory = item.isInMemory();
			    	    long sizeInBytes = item.getSize();
			    	   
			    	System.out.println(fieldName+":"+fileName+":"+contentType+":"+isInMemory+":"+sizeInBytes);
			   
			    
			    	if (fileName!=null) {
			    		String id= request.getParameter("id");
			    		List<CommentObject> list1 = DaochuJluDao.query(tableName,id);
			    			 String bianhao =list1.get(0).getValues().get("编号")+"";
			    		
			    		fileName = fileName.replaceAll("\\w*(\\.\\w*)", "$1");
			    		int  num = (int)(1+Math.random()*100);
			    		fileName =num + fileName;
			    	    File uploadedFile = new File(realpath,fileName);
			    	    String path = fileName;
			    	    pd.insertPath(bianhao,path);
			    	    try {
							item.write(uploadedFile);
							response.sendRedirect("/jsp/photo/main.jsp?file="+ uploadedFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
			    	} else {
			    	}
			    }
			}
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	  }
	}
}
