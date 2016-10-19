package contents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.BookDao;
import dto.BookDTO;

public class Book_Resiteration extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private BookDao dao = new BookDao();
	
	public Book_Resiteration(BookContents bookContents) {
		setSize(400,400);
		setLocation((int)(screenSize.getWidth()/2-getWidth()/2), (int)(screenSize.getHeight()/2-getHeight()/2));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(26, 50, 346, 231);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblNewLabel_1 = new JLabel("\uBC88\uD638");
				lblNewLabel_1.setBounds(44, 35, 57, 15);
				panel.add(lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("\uB3C4\uC11C\uBA85");
				lblNewLabel_2.setBounds(44, 60, 57, 15);
				panel.add(lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("\uC800\uC790");
				lblNewLabel_3.setBounds(44, 85, 57, 15);
				panel.add(lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("\uBD84\uC57C");
				lblNewLabel_5.setBounds(44, 116, 57, 15);
				panel.add(lblNewLabel_5);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("\uBE44\uACE0");
				lblNewLabel_6.setBounds(44, 141, 57, 15);
				panel.add(lblNewLabel_6);
			}
			{
				textField_1 = new JTextField();
				textField_1.setBounds(84, 60, 200, 21);
				panel.add(textField_1);
				textField_1.setColumns(10);
			}
			{
				int bookNo = dao.selectBookNo()+1;
				textField = new JTextField(bookNo+"");
				textField.setEditable(false);
				textField.setBounds(84, 32, 200, 21);
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				textField_2 = new JTextField();
				textField_2.setBounds(84, 85, 200, 21);
				panel.add(textField_2);
				textField_2.setColumns(10);
			}

			JComboBox comboBox = new JComboBox();

			comboBox.setModel(new DefaultComboBoxModel(new String[] { "", "\uC21C\uC815", "\uBB34\uD611",
					"\uCD94\uB9AC", "\uC561\uC158", "\uC131\uC778" }));
			comboBox.setBounds(84, 113, 200, 21);
			panel.add(comboBox);

			{
				textField_4 = new JTextField();
				textField_4.setBounds(84, 141, 200, 40);
				panel.add(textField_4);
				textField_4.setColumns(10);
			}
			{
				JButton btnNewButton = new JButton("µî·Ï");
				btnNewButton.setBounds(187, 191, 97, 23);

				btnNewButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Calendar calender = Calendar.getInstance();
						int bookNumber = Integer.parseInt(textField.getText());
						String title = textField_1.getText();
						String author = textField_2.getText();
						String publicationDate = calender.get(Calendar.YEAR)+"-"+(calender.get(Calendar.MONTH)+1)+"-"+calender.get(Calendar.DATE); 
						String type = comboBox.getSelectedItem().toString();
						String comment = textField_4.getText();

						BookDao dao = new BookDao();
						
						BookDTO book = new BookDTO(bookNumber, title, author,publicationDate, type, comment);
						int result = dao.insertBook(book);
						bookContents.selectTable();
						dispose();
					}
				});
				panel.add(btnNewButton);

				{
					JLabel lblNewLabel = new JLabel("\uB3C4\uC11C\uB4F1\uB85D");
					lblNewLabel.setBounds(30, 20, 60, 20);
					contentPanel.add(lblNewLabel);
				}
			}
		}
		setVisible(true);
	}
}
