package dao;

import java.sql.Connection;
import java.util.List;


import bean.CommentObject;

import jdbc.JdbcTools;


/**
 * 获取列的类型
 * @author Tianci
 *
 */
public class GRT extends Dao{
	/**
	 * 获取列类型
	 * @param tableName
	 * @param rowName
	 * @return
	 */
	public static String getRowType(String tableName,String rowName){
		Connection connection = null;
		String sql = "select row_type from main_table where table_name=? and row_name=?;";
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sql, tableName,rowName);
			if(list.get(0)!=null){
				return list.get(0).getValues().get("row_type")+"";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
		return null;
	}
}
