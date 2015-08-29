package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jdbc.JdbcTools;


/**
 * 获取当前记录总页数
 * @author Tianci
 *
 */
public class Fenye {
	
	public static void main(String[] args) {
		System.out.println(Fenye.getPagesForTable());
		System.out.println(Fenye.getPageForRow("personal"));
		System.out.println(Fenye.getPageForTableRecord("personal"));
	}
	/**
	 * 获取数据库中main_table中表的分页
	 * @param tableName
	 * @return
	 */
	public static int getPagesForTable(){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int size = 0;
		try {
			connection = JdbcTools.getConnection();
			statement = connection.prepareStatement("select count(*) from main_table where row_name='id';");
			rs = statement.executeQuery();
			while(rs.next()){
				size = rs.getInt(1);
			}
			System.out.println("表的数量："+size);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(rs, statement, connection);
		}
		//计算出页数。每页有10条记录，若不足十条记录则向上取值
		int pageCount = size / 10;
		if(size % 10 > 0){
			pageCount++;
		}
		return pageCount;
	}
	
	/**
	 * 获得指定表在数据库中的页数，每页默认显示10条
	 * @param tableName
	 * @return
	 */
	public static int getPageForRow(String tableName){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int size = 0;
		try {
			connection = JdbcTools.getConnection();
			statement = connection.prepareStatement("select count(*) from main_table where table_name=?;");
			statement.setString(1, tableName);
			rs = statement.executeQuery();
			while(rs.next()){
				size = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(rs, statement, connection);
		}
		//计算出页数。每页有10条记录，若不足十条记录则向上取值
		int pageCount = size / 10;
		if(size % 10 > 0){
			pageCount++;
		}
		return pageCount;
	}
	
	/**
	 * 获得指定表中总页数
	 * @param tableName
	 * @return
	 */
	public static int getPageForTableRecord(String tableName){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int size = 0;
		try {
			connection = JdbcTools.getConnection();
			statement = connection.prepareStatement("select count(*) from "+tableName+";");
			rs = statement.executeQuery();
			while(rs.next()){
				size = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(rs, statement, connection);
		}
		//计算出页数。每页有10条记录，若不足十条记录则向上取值
		int pageCount = size / 10;
		if(size % 10 > 0){
			pageCount++;
		}
		return pageCount;
	}
	
	/**
	 * 获得指定表中总记录数
	 * @param tableName
	 * @return
	 */
	public static int getPageAllRecord(String tableName){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		int size = 0;
		try {
			connection = JdbcTools.getConnection();
			statement = connection.prepareStatement("select count(*) from "+tableName+";");
			rs = statement.executeQuery();
			while(rs.next()){
				size = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(rs, statement, connection);
		}
		return size;
	}
}
