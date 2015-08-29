package servlet.beifen_huanyuan;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/beifenServlet")
public class beifenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public beifenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		request.getParameter("path");
		//String filePath ="g:/";
		String filePath =(String)request.getRealPath("/temporaryFile");
		System.out.println("filePath"+filePath);
		String sql="project.sql";
		InputStream in=null;
		File f=new File(filePath);
		System.out.println("f"+f);
		beifen beifen=new beifen();
		beifen.down_FILE(request, response, filePath, sql);
	}

}
