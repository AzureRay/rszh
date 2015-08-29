package util;

import java.util.Calendar;

public class DateUtil {
	public static int getDay(String start, String end) {
		Calendar before = DateUtil.changeStringToDate(start); // 得到开始时间对象
		Calendar after = DateUtil.changeStringToDate(end); // 得到结束时间对象
		long first = before.getTimeInMillis(); // 得到开始时间的毫秒值
		long second = after.getTimeInMillis(); // 得到结束时间的毫秒值
		int day = (int) ((second - first) / (1000 * 60 * 60 * 24));
		return day;
	}

	public static String addSomeDay(String date, int day) {
		Calendar cal = DateUtil.changeStringToDate(date);
		cal.add(Calendar.DAY_OF_MONTH, day);
		String newString = DateUtil.getDateString(cal);
		return newString;
	}

	public static String getDateString(Calendar cal) {
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONDAY) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (month < 10 && day < 10)
			return year + "-0" + month + "-0" + day;
		if (month < 10)
			return year + "-0" + month + "-" + day;
		if (day < 10)
			return year + "-" + month + "-0" + day;
		return year + "-" + month + "-" + day;
	}

	public static String getTime(Calendar cal) {
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONDAY) + 1;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (month < 10 && day < 10)
			return year + "0" + month + "0" + day;
		if (month < 10)
			return year + "0" + month + day;
		if (day < 10)
			return year + "" + month + "0" + day;
		return "" + year + month + day;
	}

	public static Calendar changeStringToDate(String date) {
		String regex = "-"; // 拆分字符串的正则式
		String d[] = date.split(regex); // 拆分得到字符串数组
		int year = Integer.parseInt(d[0]); // 得到年份
		int month = Integer.parseInt(d[1]) - 1; // 得到月份
		int day = Integer.parseInt(d[2]); // 得到这天是该月中的第几天
		Calendar calendar = Calendar.getInstance(); // 得到一个Calendar实例
		calendar.set(year, month, day); // 设置日期为字符串内容的日期
		return calendar; // 返回日期对象
	}

	public static void main(String[] args) {

		System.out.println(addSomeDay("2014-11-08", 60));
		System.out.println(getDateString(changeStringToDate("2005-10-4")));
	}
}