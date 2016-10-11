package contents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberContents extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private String[] columnNames = { "No", "회원번호", "이름", "주소", "전화번호", "생년월일", "비고" };
	private ResultSet rs = null;
	private int i = 1;
	private ArrayList<MemberDTO> memberList = null;
	private MemberDTO member = null;

	public MemberContents() {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(46, 42, 92, 39);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(139, 53, 151, 21);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setBounds(46, 102, 81, 39);
		add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(139, 113, 151, 21);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\uD68C\uC6D0\uBC88\uD638");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2.setBounds(357, 48, 92, 27);
		add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(448, 53, 151, 21);
		add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(448, 94, 151, 40);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("삭제");
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.setBounds(788, 95, 107, 39);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("수정");
		btnNewButton_2.addActionListener(new MyBtnListener());
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_2.setBounds(788, 42, 107, 39);
		add(btnNewButton_2);
		// setVisible(true);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 179, 787, 410);
		add(scrollPane);
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		table = new JTable();
		table.setModel(model);

		memberList = new MemberDAO().searchMember();
		Iterator<MemberDTO> e = memberList.iterator();

		while (e.hasNext()) {
			member = (MemberDTO) e.next();
			model.addRow(new Object[] { i, member.getMemberNo(), member.getName(), member.getAddress(),
					member.getAddress(), member.getBirthday(), member.getComment() });
			i++;
		}

		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(206);
		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(344, 609, 2, 2);
		add(scrollPane_1);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}

	class MyBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			MemberContents2 mc = new MemberContents2();
		}

	}
}
