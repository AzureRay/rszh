package servlet.import_export;
import java.sql.Connection;
import java.util.List;

import jdbc.JdbcTools;

import bean.CommentObject;

import dao.Dao;

public class DaochuJluDao extends Dao{
	public static List<CommentObject> query(String tableName,String id){
		String sql="select 姓名,部门,"+tableName+".* from personal,"+tableName+"  where personal.编号="+tableName+".编号 and "+tableName+".id=?";
		System.out.println("sql"+sql);
		Connection connection = null;
		List<CommentObject> list = null;
		try {
			connection = JdbcTools.getConnection();
			list = get(connection,sql,id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
		return list;
	}
}
