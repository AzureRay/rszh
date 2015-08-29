package dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class SerchLikeDaoTest {

	SerchLikeDao sDao = new SerchLikeDao();
	
	@Test
	public void testGetTableRowList() {
		System.out.println(sDao.getTableRowList("personal"));
	}

	@Test
	public void testGetSql() {
		System.out.println(sDao.getSql("personal", "姓名", "黄"));
	}
	
	@Test
	public void testserchLikeKey() {
		System.out.println(sDao.serchLikeKey("personal", "黄"));
	}

}
