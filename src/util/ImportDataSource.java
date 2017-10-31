package util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import jxl.Sheet;
import jxl.Workbook;
import model.Index;

public class ImportDataSource {

	public static void main(String[] args) {
	}

	/**
	 * 获取训练数据源的位置
	 * 
	 * @return 返回数据源的路径
	 */
	private String getDataSourcePath() {
		// 创建文件选择器
		JFileChooser fileChooser = new JFileChooser();
		// 设置当前目录
		// fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setAcceptAllFileFilterUsed(false);
		final String[][] fileENames = {
		// { ".java", "JAVA源程序 文件(*.java)" },
		// { ".doc", "MS-Word 2003 文件(*.doc)" },
		{ ".xls", "MS-Excel 2003 文件(*.xls)" } };
		// 循环添加需要显示的文件
		for (final String[] fileEName : fileENames) {
			fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File file) {
					if (file.getName().endsWith(fileEName[0])
							|| file.isDirectory()) {
						return true;
					}
					return false;
				}

				public String getDescription() {
					return fileEName[1];
				}
			});
		}
		fileChooser.showDialog(null, null);
		try {
			File file = fileChooser.getSelectedFile();
			String path = file.getAbsolutePath();
			// System.out.println(path);
			return path.replace("\\", "/");
			// System.out.println(path.replace("\\", "/"));
		} catch (NullPointerException e) {
			System.out.println("You selected nothing!");
		}
		return null;
	}

	/**
	 * 导入训练数据源
	 * 
	 * @return 返回训练数据源
	 */
	public List<Index> importDataSource() {
		String filePath = getDataSourcePath();
		if (filePath == null) {
			return null;
		}
		Sheet sheet;
		Workbook book;
		List<Index> list = new ArrayList<Index>();
		try {
			InputStream is = new FileInputStream(filePath);
			book = Workbook.getWorkbook(is);
			// 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
			sheet = book.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				Index Index = new Index();
				// 获取每一行的单元格
				double[] temp = new double[38];
				for (int j = 0; j < 38; j++) {
					temp[j] = Double.parseDouble(sheet.getCell(j, i)
							.getContents());
					// System.out.println(sheet.getCell(j, i).getContents());
					// System.out.println("temp[" + j + "]=" + temp[j]);
				}
				Index.setTarget(temp);
				list.add(Index);
			}
			book.close();
		} catch (Exception e) {
			System.out.println("There is something wrong with import excel!");
		}
		return list;
	}
}