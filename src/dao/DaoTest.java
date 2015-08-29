package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.Map;

import jdbc.JdbcTools;

import org.junit.Test;

public class DaoTest {

	Dao dao = new Dao();
	@Test
	public void testgetIntValue() {
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			String sql = "select id,max(depart_index),height from depart where depart=?";
			Map map = dao.getIntValue(connection, sql, "财务部");
			System.out.println(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
