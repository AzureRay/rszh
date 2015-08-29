package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Statement;

import jdbc.JdbcTools;

import bean.CommentObject;

/**
 * 动态创建表的接口:新建表、插入新列、修改列名、删除列 未完成:删除表、修改表名、大表和小表的上下移
 * 
 * @author Tianci
 */
public class MainTableDao extends Dao {

	public static final String wenben = "文本型";
	public static final String riqi = "日期型";
	public static final String xuanze = "选择型";
	public static final String zhengshu = "整数型";
	public static final String xiaoshu = "小数型";
	public static final String beizhu = "备注";
	public static final String tupian = "图片";
	public static final String lianjie = "链接";
	/**
	 * 根据传入的表名，获取创建该表的sql语句， 创建该表是自动创建主键id,并默认递增
	 * 
	 * @param tableName
	 * @return
	 */
	private String getCreateTableSql(String tableName) {
		String sql = "create table " + tableName
				+ "(id int(11) not null AUTO_INCREMENT primary key);";
		System.out.println("创建新表的sql语句： "+sql);
		return sql;
	}

	/**
	 * 该方法会返回一条插入表列的sql语句，在拼接sql语句前，会根据类型
	 * 
	 * @param tableName
	 *            插入表的表名
	 * @param rowName
	 *            新插入的表列
	 * @param rowType
	 *            类型
	 * @return
	 */
	private String getAddTableRowSql(String tableName, String rowName) {
		String sql = "alter table " + tableName + " add " + rowName + " varchar(255) null;";
		return sql;
	}

	/**
	 * 创建新表 1.先插入创建表的信息到main_table里面(插入时row_name为主键id,row_type为int)
	 * 2.更新表的table_index
	 * 
	 * @param tableName
	 * @return
	 */
	public boolean createTable(String tableName) {
		System.out.println("创建新表操作被执行");
		// 判断要创建的表是否已经存在于数据库中
		if (isExistTable(tableName)) {
			System.out.println("该表已经存在");
			return false;
		}
		// 得到插入到main_table表里的sql语句
		String sql1 = "insert into main_table(table_name,table_index,row_name,row_type) values(?,?,?,?);";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			connection.setAutoCommit(false);
			// 得到当前table_index的最大值
			String sql = "select max(table_index) from main_table";
			int tableIndex = getMaxIndex(connection, sql);
			tableIndex++;
			// 插入到main_table表里
			write(connection, sql1, tableName, tableIndex, "id", "int");
			// 得到创建表的sql语句
			String sql2 = getCreateTableSql(tableName);
			// 创建表
			excute(connection, sql2);
			connection.commit();
			System.out.println("创建新表操作执行完毕");
			return true;
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("创建新表操作执行失败");
		return false;
	}

