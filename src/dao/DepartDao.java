package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

import bean.CommentObject;

import jdbc.JdbcTools;

public class DepartDao extends Dao {
	
	/**
	 * 根据传入的ID获取单条部门的值
	 * @param id
	 * @return
	 */
	public CommentObject getOneById(int id){
		Connection connection = null;
		CommentObject commentObject = null;
		try {
			connection  = JdbcTools.getConnection();
			String sql = "select * from depart where id=?;";
			List<CommentObject> list = get(connection, sql, id);
			if(list.get(0) != null){
				commentObject = list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
		return commentObject;
	}

	/**
	 * 创建一个新的部门或者科室
	 * 
	 * @param parentName
	 *            科室的上级部门名
	 * @param departName
	 *            创建的科室名
	 */
	public void addDepart(int id, String departName) {

		Connection connection = null;
		String sql1 = "select max(depart_index) from depart where parent_id=?";
		String sql2 = "insert into depart(depart,parent_id) values(?,?)";
		String sql3 = "update depart set depart_index=? where depart=?";
		try {
			connection = JdbcTools.getConnection();
			connection.setAutoCommit(false);
			write(connection, sql2, departName, id);
			Map map = getIntValue(connection, sql1, id);
			int index = (Integer) map.get("max(depart_index)");
			System.out.println(index);
			index++;
			write(connection, sql3, index, departName);
			connection.commit();
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
	}

	/**
	 * 更新部门或者科室
	 * 
	 * @param oldName
	 * @param newName
	 */
	public void updateDao(int oldId, String newName) {
		Connection connection = null;
		String sql = "update depart set depart=? where id=?";
		try {
			connection = JdbcTools.getConnection();
			connection.setAutoCommit(false);
			write(connection, sql, newName, oldId);
			connection.commit();
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
	}

	/**
	 * 查询部门或者科室 此查询是查询父节点下的子节点的集合 要求:返回的由部门名组成的集合，集合的存放的顺序是部门序号的顺序)
	 * 
	 * @param parentName
	 */
	public List<CommentObject> selectDepart() {
		Connection connection = null;
		List<CommentObject> list = null;
		String sql = "select id,depart,parent_id from depart order by depart_index asc";
		try {
			connection = JdbcTools.getConnection();
			connection.setAutoCommit(false);
			list = get(connection, sql);
			System.out.println(list);
			connection.commit();
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
		return list;

	}
	
	public List<CommentObject> selectDepart(int id) {
		Connection connection = null;
		List<CommentObject> list = null;
		String sql = "select id,depart,parent_id from depart where parent_id=? order by depart_index asc";
		try {
			connection = JdbcTools.getConnection();
			connection.setAutoCommit(false);
			list = get(connection, sql,id);
			connection.commit();
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
		return list;
	}

	/**
	 * 判断该节点是否是叶子节点 叶子节点： 其下无子节点, 如果是叶子节点，则返回true 否则返回false
	 * 
	 * @return
	 */
	public boolean isYeZiNode(Connection connection, int id) {
		String sql = "select id from depart where parent_id=?";
		List<Map<String, Integer>> list = getIntValueList(connection, sql, id);
		System.out.println("--isYeZiNode");
		System.out.println(list);
		boolean b = list.isEmpty();
		System.out.println(b);
		return b;
	}

	/**
	 * 通过传入的部门名删除部门
	 * 
	 * @param name
	 * @return
	 */
	public boolean deleteDepart(int id) {
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			// 通过节点ID删除
			deleteNode(connection, id);
			removeDepartById(connection, id);
			// connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 通过ID移除部门下面的子部门
	 * 
	 * @param id
	 * @return
	 */
	public void removeDepartById(Connection connection, int id) {
		System.out.println("s--removeDepartById");

		// 判断该节点是否是叶子节点
		if (isYeZiNode(connection, id)) {
			deleteNode(connection, id);
		} else {
			// 不是叶子节点
			// 1.获取该节点的所有子节点id的集合
			String sql1 = "select id from depart where parent_id=?";
			List<Map<String, Integer>> list = getIntValueList(connection, sql1,
					id);
			// 循环每条ID，调用方法本身
			for (int i = 0; i < list.size(); i++) {
				int id1 = list.get(i).get("id");
				removeDepartById(connection, id1);
			}
		}
	}

	/**
	 * 删除节点
	 * 
	 * @param id
	 *            要删除节点的ID
	 */
	private void deleteNode(Connection connection, int id) {
		System.out.println("s--deleteNode");
		String sql = "delete from depart where id=?";
		write(connection, sql, id);
	}

	/**
	 * 下移
	 * 
	 * @param id
	 */
	public void downMove(int id) {
		Connection connection = null;
		String sql = "select parent_id,depart_index from depart where id=? ";
		String sql1 = "select id from depart where depart_index=? and parent_id=? ";
		String sql4 = "select depart_index from depart where parent_id=? order by depart_index asc ";

		try {
			int next = 0;
			connection = JdbcTools.getConnection();
			connection.setAutoCommit(false);
			List<Map<String, Integer>> list = getIntValueList(connection, sql,
					id);
			System.out.println("list:" + list);
			int departindex = (Integer) list.get(0).get("depart_index");
			System.out.println("departindex:" + departindex);
			int parentid = (Integer) list.get(0).get("parent_id");
			List<Map<String, Integer>> list2 = getIntValueList(connection,
					sql4, parentid);

			for (int i = 0; i < list2.size(); i++) {
				if (list2.get(i).get("depart_index").equals(departindex)) {
					next = list2.get(i + 1).get("depart_index");
				}
			}
			List<Map<String, Integer>> list1 = getIntValueList(connection,
					sql1, next, parentid);
			int id1 = (Integer) list1.get(0).get("id");
			updateId(connection, "depart", "depart_index",departindex, id1);
			updateId(connection, "depart", "depart_index",next, id);
			connection.commit();
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
	}
	/**
	 * 上移
	 * 
	 * @param id
	 */
	public void upMove(int id) {
		Connection connection = null;
		String sql = "select parent_id,depart_index from depart where id=? ";
		String sql1 = "select id from depart where depart_index=? and parent_id=?";
		String sql4 = "select depart_index from depart where parent_id=? order by depart_index desc ";

		try {
			int before = 0;
			connection = JdbcTools.getConnection();
			connection.setAutoCommit(false);
			List<Map<String, Integer>> list = getIntValueList(connection, sql,
					id);
			int departindex = (Integer) list.get(0).get("depart_index");
			System.out.println("departindex:" + departindex);
			int parentid = (Integer) list.get(0).get("parent_id");
			List<Map<String, Integer>> list2 = getIntValueList(connection,
					sql4, parentid);
			System.out.println("list2:" + list2);
			for (int i = 0; i < list2.size(); i++) {
				if (list2.get(i).get("depart_index").equals(departindex)) {
					before = list2.get(i + 1).get("depart_index");
				}
			}
			System.out.println("before:" + before);
			List<Map<String, Integer>> list1 = getIntValueList(connection,
					sql1, before, parentid);
			int id1 = (Integer) list1.get(0).get("id");
			System.out.println("id:" + id);
			updateId(connection,"depart", "depart_index",departindex, id1);
			updateId(connection, "depart", "depart_index",before, id);
			connection.commit();
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
	}

	

}
