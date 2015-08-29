package tongji;

import java.sql.Connection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jdbc.JdbcTools;

import org.jfree.data.Value;

import util.GetMapUtil;

import dao.Dao;

/**
 * 1.设置统计的限定条件
 * 2.将限定条件保存到统计设置表中
 * 3.将查询到的结果保存到统计表中
 * @author Tianci
 *
 */
public class Tongji extends Dao{
	
	public void insertTongjiSet(HttpServletRequest request) {
		Map<String, Object> params= GetMapUtil.getRequestMap(request);
		String tongjiName = params.get("tongjiName")+"";
		params.remove("tongjiName");
		String sql = "insert into 统计设置(字段名,字段值,统计名称) values(?,?,?);";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			for(String key : params.keySet()){
				String value = params.get(key)+"";
				write(connection, sql, key,value,tongjiName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
	}
	
}
