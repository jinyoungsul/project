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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import dao.BookDao;
import dto.BookDTO;

public class Book_Modification extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private BookDao dao = new BookDao();
	private JTextField textField_3;
	private JTextField textField_5;

	public Book_Modification(BookContents bookContents, int bookNumber, String title, String author,
			String publicationDate, String type, int money, String comment) {
		setSize(400, 400);
		setLocation((int) (screenSize.getWidth() / 2 - getWidth() / 2),
				(int) (screenSize.getHeight() / 2 - getHeight() / 2));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel.setBounds(26, 50, 346, 267);
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
				lblNewLabel_5.setBounds(44, 135, 57, 15);
				panel.add(lblNewLabel_5);
			}
			{
				JLabel lblNewLabel_6 = new JLabel("\uBE44\uACE0");
				lblNewLabel_6.setBounds(44, 185, 57, 15);
				panel.add(lblNewLabel_6);
			}
			{
				textField_1 = new JTextField(title);
				textField_1.setBounds(84, 60, 200, 21);
				panel.add(textField_1);
				textField_1.setColumns(10);
			}
			{
				textField = new JTextField(bookNumber + "");
				textField.setEditable(false);
				textField.setBounds(84, 32, 200, 21);
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				textField_2 = new JTextField(author);
				textField_2.setBounds(84, 85, 200, 21);
				panel.add(textField_2);
				textField_2.setColumns(10);
			}

			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "", "\uC21C\uC815", "\uBB34\uD611",
					"\uCD94\uB9AC", "\uC561\uC158", "\uC131\uC778" }));
			comboBox.setSelectedItem(type);
			comboBox.setBounds(84, 135, 200, 21);
			panel.add(comboBox);

			{
				textField_4 = new JTextField(comment);
				textField_4.setBounds(84, 185, 200, 40);
				panel.add(textField_4);
				textField_4.setColumns(10);
			}
			{
				JButton btnNewButton = new JButton("\uC218\uC815");
				btnNewButton.setBounds(83, 235, 97, 23);

				btnNewButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Calendar calender = Calendar.getInstance();
						int bookNumber = Integer.parseInt(textField.getText());
						String title = textField_1.getText();
						String author = textField_2.getText();
						String publicationDate = calender.get(Calendar.YEAR) + "-" + (calender.get(Calendar.MONTH) + 1)
								+ "-" + calender.get(Calendar.DATE);
						String type = comboBox.getSelectedItem().toString();
						int money = Integer.parseInt(textField_5.getText());
						String comment = textField_4.getText();
						BookDao dao = new BookDao();
						BookDTO book = new BookDTO(bookNumber, title, author, publicationDate, type, money, comment);
						int result = dao.updateBook(book);
						bookContents.selectTable();
						dispose();
					}
				});
				panel.add(btnNewButton);

				JLabel label = new JLabel("\uB4F1\uB85D\uC77C");
				label.setBounds(44, 110, 57, 15);
				panel.add(label);

				textField_3 = new JTextField(publicationDate);
				textField_3.setColumns(10);
				textField_3.setBounds(84, 110, 200, 21);
				panel.add(textField_3);
				{
					JLabel label_1 = new JLabel("\uAE08\uC561");
					label_1.setBounds(44, 160, 57, 15);
					panel.add(label_1);
				}
				{
					textField_5 = new JTextField(money + "");
					textField_5.setColumns(10);
					textField_5.setBounds(84, 160, 200, 21);
					panel.add(textField_5);
				}

				JButton button = new JButton("\uC0AD\uC81C");
				button.setBounds(187, 235, 97, 23);
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int bookNumber = Integer.parseInt(textField.getText());
						BookDao dao = new BookDao();
						Object[] options = { "삭제", "취소" };
						int value = JOptionPane.showOptionDialog(new JFrame(), "해당 도서를 \n정말 삭제하시겠습니까?", "삭제확인",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
						if(value==JOptionPane.YES_OPTION){
							int result = dao.deleteBook(bookNumber);
							bookContents.selectTable();
						}
						dispose();
					}
				});
				panel.add(button);

				{
					JLabel lblNewLabel = new JLabel("\uB3C4\uC11C\uC218\uC815");
					lblNewLabel.setBounds(30, 20, 60, 20);
					contentPanel.add(lblNewLabel);
				}
			}
		}
		setVisible(true);
	}
}
