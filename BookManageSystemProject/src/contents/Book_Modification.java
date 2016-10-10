package contents;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Book_Modification extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Book_Modification dialog = new Book_Modification();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Book_Modification() {
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(12, 49, 410, 280);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel_1 = new JLabel("\uBC88\uD638");
				lblNewLabel_1.setBounds(78, 20, 57, 15);
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("\uB3C4\uC11C\uBA85");
				lblNewLabel_2.setBounds(78, 50, 57, 15);
				panel.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("\uC800\uC790");
				lblNewLabel_3.setBounds(78, 80, 57, 15);
				panel.add(lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("\uBC1C\uC0DD\uC77C");
				lblNewLabel_4.setBounds(78, 110, 57, 15);
				panel.add(lblNewLabel_4);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("\uBD84\uC57C");
				lblNewLabel_5.setBounds(78, 140, 57, 15);
				panel.add(lblNewLabel_5);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("\uBE44\uACE0");
				lblNewLabel_6.setBounds(78, 170, 57, 15);
				panel.add(lblNewLabel_6);
			}
			{
				textField = new JTextField();
				textField.setBounds(118, 50, 200, 21);
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				textField_1 = new JTextField();
				textField_1.setBounds(118, 20, 200, 21);
				panel.add(textField_1);
				textField_1.setColumns(10);
			}
			{
				textField_2 = new JTextField();
				textField_2.setBounds(118, 80, 200, 21);
				panel.add(textField_2);
				textField_2.setColumns(10);
			}
			{
				textField_3 = new JTextField();
				textField_3.setBounds(118, 110, 200, 21);
				panel.add(textField_3);
				textField_3.setColumns(10);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(118, 140, 200, 21);
				panel.add(comboBox);
			}
			{
				textField_4 = new JTextField();
				textField_4.setBounds(118, 170, 200, 40);
				panel.add(textField_4);
				textField_4.setColumns(10);
			}
			{
				JButton btnNewButton = new JButton("\uC0AD\uC81C");
				btnNewButton.setBounds(221, 247, 97, 23);
				panel.add(btnNewButton);
			}
			{
				JButton btnNewButton_1 = new JButton("\uC218\uC815");
				btnNewButton_1.setBounds(118, 247, 97, 23);
				panel.add(btnNewButton_1);
			}
		}
		{
			JLabel lblNewLabel = new JLabel("\uB3C4\uC11C\uC218\uC815/\uC0AD\uC81C");
			lblNewLabel.setBounds(30, 20, 108, 20);
			contentPanel.add(lblNewLabel);
		}
	
		setVisible(true);
	}

}
