package shujugeshichuli;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import util.DateUtil;
import jdbc.JdbcTools;


/**
 * 更新年龄
 * @author Tianci
 *
 */
public class UpdateAge {
	
	public static void main(String[] args) {
		UpdateAge updateAge = new UpdateAge();
		System.out.println(updateAge.updateAllAge());
	}
	
	
	/**
	 * 更新数据库中所有人员的年龄
	 * @return
	 */
	public boolean updateAllAge(){
		//从数据库中读取记录
		String sql = "select * from personal;";
		//逐条更新
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//从数据库中读取记录
			connection = JdbcTools.getConnection();
			//读取
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			//使用批处理更新年龄
			String writeSql = "update personal set 年龄=? where id=?;";
			ps = connection.prepareStatement(writeSql);
			while (rs.next()) {
				String birthdayString = rs.getString("出生日期");
				if(birthdayString == null||birthdayString.equals("")){
					continue;
				}
				int id = rs.getInt("id");
				//将出生日期转换成日期类
				Calendar birthday  = DateUtil.changeStringToDate(birthdayString);
				//获取当前的日期
				Date current = new Date();
				java.util.Calendar calendar = Calendar.getInstance();
				calendar.setTime(current);
				//计算出年龄
				int now = calendar.get(Calendar.YEAR);
				int bir = birthday.get(Calendar.YEAR);
				int age = now - bir;
				//加入批处理
				ps.setString(1, age+"");
				ps.setInt(2, id);
				ps.addBatch();
			}
			ps.executeBatch();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(rs, ps, connection);
		}
		return false;
	}
}
