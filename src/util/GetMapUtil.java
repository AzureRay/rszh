package util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 将请求中的键值对放入map中
 * 
 * @author Tianci
 * 
 */
public class GetMapUtil {

	public static Map<String, Object> getRequestMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			Object val = request.getParameter(name);
			System.out.println("hhhiiii------" + name + ": " + val);
			map.put(name, val);
		}
		return map;
	}
}
