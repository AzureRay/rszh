package servlet.rsjl_import;

import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import bean.CommentObject;
import jxl.*;

public class importDao {

	/**
	 * 导入excel操作的类
	 * 
	 * 
	 */
	public static class ExcelOperationUtil {
		/**
		 * 读取Excel中的列
		 * 
		 * @param filePath
		 * @return
		 */

		public static List<String> readExcelColumnData(String filePath) {
             Dialog log;
			List<String> list1 = new ArrayList<String>();
			List<CommentObject> list = new ArrayList<CommentObject>();
			try {
				File xlsFile = new File(filePath);
	           FileInputStream fs = new FileInputStream(xlsFile);
				Workbook book = Workbook.getWorkbook(fs);// 获取工作簿对象
				Sheet sheet = book.getSheet(0);// 获取工作表对象
				int rows = sheet.getRows();// 获取工作表中的数据行数
				int columns = sheet.getColumns();
				for (int j = 0; j < columns; j++) {
					list1.add(sheet.getCell(j, 0).getContents());
				}
            fs.close();
				return list1;
			} catch (Exception e) {
				System.out.println("异常信息：" + e.getMessage());
				e.printStackTrace();
				return null;
			}
		}

		/**
		 * 读取Excel中的数据
		 * 
		 * @param Excel存放的路径
		 * @return 对应的表中的数据
		 */
		public static List<Vector> readExcelData(String filePath) {
			List<Vector> list = new ArrayList<Vector>();
			try {
				File xlsFile = new File(filePath);
				FileInputStream fs = new FileInputStream(xlsFile);
				Workbook book = Workbook.getWorkbook(fs);// 获取工作簿对象
				Sheet sheet = book.getSheet(0);// 获取工作表对象
				int rows = sheet.getRows();// 获取工作表中的数据行数
				int columns = sheet.getColumns();
				for (int i = 1; i < rows; i++) {// 循环Excel工作表的行，并读取单元格数据
					Vector s = new Vector(columns);
					for (int j = 0; j < columns; j++) {
						s.addElement(sheet.getCell(j, i).getContents());
					}
					list.add(s);
              fs.close();
				}
				System.out.println(list.get(0).get(0));
				return list;
			} catch (Exception e) {
				System.out.println("异常信息：" + e.getMessage());
				e.printStackTrace();
				return null;
			}
		}
	}

	public void removeAll(File f) {
		if (f.isFile() || f.list().length == 0) {
			System.out.print(f.delete());
		} else {
			File[] fs = f.listFiles();
			for (File file : fs) {
				removeAll(file);
				System.out.print(file.delete());
			}
		}

	}

}