package contents;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.MemberDAO;
import dto.MemberDTO;

class Member_Modification extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JButton btnNewButton;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public Member_Modification(MemberContents memberContents,String memberNo,String name,String address,String phoneNumber,String birthday,String comment) {

		setLayout(null);
		setSize(480, 430);
		setResizable(false);
		setLocation((int) screenSize.getWidth() / 2 - getWidth() / 2,
				(int) screenSize.getHeight() / 2 - getHeight() / 2);
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel.setBounds(52, 38, 57, 24);
		add(lblNewLabel);

		textField = new JTextField(name);
		textField.setBounds(91, 41, 116, 21);
		add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("회원번호");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_1.setBounds(219, 40, 68, 21);
		add(lblNewLabel_1);

		textField_1 = new JTextField(memberNo);
		textField_1.setBounds(287, 41, 116, 21);
		textField_1.setEnabled(false);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("주소");
		lblNewLabel_2.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_2.setBounds(52, 90, 57, 24);
		add(lblNewLabel_2);

		textField_2 = new JTextField(address);
		textField_2.setBounds(91, 93, 312, 21);
		add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("전화번호");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_3.setBounds(25, 149, 68, 24);
		add(lblNewLabel_3);

		textField_3 = new JTextField(phoneNumber);
		textField_3.setBounds(91, 152, 116, 21);
		add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("생년월일");
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_4.setBounds(219, 151, 68, 21);
		add(lblNewLabel_4);

		textField_4 = new JTextField(birthday);
		textField_4.setBounds(287, 152, 116, 21);
		add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("비고");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 14));
		lblNewLabel_5.setBounds(52, 202, 48, 24);
		add(lblNewLabel_5);

		textField_5 = new JTextField(comment);
		textField_5.setBounds(91, 205, 312, 85);
		add(textField_5);
		textField_5.setColumns(10);

		btnNewButton = new JButton("수정");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 14));
		btnNewButton.setBounds(118, 328, 97, 36);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int memberNo = Integer.parseInt(textField_1.getText());
				String name = textField.getText();
				String address = textField_2.getText();
				String phoneNumber = textField_3.getText();
				String birthday = textField_4.getText();
				String comment = textField_5.getText();
				MemberDAO dao = new MemberDAO();
				MemberDTO member = new MemberDTO(memberNo,name, address, phoneNumber, birthday, comment);
				int result = dao.updateMember(member);
				System.out.println("회원정보 변경 완료 : "+result);
				memberContents.getModel().setRowCount(0);
				memberContents.selectTable();
				dispose();
			}
		});
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();

			}
		});
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 14));
		btnNewButton_1.setBounds(271, 328, 97, 36);
		add(btnNewButton_1);
		setVisible(true);
	}
}