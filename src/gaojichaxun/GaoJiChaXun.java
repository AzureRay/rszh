package gaojichaxun;

import java.sql.Connection;
import java.util.List;
import jdbc.JdbcTools;
import bean.CommentObject;
import dao.Dao;

public class GaoJiChaXun extends Dao{

	public List<CommentObject> getList(String sql){
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sql);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
		return null;
	}
	
}
