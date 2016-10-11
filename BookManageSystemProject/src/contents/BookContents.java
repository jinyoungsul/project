package contents;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BookContents extends JPanel{
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	public BookContents() {
		setLayout(null);
		setSize(900, 600);
		
		JLabel lblNewLabel = new JLabel("도서검색");
		lblNewLabel.setBounds(70, 60, 57, 15);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(70, 80, 250, 25);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.setBounds(390, 80, 100, 25);
		add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("저자검색");
		lblNewLabel_1.setBounds(70, 130, 57, 15);
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(70, 155, 250, 25);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("검색");
		btnNewButton_1.setBounds(390, 153, 100, 25);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("도서등록");
		btnNewButton_2.setBounds(721, 78, 150, 25);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("도서수정/삭제");
		btnNewButton_3.setBounds(721, 153, 150, 25);
		add(btnNewButton_3);
		
		
		btnNewButton_2.addActionListener(new btn1Listener());
		btnNewButton_3.addActionListener(new btn2Listener());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 246, 801, 354);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setBackground(Color.WHITE);
		table.setFont(new Font("굴림", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, "", "", "", "", "", "", ""},
				{null, null, null, null, null, null, "", null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"NO", "\uB3C4\uC11C\uBC88\uD638", "\uB3C4\uC11C\uBA85", "\uB300\uC5EC", "\uB300\uC5EC\uD69F\uC218", "\uC800\uC790", "\uB4F1\uB85D\uC77C", "\uBD84\uC57C", "\uBE44\uACE0"
			}
		));
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawString("도서페이지", 450, 400);
	}
	class btn1Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Book_Resiteration resiteration = new Book_Resiteration();
		}
	}
	class btn2Listener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Book_Modification modification = new Book_Modification();
		}
	}
}

