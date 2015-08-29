package bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储记录的对象，把从数据库中查询到的每条记录封装成一个该对象
 * 该对象包含一个map,键：列名，值：列值
 * @author Tianci
 */
public class CommentObject {
	
	/**
	 * 存储表某一行的值的容器
	 */
	private Map<String, Object> values = new HashMap<String, Object>();
	
	/**
	 * 返回容器
	 * @return
	 */
	public Map<String, Object> getValues(){
		return values;
	}
	
	/**
	 * 添加某一列的值
	 * @param key 列名
	 * @param value 列值
	 */
	public void addValue(String key,Object value){
		values.put(key, value);
	}

	@Override
	public String toString() {
		return "CommentObject [values=" + values + "]";
	}
	
}
