package com.main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * 
 * @author Mawenjie
 *
 */
public class MainFrame extends JFrame {
	static Folder2Tree folder2Tree = new Folder2Tree();
	static MainFrame mainFrame = new MainFrame();
	static JLabel inputLabel = new JLabel("需要分析的文件目录：");
	static JLabel outputLabel = new JLabel("输出文本的地址目录：");
	static JTextField inputText = new JTextField();
	static JTextField outputText = new JTextField();
	static JButton sureBtn = new JButton("确认输出");
	static JButton inputerBtn = new JButton("...");
	static JButton outerBtn = new JButton("...");
	static JFileChooser chooser = new JFileChooser();

	public void init() {
		System.out.println(System.getProperty("java.class.path"));
		Image icon = Toolkit.getDefaultToolkit().getImage(System.getProperty("java.class.path")+"\\文件夹图标.png"); // 引号内为图片所在位置，但是路径只能用\\,是双斜杠哦~
		mainFrame.setIconImage(icon);
		mainFrame.setTitle("文件目录读取器");
		mainFrame.setBounds(500, 300, 500, 300);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFrame.setLayout(new GridLayout(3, 1));
		Container above = new Container();
		Container behind = new Container();
		Container last = new Container();

		above.add(inputLabel);
		above.add(inputText);
		above.add(inputerBtn);

		last.add(sureBtn);
		behind.add(outputLabel);
		behind.add(outputText);
		behind.add(outerBtn);

		mainFrame.add(above);
		mainFrame.add(behind);
		mainFrame.add(last);
		inputerBtn.setBounds(400, 50, 25, 25);
		sureBtn.setBounds(200, 0, 100, 25);
		outerBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.setCurrentDirectory(new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop"));
				chooser.setFileSelectionMode(0);// 设定只能选择到文件夹
				int state = chooser.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
				if (state == 1) {
					return;
				} else {
					File f = chooser.getSelectedFile();// f为选择到的目录
					outputText.setText(f.getAbsolutePath());
				}
			}
		});
		inputerBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.setCurrentDirectory(new File("C:\\Users\\" + System.getProperty("user.name") + "\\Desktop"));
				chooser.setFileSelectionMode(1);// 设定只能选择到文件夹
				int state = chooser.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
				if (state == 1) {
					return;
				} else {
					File f = chooser.getSelectedFile();// f为选择到的目录
					inputText.setText(f.getAbsolutePath());
				}
			}
		});
		sureBtn.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String input = inputText.getText().trim();
				String output = outputText.getText().trim();
				folder2Tree.doProcess(input, output);

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		inputLabel.setBounds(50, 50, 150, 25);
		inputText.setBounds(200, 50, 200, 25);
		outputLabel.setBounds(50, 25, 150, 25);
		outputText.setBounds(200, 25, 200, 25);
		outerBtn.setBounds(400, 25, 25, 25);
		/*
		 * mainFrame.add(inputLabel); mainFrame.add(inputLabel);
		 */
		mainFrame.setVisible(true);
	}

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.init();
	}
}
