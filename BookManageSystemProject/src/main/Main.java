package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class Main extends JFrame{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JButton memberBtn = new JButton("회원관리");
	private JButton bookBtn = new JButton("도서관리");
	private JButton rent_return_Btn = new JButton("대여반납 관리");
	private JButton salesBtn = new JButton("매출관리");
	
	private JPanel btnPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	
	public Main(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("도서대여관리 System");
		setSize(1000,800);
		setLocation((int)screenSize.getWidth()/2-getWidth()/2, (int)screenSize.getHeight()/2-getHeight()/2);
		getContentPane().setLayout(null);
		
		
		btnPanel.setBounds(0, 0, 984, 86);
		getContentPane().add(btnPanel);
		btnPanel.setLayout(new GridLayout(0, 4, 0, 0));
		btnPanel.add(memberBtn);
		btnPanel.add(bookBtn);
		btnPanel.add(rent_return_Btn);
		btnPanel.add(salesBtn);
		
		centerPanel.setBounds(0, 86, 984, 834);
		getContentPane().add(centerPanel);
		
		bottomPanel.setBounds(0, 920, 984, 42);
		getContentPane().add(bottomPanel);
		
		setVisible(true);
		
	}
}
