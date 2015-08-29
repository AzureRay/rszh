package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import bean.CommentObject;
import jdbc.JdbcTools;

public class Dao {

	/**
	 * 执行创建，插入新列语句
	 * @param connection
	 * @param sql
	 */
	public void excute(Connection connection,String sql){
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			statement.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.free(resultSet, statement, null);
		}
	}
	/**
	 * 获取由INT类型组成的map
	 * 只适用于一条记录的int型数据组成的集合
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	public Map<String, Integer> getIntValue(Connection connection,String sql,Object ... args) {
		Map<String, Integer> map = getIntValueList(connection, sql, args).get(0);
		System.out.println("--getIntValue");
		System.out.println(map);
		return map;
	}
	
	/**
	 * 获取有int型数据组成的map集合的list集合
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<Map<String, Integer>> getIntValueList(Connection connection,String sql,Object ... args){
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, Integer>> list = new ArrayList<Map<String,Integer>>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int count = rsmd.getColumnCount();
			String[] columnName = new String[count];
			for (int i = 0; i < count; i++) {
				columnName[i] = rsmd.getColumnName(i+1);
			}
			
			while(resultSet.next()){
				Map<String, Integer> map = new HashMap<String, Integer>();
				for(int i = 0; i < count; i++){
					Integer value = resultSet.getInt(i+1);
					map.put(columnName[i], value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(resultSet, preparedStatement, null);
		}
		System.out.println("--getIntValueList");
		System.out.println(list);
		return list;
	}
	
	
	/**
	 * 执行 SQL 语句, 使用 PreparedStatement
	 * 注意:该方法需要传入链接数据库的对象，所以使用该方法后需要关闭该链接对象
	 * @param sql insert、update、delete语句
	 * @param args: 填写 SQL 占位符的可变参数
	 */
	public static void write(Connection connection,String sql, Object ... args){
		
		PreparedStatement preparedStatement = null;
		
		try {
			
			preparedStatement = connection.prepareStatement(sql);
			
			for(int i = 0; i < args.length; i++){
				preparedStatement.setObject(i + 1, args[i]);
			}
			
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, preparedStatement, null);
		}
	}
	
	/**
	 * 通用的查询方法，可根据传入的SQL查询到记录
	 * 每一条记录被封装成一个CommentObject的公共对象
	 * 返回的list封装了查询到的多条记录,若只查询一条记录
	 * 则取List[0]
	 * 注意:该方法需要传入链接数据库的对象，所以使用该方法后需要关闭该链接对象
	 * @param sql select语句
	 * @param args select语句对应的占位符
	 * @return 封装了查询到的结果记录
	 */
	public static List<CommentObject> get(Connection connection,String sql, Object... args) {
		//Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<CommentObject> list = new ArrayList<CommentObject>();
		
		try {
			//connection = JdbcTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int count = rsmd.getColumnCount();
			String[] columnName = new String[count];
			for (int i = 0; i < count; i++) {
				columnName[i] = rsmd.getColumnName(i+1);
			}
			
			while(resultSet.next()){
				CommentObject commentObject = new CommentObject();
				for(int i = 0; i < count; i++){
					Object columnValue = resultSet.getObject(i + 1);
					commentObject.addValue(columnName[i], columnValue);
				}
				list.add(commentObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.free(resultSet, preparedStatement, null);
		}
		return list;
	}
	/**
	 * 通过ID、相应的表名、索引的名字更新上下移动结果
	 * 
	 * @param id
	 *            要删除节点的ID
	 */
	public void updateId(Connection connection, String name,String indexname,int next, int id) {
		System.out.println("s--updateId");
		String sql = "update "+name+" set "+indexname+" =? where id=?";
		System.out.println(sql);
		write(connection, sql,next, id);
	}
}
