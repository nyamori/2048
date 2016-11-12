package my2048;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * 主要提供txt文本的操作。
 */
public class TxtOperation {

	private int[][] number = new int[4][4];
	private int maxPoint;
	private int point;
/*
 * 读取对应文本中的字符串。
 */
	public String readTxtFile(String path) throws Exception {         
		File file = new File(path);

		String aString = "";
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");

			BufferedReader reader = new BufferedReader(read);
			String line = null;

			while ((line = reader.readLine()) != null) {
				aString += line;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return aString;

	}
/*
 * 将字符串存入txt文本中，若已有对应文本，删除后创建新文本。
 */
	public boolean writeTxtFile(String content, File fileName) throws Exception {
		boolean flag = false;
		FileOutputStream o = null;
		try {
		
			if (fileName.exists()) {
				fileName.delete();
			}
			fileName.createNewFile();
			o = new FileOutputStream(fileName);
			o.write(content.getBytes("UTF-8"));
			o.close();
			flag = true;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return flag;
	}

	public int[][] getNumber() {
		return number;
	}

	public int getPoint() {
		return point;
	}

	public int getMaxPoint() {
		return maxPoint;
	}

}
