package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import jxl.Sheet;
import jxl.Workbook;
import model.Index;

public class ImportDataSource {

	public static void main(String[] args) {
		JFileChooser jf = new JFileChooser();
		jf.setFileSelectionMode(JFileChooser.SAVE_DIALOG
				| JFileChooser.DIRECTORIES_ONLY);
		jf.showDialog(null, null);
		File fi = jf.getSelectedFile();
		String f = fi.getAbsolutePath() + "\\test.txt";
		System.out.println("save: " + f);
		try {
			FileWriter out = new FileWriter(f);
			out.write("successful!!!");
			out.close();
		} catch (Exception ee) {
		}
		jf.setSize(500, 100);
		jf.setVisible(true);
	}

	/**
	 * 导入训练数据源
	 * 
	 * @return
	 */
	public List<Index> importExcel() {

		Sheet sheet;
		Workbook book;

		List<Index> list = new ArrayList<Index>();
		try {
			InputStream is = new FileInputStream(
					"C:/Users/admin/Desktop/importdata.xls");
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
			System.out.println("there is something wrong with import excel!");
		}
		return list;
	}
}
// 仿真系统：输出通道质量查询界面表格的数据
// Sheet sheet;
// Workbook book;
// double[][] data = new double[30][38];
// int count = 0;
// try {
// InputStream is = new FileInputStream(
// "C:/Users/admin/Desktop/test.xls");
// book = Workbook.getWorkbook(is);
// // 获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
// sheet = book.getSheet(0);
// for (int i = 0; i < sheet.getRows(); i++) {
// // 获取每一行的单元格
// for (int j = 0; j < 38; j++) {
// data[count][j] = Double.parseDouble(sheet.getCell(j, i)
// .getContents());
// }
// count++;
// }
// book.close();
// } catch (Exception e) {
// System.out.println("there is something wrong with import excel!");
// }
// for (int i = 1; i <= count; i++) {
// StringBuilder sb = new StringBuilder();
// sb.append("{no:\"" + i + "\",zb1:" + data[i - 1][0]);
// for (int j = 2; j <= 38; j++) {
// sb.append(",zb" + j + ":" + data[i - 1][j - 1]);
// }
// sb.append("},");
// System.out.println(sb);
// }
