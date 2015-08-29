package notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.DateUtil;

import bean.CommentObject;

import jdbc.JdbcTools;

import dao.Dao;

public class SaveNotice extends Dao{
	
		/**
		 * 保存统计结果
		 * @param NoticeName
		 * @param num
		 * @return
		 */
		public boolean saveNoticeResult(String noticeName,int num,String noticeTime){
			String sql = "insert into 提醒(notice_content,notice_num,notice_time) values(?,?,?);";
			Connection connection = null;
			try {
				connection = JdbcTools.getConnection();
				write(connection,sql,noticeName,num,noticeTime);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				JdbcTools.free(null, null, connection);
			}
			return false;
		}
		
		/**
		 * 获取提醒结果
		 * @return
		 */
		public List<CommentObject> getNoticeRecord(){
			Connection connection = null;
			List<CommentObject> list = null;
			String sql = "select * from 提醒;";
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
		public boolean delNoticeRecord(List<Object> ids){
			String sql = "delete from 提醒  where id=?;";
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
		
		/**
		 * 获得当日提醒
		 * @param today
		 * @return
		 */
		public List<Map<String, String>> getTodayNotice(){
			//从数据库中读取记录
			String sql = "select * from 提醒;";
			//逐条更新
			Connection connection = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<Map<String, String>> list = new ArrayList<Map<String,String>>();
			try {
				//从数据库中读取记录
				connection = JdbcTools.getConnection();
				//读取
				ps = connection.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					String noticeString = rs.getString("notice_time");
					int id = rs.getInt("id");
					//将出生日期转换成日期类
					Calendar notice  = DateUtil.changeStringToDate(noticeString);
					//获取当前的日期
					Date current = new Date();
					java.util.Calendar calendar = Calendar.getInstance();
					calendar.setTime(current);
					if(calendar.compareTo(notice)==0){
						String noticeContent = rs.getString("notice_content");
						String noticeNum = rs.getString("notice_num");
						Map<String, String> map = new HashMap<String, String>();
						map.put("noticeContent", noticeContent);
						map.put("noticeNum", noticeNum);
						list.add(map);
					}
					
					/*int now = calendar.get(Calendar.YEAR);
					int bir = notice.get(Calendar.YEAR);
					if(now == bir){
						if(calendar.get(Calendar.MONTH)==notice.get(Calendar.MONTH)){
							if(calendar.get(Calendar.d)==notice.get(Calendar.MONTH)){
								
							}
						}
					}*/
				}
				return list;
			} catch (Exception e) {
				if (connection!=null) {
					try {
						connection.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				e.printStackTrace();
			} finally{
				JdbcTools.free(rs, ps, connection);
			}
			return null;
		}
		
		public static void main(String[] args) {
			SaveNotice saveNotice = new SaveNotice();
			System.out.println(saveNotice.getTodayNotice());
			//判断当天的日期是不是一月一号，如果是，则更新年龄
			Date today = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(today);
			System.out.println("当日时间："+calendar);
			String date = DateUtil.getTime(calendar);
			System.out.println("转换后的String格式的时间："+date);
			date = date.replaceAll("(\\d{4})(\\d{2})(\\d{2})", "$2-$3");
			System.out.println("用正则表达式转换后的String格式的时间(取月日) ："+date);
			
		}
}
