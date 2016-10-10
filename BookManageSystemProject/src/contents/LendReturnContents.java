package contents;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LendReturnContents extends JPanel{
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private Font f1;
	private JTextField textField;
	public LendReturnContents() {
		MyLisner lisner = new MyLisner();
		f1 = new Font("bold", Font.BOLD,13 );
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 181, 907, 122);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"도서이름", "연체여부", "대여날짜", "저자", "출판사"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(190);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(29, 440, 907, 122);
		add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
					"도서이름", "연체여부", "대여날짜", "저자", "출판사"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(190);
		
		JButton btnNewButton = new JButton("반납");
		btnNewButton.setBounds(800, 330, 130, 32);
		add(btnNewButton);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(8, 202, 21, 23);
		add(chckbxNewCheckBox);
		
		JButton button = new JButton("대여");
		button.setBounds(800, 583, 130, 32);
		add(button);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"이름", null},
				{"주소", null},
				{"휴대폰", null},
				{"회원번호", null},
			},
			new String[] {
				"",""
			}
		));
		table_2.getColumnModel().getColumn(1).setPreferredWidth(130);
		table_2.setBounds(29, 74, 269, 64);
		add(table_2);
		
		textField = new JTextField();
		textField.setBounds(750, 114, 116, 21);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("등록");
		btnNewButton_1.setBounds(870, 114, 60, 21);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("회원검색");
		btnNewButton_2.addActionListener(lisner);
		btnNewButton_2.setBounds(810, 64, 120, 21);
		add(btnNewButton_2);
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.BLACK);
		g.setFont(f1);
		g.drawString("대여목록", 500, 165);
		g.drawString("신규대여", 500, 425);
		g.drawString("회원정보", 30, 65);
		g.drawString("도서코드", 690, 130);
	}
	
	public class MyLisner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			search s = new search();
			
		}
		
	}
	
}
