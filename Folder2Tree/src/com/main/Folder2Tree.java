package com.main;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * @author Zhangjiacheng
 * @modifier Mawenjie
 */

public class Folder2Tree extends JFrame {

	private static String[] stopNames = { "\\.{1}.*" };
	private static FileWriter outFile;
	private static File folder;

	public static void doProcess(String input, String output) {
		folder = new File(input);
		try {
			outFile = new FileWriter(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * File outFile = new File(output);
		 * 
		 * if (!outFile.exists()) { outFile.createNewFile(); }
		 */
		if (!folder.isDirectory()) {
			JOptionPane.showMessageDialog(null, folder.getPath() + "不是一个文件夹");
			/* System.out.println(folder.getAbsolutePath() + " 不是一个文件夹"); */
		} else {
			getFolderContent(folder, 0);
			try {
				outFile.flush();
				JOptionPane.showMessageDialog(null, "输出文件成功！");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void getFolderContent(File folder, int spaceNumber) {
		/*
		 * int folderNumber = folder.listFiles(pathname ->
		 * pathname.isDirectory()).length;
		 */
		int tempNum=1;
		System.out.println(folder+"第"+tempNum+++"个");
		try {
			/* if (folderNumber > 0) { */
			for (int i = 0; i < spaceNumber; i++) {
				outFile.write("\t");
			}
			outFile.write("- " + folder.getName());
			outFile.write("\r\n");
			/* } */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File[] files = folder.listFiles();
		if (files.length > 0) {
			for (File file : files) {
				/*
				 * if (file.isFile()) { System.out.println(file.getName()); }
				 */
				if (file.isDirectory() && stopFolder(file.getName(), stopNames)) {
					getFolderContent(file, spaceNumber + 1);
				}

				/*
				 * if (stopFolder(file.getName(), stopNames) && file.isDirectory()) {
				 * getFolderContent(file, spaceNumber + 1); }
				 */
			}
		}

	}

	private static boolean stopFolder(String name, String[] stopNames) {
		for (String stopName : stopNames) {
			if (Pattern.matches(stopName, name)) {
				return false;
			}
		}
		return true;
	}

}
