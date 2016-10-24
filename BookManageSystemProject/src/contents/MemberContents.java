package contents;

import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.*;

import dao.MemberDAO;
import dto.MemberDTO;

public class MemberContents extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private String[] columnNames = { "No", "회원번호", "이름", "주소", "전화번호", "생년월일", "비고" };
	private JScrollPane scrollPane = new JScrollPane();
	private ArrayList<MemberDTO> memberList = null;
	private MemberDTO member = null;
	private DefaultTableModel model;
	private JButton button; // 검색버튼
	private JButton button_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_1;
	private MemberContents memberContents;
	private MemberDAO dao = new MemberDAO();

	public MemberContents() {
		setLayout(null);
		memberContents = this;
		MyBtnListener listener = new MyBtnListener();
		JLabel lblNewLabel = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(25, 54, 92, 39);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(114, 32, 151, 21);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\uC774\uB984");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_1.setBounds(277, 21, 81, 39);
		add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(114, 65, 151, 21);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\uD68C\uC6D0\uBC88\uD638");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel_2.setBounds(25, 27, 92, 27);
		add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(328, 32, 151, 21);
		add(textField_2);
		textField_2.setColumns(10);

		btnNewButton_1 = new JButton("삭제");
		btnNewButton_1.addActionListener(listener);
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_1.setBounds(838, 54, 107, 39);
		add(btnNewButton_1);

		btnNewButton_2 = new JButton("수정");
		btnNewButton_2.addActionListener(listener);
		btnNewButton_2.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton_2.setBounds(730, 54, 107, 39);
		add(btnNewButton_2);
		// setVisible(true);
		scrollPane.setBounds(25, 103, 920, 473);
		add(scrollPane);

		button = new JButton("검색");
		button.addActionListener(listener);
		button.setFont(new Font("굴림", Font.BOLD, 20));
		button.setBounds(516, 54, 107, 39);
		add(button);

		button_1 = new JButton("\uAC00\uC785");
		button_1.addActionListener(new MyBtnListener());
		button_1.setFont(new Font("굴림", Font.BOLD, 20));
		button_1.setBounds(623, 54, 107, 39);
		add(button_1);
		selectTable();

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
	    super.paintComponent(g);
		
	}

	class MyBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==button){					//검색버튼
				selectTable();
			} else if (e.getSource() == button_1) { // 가입버튼
				new Member_Resiteration(memberContents);
			}  
			else if (e.getSource() == btnNewButton_2) { // 수정버튼
				int row = table.getSelectedRow();
				if (table.isRowSelected(row)) {
					String memberNo = model.getValueAt(row, 1).toString();
					String name = model.getValueAt(row, 2).toString();
					String address = model.getValueAt(row, 3).toString();
					String phoneNumber = model.getValueAt(row, 4).toString();
					String birthday = model.getValueAt(row, 5).toString();
					String comment = model.getValueAt(row, 6).toString();
					new Member_Modification(memberContents, memberNo, name, address, phoneNumber, birthday, comment);
				}
			} else if (e.getSource() == btnNewButton_1) { // 삭제버튼
				int row = table.getSelectedRow();
				if (table.isRowSelected(row)) {
					Object[] options = {"삭제",
		                    "취소"};
					int value = JOptionPane.showOptionDialog(new JFrame(), "해당 회원을 \n정말 삭제하시겠습니까?", "삭제확인", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
					if (value == JOptionPane.YES_OPTION) {
						int memberNo = Integer.parseInt(model.getValueAt(row, 1).toString());
						dao.deleteMember(memberNo);
					} 
				}
				selectTable();
			}
		}
	}

	public void selectTable() {

		int i = 1;

		model = new DefaultTableModel(columnNames, 0);
		table = new JTable();
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(15);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(206);
		
		if(textField.getText().length()>0){
			int memberNo = Integer.parseInt(textField.getText());
			memberList = new MemberDAO().searchKeywordMember(memberNo);
		} else if(  textField_2.getText().length()>0 || textField_1.getText().length()>0){
			String name = textField_2.getText();
			String phoneNumber = textField_1.getText();
			memberList = new MemberDAO().searchKeywordMember(name,phoneNumber);
		}
	else {
			memberList = new MemberDAO().searchMember();
		}
		Iterator<MemberDTO> e = memberList.iterator();

		while (e.hasNext()) {
			member = (MemberDTO) e.next();
			model.addRow(new Object[] { i, member.getMemberNo(), member.getName(), member.getAddress(),
					member.getPhoneNumber(), member.getBirthday(), member.getComment() });
			i++;
		}
		DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
		tableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for(int c=0;c<table.getColumnCount();c++)
			table.getColumnModel().getColumn(c).setCellRenderer(tableCellRenderer);

		scrollPane.setViewportView(table);

	}

	public DefaultTableModel getModel() {
		return model;
	}
	
}
