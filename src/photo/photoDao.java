package photo;

import java.sql.Connection;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.Select;

import bean.CommentObject;

import jdbc.JdbcTools;
import dao.Dao;

public class photoDao extends Dao{
	/**
	 * 将相对路径写入数据库
	 * @param path
	 */
	public  void  insertPath(String id,String path){
		String sql = "insert into photo(编号,photo) values(?,?)";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			write(connection, sql, id,path);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
	}
	
	/**
	 * personal表和photo表联系在一起
	 */
	
	public static List<CommentObject> queryList(String id){
		String sql="select 编号 from personal where id = ?";
		System.out.println("sql"+sql);
		Connection connection = null;
		List<CommentObject> list = null;
		try {
			connection = JdbcTools.getConnection();
			list = get(connection, sql, id);
			System.out.println("**&list:"+list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
		return list;
	}
	
	public static List<CommentObject> query(String num){
		String sql = "select photo from photo where 编号=?";
		Connection connection = null;
		List<CommentObject> list = null;
		try {
			connection  = JdbcTools.getConnection();
			list = get(connection, sql, num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
		
		
		return list;
		
	}
}

