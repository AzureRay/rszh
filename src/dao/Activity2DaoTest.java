package dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Activity2DaoTest {

Activity2Dao a2d = new Activity2Dao();
	
	@Test
	public void testGetRowNamesOrderByRowIndex() {
		System.out.println(a2d.getRowNamesOrderByRowIndex("培训"));
	}

	@Test
	public void testGetSelectSql() {
		System.out.println(a2d.getSelectSql(a2d.getRowNamesOrderByRowIndex("培训"), "培训"));
	}
	@Test
	public void testGetRowNameList(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "personal");
		map.put("num", "02");
		map.put("tiaojian", "编号");
		System.out.println(a2d.getRowListValue(map).get(0).getValues());
	}
	@Test
	public void testGetperson() {
		System.out.println(a2d.getPerson().get(24));
	}

}
