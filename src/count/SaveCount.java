package count;

import java.sql.Connection;
import java.util.List;

import bean.CommentObject;

import jdbc.JdbcTools;

import dao.Dao;

public class SaveCount extends Dao{
	
		/**
		 * 保存统计设置
		 * @param tableName
		 * @param countName
		 * @param rowNames
		 * @param tjs
		 * @param values
		 * @param ljs
		 * @return
		 */
		public boolean saveCountSet(String tableName,String countName
				,List<String> rowNames,List<String> tjs
				,List<String> values,List<String> ljs) {
			String sql = "insert into 统计设置(table_name,count_name,row_name,tj,value,lj) values(?,?,?,?,?,?);";
			Connection connection = null;
			try {
				connection = JdbcTools.getConnection();
				for(int i=0;i < rowNames.size();i++){
					write(connection,sql,tableName,countName,rowNames.get(i),tjs.get(i),values.get(i),ljs.get(i));
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				JdbcTools.free(null, null, connection);
			}
			return false;
		}
		
		/**
		 * 保存统计结果
		 * @param countName
		 * @param num
		 * @return
		 */
		public boolean saveCountResult(String countName,int num){
			String sql = "insert into 统计(count_content,count_num) values(?,?);";
			Connection connection = null;
			try {
				connection = JdbcTools.getConnection();
				write(connection,sql,countName,num);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				JdbcTools.free(null, null, connection);
			}
			return false;
		}
		
		/**
		 * 获取统计结果
		 * @return
		 */
		public List<CommentObject> getCountRecord(){
			Connection connection = null;
			List<CommentObject> list = null;
			String sql = "select * from 统计;";
			try {
				connection = JdbcTools.getConnection();
				list = get(connection, sql);
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				JdbcTools.free(null, null, connection);
			}
			return list;
		}
		
		/**
		 * 根据传入的id删除记录
		 * @param ids
		 * @return
		 */
		public boolean delCountRecord(List<Object> ids){
			String sql = "delete from 统计  where id=?;";
			Connection connection = null;
			try {
				connection = JdbcTools.getConnection();
				for(int i=0;i < ids.size();i++){
					write(connection,sql,ids.get(i));
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				JdbcTools.free(null, null, connection);
			}
			return false;
		}
}
