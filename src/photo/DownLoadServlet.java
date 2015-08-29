package photo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DownLoadServlet
 */
@WebServlet("/downloadServlet")
public class DownLoadServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		String path = request.getParameter("path");
		System.out.println("@@path"+path);
		String filename= path.substring(path.indexOf("\\"));
		System.out.println("filename$$"+filename);
		response.setHeader("Content-disposition", "attachment;filename="+filename);
		File f = new File(path);
		FileInputStream in = new FileInputStream(path);
		ServletOutputStream o = response.getOutputStream();
		int n=0;
		byte b[]= new byte[1024];
		while((n=in.read(b))!=-1){
			o.write(b,0,n);
		}
		o.close();
		in.close();
	}
}

