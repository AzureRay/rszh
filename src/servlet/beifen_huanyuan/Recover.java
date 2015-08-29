package servlet.beifen_huanyuan;

import java.io.*;   
import java.lang.*; 
import java.util.List;

import bean.CommentObject;
import dao.*;
  
/*   
* 还原MySql数据库   
* */   
public class Recover {  
public boolean loadDelete(){ 
	ActivityTableDao dao = new ActivityTableDao();
	List<CommentObject> tableList = dao.Alltables();
	for(int i=0;i<tableList.size();i++){
		dao.deleteTable((String)tableList.get(i).getValues().get("TABLE_NAME"));
	}
	    return true;  
	}  
public boolean load(String username, String password,String path){  
	ActivityTableDao dao = new ActivityTableDao();
	String filepath = path; // 备份的路径地址    
      //新建数据库test   
 
      String stmt2 = "mysql -u "+username+" -p"+password+" project"+"< " + filepath;   
      String[] cmd = {"cmd","/c",stmt2}; 
      System.out.println("cmd"+cmd);
      try { 
      Runtime.getRuntime().exec(cmd); 
      System.out.println("数据已从 " + filepath + " 导入到数据库中");   
      } catch (IOException e) {   
      e.printStackTrace();   
      }   
      return true;  
} 


  

/**  
 * 根据返回结果验证是否成功  
 * @param result  
 * @return  
 */  
public boolean check(String result) {  
    return true;  
}  
}   