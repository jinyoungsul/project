package contents;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

public class MemberContents2 extends JDialog {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public MemberContents2() {
		setLayout(null);
		setSize(480, 430);
		setResizable(false);
		setLocation((int)screenSize.getWidth()/2-getWidth()/2, (int)screenSize.getHeight()/2-getHeight()/2);
		JLabel lblNewLabel = new JLabel("ÀÌ¸§");
		lblNewLabel.setFont(new Font("±¼¸²", Font.BOLD, 14));
		lblNewLabel.setBounds(52, 38, 57, 24);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(91, 41, 116, 21);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("È¸¿ø¹øÈ£");
		lblNewLabel_1.setFont(new Font("±¼¸²", Font.BOLD, 14));
		lblNewLabel_1.setBounds(219, 40, 68, 21);
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(287, 41, 116, 21);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("ÁÖ¼Ò");
		lblNewLabel_2.setFont(new Font("±¼¸²", Font.BOLD, 14));
		lblNewLabel_2.setBounds(52, 90, 57, 24);
		add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(91, 93, 312, 21);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("ÀüÈ­¹øÈ£");
		lblNewLabel_3.setFont(new Font("±¼¸²", Font.BOLD, 14));
		lblNewLabel_3.setBounds(25, 149, 68, 24);
		add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(91, 152, 116, 21);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("»ý³â¿ùÀÏ");
		lblNewLabel_4.setFont(new Font("±¼¸²", Font.BOLD, 14));
		lblNewLabel_4.setBounds(219, 151, 68, 21);
		add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(287, 152, 116, 21);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("ºñ°í");
		lblNewLabel_5.setFont(new Font("±¼¸²", Font.BOLD, 14));
		lblNewLabel_5.setBounds(52, 202, 48, 24);
		add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setBounds(91, 205, 312, 85);
		add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnNewButton = new JButton("°¡ÀÔ");
		btnNewButton.setFont(new Font("±¼¸²", Font.BOLD, 14));
		btnNewButton.setBounds(118, 328, 97, 36);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ãë¼Ò");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				
			}
		});
		btnNewButton_1.setFont(new Font("±¼¸²", Font.BOLD, 14));
		btnNewButton_1.setBounds(271, 328, 97, 36);
		add(btnNewButton_1);
		
		
		setVisible(true);
	}
}

