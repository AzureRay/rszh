package servlet.renshishezhi.dasz;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ActivityTableDao;

/**
 * 当添加一行列时，调用此servlet,该类返回select_table中数据类型
 * ,暂时不用在request中获取值
 * @author Tianci
 *
 */
@WebServlet("/addNewRowRequest")
public class AddNewRowRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ActivityTableDao atd = new ActivityTableDao();
		//获取数据类型
		List<String> sjlx = atd.getSelectRowValueList("数据类型");
		request.setAttribute("sjlx", sjlx);
		request.getRequestDispatcher("/jsp/rssz/dasz/add.jsp").forward(request, response);
	}

}
