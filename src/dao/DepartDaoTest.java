package dao;

import static org.junit.Assert.*;

import org.junit.Test;

public class DepartDaoTest {

	DepartDao dd = new DepartDao();
	//SelectDao sd = new SelectDao();
	
	@Test
	public void testUpdateDepart() {
		dd.updateDao(9, "财务补补");
	}
	@Test
	public void testGet() {
		System.out.println(dd.getOneById(1));
	}
	
	@Test
	public void testAddDepart() {
		dd.addDepart(20, "生产部第三记");
	}
	@Test
	public void testSelectDepart() {
		
		System.out.println(dd.selectDepart().get(0).getValues().get("id"));
	}
	@Test
	public void testDeleteDepart(){
		dd.deleteDepart(16);
	}
	@Test
	public void testDownMove() {
		dd.downMove(16);
	}
	@Test
	public void testUpMove() {
		dd.upMove(2);
	}

}
