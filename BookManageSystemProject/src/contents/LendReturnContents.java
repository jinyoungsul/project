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
				"�����̸�", "��ü����", "�뿩��¥", "����", "���ǻ�"
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
					"�����̸�", "��ü����", "�뿩��¥", "����", "���ǻ�"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(190);
		
		JButton btnNewButton = new JButton("�ݳ�");
		btnNewButton.setBounds(800, 330, 130, 32);
		add(btnNewButton);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(8, 202, 21, 23);
		add(chckbxNewCheckBox);
		
		JButton button = new JButton("�뿩");
		button.setBounds(800, 583, 130, 32);
		add(button);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"�̸�", null},
				{"�ּ�", null},
				{"�޴���", null},
				{"ȸ����ȣ", null},
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
		
		JButton btnNewButton_1 = new JButton("���");
		btnNewButton_1.setBounds(870, 114, 60, 21);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("ȸ���˻�");
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
		g.drawString("�뿩���", 500, 165);
		g.drawString("�űԴ뿩", 500, 425);
		g.drawString("ȸ������", 30, 65);
		g.drawString("�����ڵ�", 690, 130);
	}
	
	public class MyLisner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			search s = new search();
			
		}
		
	}
	
}
