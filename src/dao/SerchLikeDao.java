package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jdbc.JdbcTools;
import util.GetMapUtil;

import bean.CommentObject;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

/**
 * 模糊查询类(需要传入tableName,根据具体表查询)
 * 1.获取所有列
 * 2.拼接字符串
 * 3.查找数据库
 * @author Tianci
 *
 */
public class SerchLikeDao extends Dao{
	//1.获取指定表的列明集合
	public List<CommentObject> getTableRowList(String tableName){
		Connection connection = null;
		String sql = "select row_name from main_table where table_name=? order by row_index asc";
		List<CommentObject> list = null;
		try {
			connection = JdbcTools.getConnection();
			list = get(connection, sql, tableName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
		 return list;
	}
	
	//2.拼接sql
	public String getSql(String tableName,String rowName,String keyWord){
		String sql = "select * from  "+tableName+"  where  "+rowName+"  like '%"+keyWord+"%'";
		return sql;
	}
	
	//查询
	public List<CommentObject> serchLikeKey(String tableName,String keyWord){
		List<CommentObject> rowNames = getTableRowList(tableName);
		Connection connection = null;
		List<CommentObject> result = new ArrayList<CommentObject>();
		try {
			connection = JdbcTools.getConnection();
			for (int i = 0;i < rowNames.size();i++) {
				String rowName = (String)rowNames.get(i).getValues().get("row_name");
				//得到sql语句
				String sql = getSql(tableName, rowName, keyWord);
				System.out.println("sql语句： "+sql);
				//查询
				List<CommentObject> list = get(connection, sql);
				if(list != null){
					for(int j = 0; j < list.size();j++){
						result.add(list.get(j));
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JdbcTools.free(null, null, connection);
		}
		return result;
	}
	
}
