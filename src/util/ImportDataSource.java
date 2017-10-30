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

	private String getDataSourcePath() {
		// �����ļ�ѡ����
		JFileChooser fileChooser = new JFileChooser();
		// ���õ�ǰĿ¼
		// fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setAcceptAllFileFilterUsed(false);
		final String[][] fileENames = {
		// { ".java", "JAVAԴ���� �ļ�(*.java)" },
		// { ".doc", "MS-Word 2003 �ļ�(*.doc)" },
		{ ".xls", "MS-Excel 2003 �ļ�(*.xls)" } };
		// ѭ�������Ҫ��ʾ���ļ�
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
		}
		return null;
	}

	/**
	 * ����ѵ������Դ
	 * 
	 * @return
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
			// ��õ�һ�����������(ecxel��sheet�ı�Ŵ�0��ʼ,0,1,2,3,....)
			sheet = book.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				Index Index = new Index();
				// ��ȡÿһ�еĵ�Ԫ��
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
// ����ϵͳ�����ͨ��������ѯ�����������
// Sheet sheet;
// Workbook book;
// double[][] data = new double[30][38];
// int count = 0;
// try {
// InputStream is = new FileInputStream(
// "C:/Users/admin/Desktop/test.xls");
// book = Workbook.getWorkbook(is);
// // ��õ�һ�����������(ecxel��sheet�ı�Ŵ�0��ʼ,0,1,2,3,....)
// sheet = book.getSheet(0);
// for (int i = 0; i < sheet.getRows(); i++) {
// // ��ȡÿһ�еĵ�Ԫ��
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
