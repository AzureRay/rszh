package servlet.import_export;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Session;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String path = request.getParameter("path");
		System.out.println(path);

		System.out.println("filename:" + path);
		PrintWriter out = response.getWriter();

		String realpath = request.getRealPath("/temporaryFile");
		System.out.println("realpath:" + realpath);
		//
		String nameString = null;
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();

			ServletContext servletContext = this.getServletConfig()
					.getServletContext();
			File repository = (File) servletContext
					.getAttribute("javax.servlet.context.tempdir");
			factory.setRepository(repository);

			factory.setSizeThreshold(4 * 1024);

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setSizeMax(4 * 1024 * 1024);
			try {
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();

					if (item.isFormField()) {

						String name = item.getFieldName();
						String value = item.getString();
						System.out.println(name + ":" + value);
					} else {

						String fieldName = item.getFieldName();
						String fileName = item.getName();
						String contentType = item.getContentType();
						boolean isInMemory = item.isInMemory();
						long sizeInBytes = item.getSize();

						System.out.println(fieldName + ":" + fileName + ":"
								+ contentType + ":" + isInMemory + ":"
								+ sizeInBytes);
						if (fileName != null) {
							nameString = fileName;
							System.out.println("nameString" + nameString);
							File uploadedFile = new File(realpath, fileName);
							try {
								item.write(uploadedFile);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {

						}
						System.out.println(nameString);
						request.setAttribute("src", nameString);
						request.getRequestDispatcher("/importServlet").forward(
								request, response);
					}
				}

			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
	}
}