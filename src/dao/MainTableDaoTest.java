package dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTableDaoTest {

	MainTableDao mtd = new MainTableDao();
	
	@Test
	public void testCreateTable() {
		System.out.println(mtd.createTable("培训"));
	}

	@Test
	public void testAddNewRowStringStringString() {
	//	mtd.addNewRow("培训", "出生日期", MainTableDao.riqi);
	}
	
	@Test
	public void testBigdownMove() {
		//System.out.println(mtd.getTableNameList());
		mtd.bigdownMove("personal");
		//System.out.println(mtd.getTableNameList());
	}
	
	@Test
	public void testBigupMove() {
		//System.out.println(mtd.getTableNameList());
		mtd.bigUpMove("personal");
		//System.out.println(mtd.getTableNameList());
	}
	
	
	@Test
	public void testDownSmallMove() {
		mtd.downSmallMove("personal", "编号");
	}

}
