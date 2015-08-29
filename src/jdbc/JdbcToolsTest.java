package jdbc;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


import org.junit.Test;

import bean.CommentObject;

public class JdbcToolsTest {

	@Test
	public void testGetConnection() throws Exception {
		System.out.println(JdbcTools.getConnection());
	}
	
	/*@Test
	public void testGetOne(){
		String sqlString = "select * from record_table where row_index=?";
		List<CommentObject> list = JdbcTools.get(sqlString, 1);
		System.out.println(list);
	}*/
	
	/*@Test
	public void testGetAll(){
		String sqlString = "select * from record_table";
		List<CommentObject> list = JdbcTools.get(sqlString);
		System.out.println(list);
	}
	@Test
	public void testUpdate(){
		String sqlString = "update record_table set table_name=?";
		JdbcTools.update(sqlString, "ddss");
	}*/
	
	

}