	/**
	 * 在已经存在的表中插入新的列 1.判断要插入的表是否存在 2.判断要插入的列是否存在 3.将插入的新列的信息保存到main_table中
	 * 4.插入新列
	 * 
	 * @param tableName
	 * @param rowName
	 * @param rowType
	 * @param judgeChoseRow
	 * @return
	 */
	public boolean addNewRow(String tableName, String rowName, String rowType,
			int judgeChoseRow,String comment) {
		// 判断要创建的表是否已经存在于数据库中
		if (!isExistTable(tableName)) {
			System.out.println("该表不存在");
			return false;
		}
		System.out.println("1");
		if (isExistRow(tableName, rowName)) {
			System.out.println("要插入的列名已经在该表中存在");
			return false;
		}
		System.out.println("2");
		String sql = "insert into main_table(table_name,row_name,row_index,row_type,judge_chose_row,comment) values(?,?,?,?,?,?);";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			connection.setAutoCommit(false);
			// 得到当前最大的row_index
			String sql1 = "select max(row_index) from main_table where table_name=?";
			int index = getMaxIndex(connection, sql1, tableName);
			System.out.println("当前最大的列序号： " + index);
			index++;
			System.out.println("3");
			// 将该条记录插入到main_table
			if(rowType.equals("选择型")){
				write(connection, sql, tableName, rowName, index, rowType,
						1,comment);
				ActivityTableDao atd = new ActivityTableDao();
				atd.addNewSelectRow(rowName, "默认值");
			}else{
				write(connection, sql, tableName, rowName, index, rowType,
						judgeChoseRow,comment);
			}
			// 得到插入列的sql2
			String sql2 = getAddTableRowSql(tableName, rowName);
			excute(connection, sql2);
			connection.commit();
			return true;
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据传入的参数删除表中指定列 1.判断指定表指定列是否存在 2.删除在main_table中的记录 3.删除表中的列
	 * 
	 * @param tableName
	 * @param rowName
	 * @return
	 */
	public boolean dropRow(String tableName, String rowName) {
		// 1.判断指定表指定列是否存在
		if (!isExistRow(tableName, rowName)) {
			System.out.println("要删除的列不存在");
			return false;
		}
		String sql = "delete from main_table where table_name=? and row_name=?";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			connection.setAutoCommit(false);
			write(connection, sql, tableName, rowName);
			String sql1 = "alter table " + tableName + " drop column "
					+ rowName + ";";
			excute(connection, sql1);
			connection.commit();
			return true;
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据传入的参数修改表中指定列 1.修改main_table 中的记录 2.修改表
	 * 
	 * @param tableName
	 * @param rowName
	 * @return
	 */
	public boolean alterRow(String tableName, String oldRowName,
			String newRowName, String rowType,String comment) {
		// 1.判断指定表指定列是否存在
		if (!isExistRow(tableName, oldRowName)) {
			System.out.println("要修改的列不存在");
			return false;
		}
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			connection.setAutoCommit(false);
			String sql = "update main_table set row_name=?,row_type=?,comment=?  where table_name=? and row_name=?";
			write(connection, sql, newRowName,rowType,comment, tableName, oldRowName);
			String sql1 = "alter table " + tableName + " change " + oldRowName
					+ " " + newRowName + " varchar(255);";
			excute(connection, sql1);
			connection.commit();
			return true;
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据传入的表名，判断表是否已经存在数据库中， 如果存在，则返回true
	 * 
	 * @return
	 */
	private boolean isExistTable(String tableName) {
		String sqlString = "select table_name from main_table where table_name=?";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sqlString, tableName);
			if(list.isEmpty()){
				return false;
			}else{
				if (list.get(0).getValues().containsValue(tableName)) {
					return true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据传入的列名，判断该表中是否已经存在该列， 如果存在，则返回true
	 * 
	 * @return
	 */
	private boolean isExistRow(String tableName, String rowName) {
		String sqlString = "select row_name from main_table where table_name=? and row_name=?";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sqlString, tableName,
					rowName);
			if (list == null || list.isEmpty()) {
				return false;
			}
			if (list.get(0).getValues().containsValue(rowName)) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 得到数据库中已经存在的表名的集合(id,table_name,...) 要求:按顺序排列
	 * 
	 * @return
	 */
	public List<CommentObject> getTableNameList(int current) {
		String sql = "select DISTINCT table_name from main_table where row_name='id' order by table_index asc limit ?,10;";
		Connection connection = null;
		int page = (current-1)*10;
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sql,page);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JdbcTools.free(null, null, connection);
		}
		return null;
	}
	
	/**
	 * 得到数据库中已经存在的表名的集合(id,table_name,...) 要求:按顺序排列
	 * 
	 * @return
	 */
	public List<CommentObject> getTableNameList() {
		String sql = "select DISTINCT table_name from main_table where row_name='id' order by table_index asc;";
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
	/**
	 * 得到数据库中已经存在表的所有信息
	 * @return
	 */
	public List<CommentObject> getTableInfo() {
		String sql = "select * from main_table order by table_index asc;";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sql);
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据传入的参数得到index最大值
	 * 
	 * @param args
	 * @return
	 */
	private int getMaxIndex(Connection connection, String sql, Object... args) {
		Map<String, Integer> map = getIntValue(connection, sql, args);
		int max = 0;
		if (map.containsKey("max(table_index)")) {
			max = map.get("max(table_index)");
		} else if (map.containsKey("max(row_index)")) {
			max = map.get("max(row_index)");
			System.out.println("max(row_index): " + max);
		}
		return max;
	}

	/**
	 * 删除主表及副表中的主表的字段
	 * 
	 * @param tableName
	 * @return
	 */
	public boolean dropTable(String tableName) {
		// 1.判断指定表指定列是否存在
		if (!isExistTable(tableName)) {
			System.out.println("要删除的表不存在");
			return false;
		}
		String sql = "delete from main_table where table_name=?";
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			connection.setAutoCommit(false);
			write(connection, sql, tableName);
			String sql1 = "drop table " + tableName;
			excute(connection, sql1);
			connection.commit();
			return true;
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据传参修改大表表名及主表里的表名字
	 * 
	 * @param oldTableName
	 * @param newTableName
	 * @return
	 */
	public boolean alterTable(String oldTableName, String newTableName) {
		// 1.判断指定表指定列是否存在

		if (!isExistTable(oldTableName)) {
			System.out.println("要修改的表不存在");
			return false;
		}
		Connection connection = null;
		try {
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;
			connection = JdbcTools.getConnection();
			connection.setAutoCommit(false);
			String sql = "update main_table set table_name=? where table_name=?";
			write(connection, sql, newTableName, oldTableName);
			String sql1 = "alter table " + oldTableName + " rename "
					+ newTableName + ";";
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.execute();
			connection.commit();
			return true;
		} catch (Exception e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 大表的下移
	 * 
	 */
	public void bigdownMove(String tableName) {
		Connection connection = null;
		String sql = "select table_index,table_name from main_table where row_name='id' order by table_index asc ";
		int index = 0;
		int next = 0;
		String nextTableName = null;
		//1.获取当前所有表的序号
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sql);
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getValues().get("table_name").equals(tableName)){
					index = (Integer)list.get(i).getValues().get("table_index");
					if((i+1) <= list.size()){
						//2.找到指定表的下一个序号
						next = (Integer)list.get(i+1).getValues().get("table_index");
						nextTableName = (String)list.get(i+1).getValues().get("table_name");
					}
					//3.交换序号
					String sql1 = "update main_table set table_index=? where table_name=? and row_name='id'";
					write(connection, sql1, next,tableName);
					write(connection, sql1, index,nextTableName);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
	}

	/**
	 * 大表的上移
	 * 
	 * @param id
	 */
	public void bigUpMove(String tableName) {
		Connection connection = null;
		String sql = "select table_index,table_name from main_table where row_name='id' order by table_index asc ";
		int index = 0;
		int before = 0;
		String beforeTableName = null;
		//1.获取当前所有表的序号
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sql);
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getValues().get("table_name").equals(tableName)){
					index = (Integer)list.get(i).getValues().get("table_index");
					if((i-1) >= 0){
						//2.找到指定表的下一个序号
						before = (Integer)list.get(i-1).getValues().get("table_index");
						beforeTableName = (String)list.get(i-1).getValues().get("table_name");
					}
					//3.交换序号
					String sql1 = "update main_table set table_index=? where table_name=? and row_name='id'";
					write(connection, sql1, before,tableName);
					write(connection, sql1, index,beforeTableName);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
	}

	/**
	 * 小表下移
	 * 1.获取当前的列的序号
	 * 2.获取当前列下一列的序号
	 * 3.交换
	 */
	public void downSmallMove(String tableName,String rowName) {
		Connection connection = null;
		String sql = "select row_name,row_index from main_table where table_name=? order by row_index asc ";
		Object index = 0;
		Object next = 0;
		String nextRowName = null;
		//1.获取当前所有表的序号
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sql,tableName);
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getValues().get("row_name").equals(rowName)){
					index = list.get(i).getValues().get("row_index");
					if((i+1) <= list.size()){
						//2.找到指定表的下一个序号
						next = list.get(i+1).getValues().get("row_index");
						nextRowName = (String)list.get(i+1).getValues().get("row_name");
					}
					//3.交换序号
					String sql1 = "update main_table set row_index=? where table_name=? and row_name=?";
					write(connection, sql1, next,tableName,rowName);
					write(connection, sql1, index,tableName,nextRowName);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
	}

	/**
	 * 小表的上移
	 * 
	 */
	public void upSmallMove(String tableName,String rowName) {
		Connection connection = null;
		String sql = "select row_name,row_index from main_table where table_name=? order by row_index asc ";
		Object index = 0;
		Object before = 0;
		String beforeRowName = null;
		//1.获取当前所有表的序号
		try {
			connection = JdbcTools.getConnection();
			List<CommentObject> list = get(connection, sql,tableName);
			for (int i = 0; i < list.size(); i++) {
				if(list.get(i).getValues().get("row_name").equals(rowName)){
					index = list.get(i).getValues().get("row_index");
					if((i-1) >= 0){
						//2.找到指定表的下一个序号
						before = list.get(i-1).getValues().get("row_index");
						beforeRowName = (String)list.get(i-1).getValues().get("row_name");
					}
					//3.交换序号
					String sql1 = "update main_table set row_index=? where table_name=? and row_name=?";
					write(connection, sql1, before,tableName,rowName);
					write(connection, sql1, index,tableName,beforeRowName);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
	}

	/**
	 * 获取由String类型组成的map 只适用于一条记录的String型数据组成的集合
	 * 
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	public Map<String, String> getStringValue(Connection connection,
			String sql, Object... args) {
		Map<String, String> map = getStringValueList(connection, sql, args)
				.get(0);
		System.out.println("--getIntValue");
		System.out.println(map);
		return map;
	}

	/**
	 * 获取有int型数据组成的map集合的list集合
	 * 
	 * @param connection
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<Map<String, String>> getStringValueList(Connection connection,
			String sql, Object... args) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
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
				columnName[i] = rsmd.getColumnName(i + 1);
			}

			while (resultSet.next()) {
				Map<String, String> map = new HashMap<String, String>();
				for (int i = 0; i < count; i++) {
					String value = resultSet.getString(i + 1);
					map.put(columnName[i], value);
				}
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.free(resultSet, preparedStatement, null);
		}
		System.out.println("--getIntValueList");
		System.out.println(list);
		return list;
	}

}
