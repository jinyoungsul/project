package contents;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import contents.LendReturnContents.MyLisner;

public class search extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private Font f1;

	public search() {
		MyLisner lisner = new MyLisner();

		f1 = new Font("bold", Font.BOLD, 15);
		setVisible(true);
		setSize(600, 450);
		setLocation((int)(screenSize.getWidth()/2-getWidth()/2),(int)(screenSize.getHeight()/2-getHeight()/2));

		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 562);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(291, 191, -125, -184);
		panel.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(42, 161, 498, 186);
		panel.add(scrollPane_1);

		table = new JTable();
		table.setModel(
				new DefaultTableModel(
						new Object[][] { { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null }, { null, null, null, null },
								{ null, null, null, null }, { null, null, null, null }, },
						new String[] { "회원번호", "이름", "전화번호", "비고" }));
		scrollPane_1.setViewportView(table);

		JButton btnNewButton = new JButton("검색");
		btnNewButton.setBounds(387, 91, 153, 30);
		btnNewButton.addActionListener(lisner);
		panel.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(115, 36, 134, 30);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(387, 36, 153, 30);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(115, 91, 134, 30);
		panel.add(textField_2);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.setFont(f1);
		g.drawString("전화번호", 55, 85);
		g.drawString("회원번호", 327, 85);
		g.drawString("이름", 55, 141);
	}

	public class MyLisner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
