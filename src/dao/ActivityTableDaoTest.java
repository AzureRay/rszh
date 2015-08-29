package dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ActivityTableDaoTest {

	ActivityTableDao activityTableDao = new ActivityTableDao();
	
	@Test
	public void testIsSelectRow(){
		System.out.println(activityTableDao.isSelectRow("民族"));
	}
	
	@Test
	public void testGetSelectRowValueList(){
		System.out.println(activityTableDao.getSelectRowValueList("民族"));
	}
	
	
	@Test
	public void testGetRowNameList(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "培训");
		//System.out.println(activityTableDao.getRowNameList(map));
	}
	
	@Test
	public void testAdd(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "培训");
		map.put("参与人数", "12");
		map.put("民族", "汉");
		map.put("姓名", "姚云");
		//activityTableDao.add(map);
	}
	
	@Test
	public void testDelete(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "培训");
		map.put("id", "3");
		//activityTableDao.delete(map);
	}
	
	@Test
	public void testgetRowNameList(){
		System.out.println(activityTableDao.getRowNameList("personal"));
	}
	
	@Test
	public void testAlter(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "培训");
		map.put("id", "4");
		map.put("姓名", "黄天赐");
		//activityTableDao.alter(map);
	}
	
	@Test
	public void testGetList(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "培训");
		//map.put("姓名", "黄天赐");
		//map.put("参与人数", "13");
		//System.out.println(activityTableDao.getList(map));
	}
	//getSelectSqlByValue
	@Test
	public void testGetSelectSqlByValue(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "培训");
		map.put("姓名", "黄天赐");
		map.put("参与人数", "13");
		//System.out.println(activityTableDao.getSelectSqlByValue(map));
	}
	
}
