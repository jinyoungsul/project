package contents;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Book_Resiteration extends JDialog {

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
			Book_Resiteration dialog = new Book_Resiteration();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Book_Resiteration() {
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
				lblNewLabel_1.setBounds(76, 20, 57, 15);
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("\uB3C4\uC11C\uBA85");
				lblNewLabel_2.setBounds(76, 50, 57, 15);
				panel.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("\uC800\uC790");
				lblNewLabel_3.setBounds(76, 80, 57, 15);
				panel.add(lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("\uB4F1\uB85D\uC77C");
				lblNewLabel_4.setBounds(76, 110, 57, 15);
				panel.add(lblNewLabel_4);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("\uBD84\uC57C");
				lblNewLabel_5.setBounds(76, 140, 57, 15);
				panel.add(lblNewLabel_5);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("\uBE44\uACE0");
				lblNewLabel_6.setBounds(76, 170, 57, 15);
				panel.add(lblNewLabel_6);
			}
			{
				textField = new JTextField();
				textField.setBounds(116, 50, 200, 21);
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				textField_1 = new JTextField();
				textField_1.setBounds(116, 20, 200, 21);
				panel.add(textField_1);
				textField_1.setColumns(10);
			}
			{
				textField_2 = new JTextField();
				textField_2.setBounds(116, 80, 200, 21);
				panel.add(textField_2);
				textField_2.setColumns(10);
			}
			{
				textField_3 = new JTextField();
				textField_3.setBounds(116, 110, 200, 21);
				panel.add(textField_3);
				textField_3.setColumns(10);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "\uC21C\uC815", "\uBB34\uD611", "\uCD94\uB9AC", "\uC561\uC158", "\uC131\uC778"}));
				comboBox.setBounds(116, 140, 200, 21);
				panel.add(comboBox);
			}
			{
				textField_4 = new JTextField();
				textField_4.setBounds(116, 170, 200, 40);
				panel.add(textField_4);
				textField_4.setColumns(10);
			}
			{
				JButton btnNewButton = new JButton("\uB4F1\uB85D");
				btnNewButton.setBounds(219, 247, 97, 23);
				panel.add(btnNewButton);
			}
		}
		{
			JLabel lblNewLabel = new JLabel("\uB3C4\uC11C\uB4F1\uB85D");
			lblNewLabel.setBounds(30, 20, 60, 20);
			contentPanel.add(lblNewLabel);
		}
		
		
		setVisible(true);
	}

}
