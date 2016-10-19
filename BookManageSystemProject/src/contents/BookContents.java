package contents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.BookDao;
import dao.MemberDAO;
import dto.BookDTO;

public class BookContents extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private String[] columnNames = { "No", "도서번호", "도서명", "대여여부", "대여횟수", "저자", "등록일", "분야", "금액", "비고" };
	private JButton btnNewButton_3;
	private ArrayList<BookDTO> bookList = null;
	private BookDTO book = null;
	private DefaultTableModel model;
	private JScrollPane scrollPane = new JScrollPane();
	private BookContents bookContents;

	public BookContents() {
		bookContents = this;
		setLayout(null);
		setSize(900, 600);

		JLabel lblNewLabel = new JLabel("도서검색");
		lblNewLabel.setBounds(70, 60, 57, 15);
		add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(70, 80, 250, 25);
		add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("검색");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectTable();
			}
		});
		btnNewButton.setBounds(390, 80, 100, 25);
		add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("저자검색");
		lblNewLabel_1.setBounds(70, 130, 57, 15);
		add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(70, 155, 250, 25);
		add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton_1 = new JButton("검색");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				selectTable();
			}
		});
		btnNewButton_1.setBounds(390, 153, 100, 25);
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("도서등록");
		btnNewButton_2.setBounds(721, 78, 150, 25);
		add(btnNewButton_2);

		btnNewButton_3 = new JButton("도서수정/삭제");
		btnNewButton_3.setBounds(721, 153, 150, 25);
		add(btnNewButton_3);

		btnNewButton_2.addActionListener(new btn1Listener());
		btnNewButton_3.addActionListener(new btn1Listener());

		scrollPane.setBounds(70, 246, 801, 354);
		add(scrollPane);

		selectTable();
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}

	class btn1Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnNewButton_3) {
				int row = table.getSelectedRow();
				if (table.isRowSelected(row)) {
					int bookNumber = Integer.parseInt(model.getValueAt(row, 1).toString());
					String title = model.getValueAt(row, 2).toString();
					String author = model.getValueAt(row, 5).toString();
					String publicationDate = model.getValueAt(row, 6).toString();
					String type = model.getValueAt(row, 7).toString();
					int money = Integer.parseInt(model.getValueAt(row, 8).toString());
					String comment = model.getValueAt(row, 9).toString();
					new Book_Modification(bookContents,bookNumber,title,author,publicationDate,type,money,comment);
				}
			} else{
				new Book_Resiteration(bookContents);
				System.out.println("등록 다일러로그 출력");
			}

		}
	}

	

	public void selectTable() {

		int i = 1;

		model = new DefaultTableModel(columnNames, 0);
		table = new JTable();
		table.setModel(model);
		if(textField.getText().length()>0){
			String title = textField.getText();
			bookList = new BookDao().searchKeywordBook(title);
		} else if(  textField_1.getText().length()>0){
			String author = textField_1.getText();
			bookList = new BookDao().searchKeywordBook(author);
		} else {
			bookList = new BookDao().searchBook();
		}
		Iterator<BookDTO> e = bookList.iterator();

		while (e.hasNext()) {
			book = (BookDTO) e.next();
			model.addRow(new Object[] { i, book.getBookNumber(), book.getTitle(), book.getLending(),
					book.getLendingNumber(), book.getAuthor(), book.getPublicationDate(), book.getType(),
					book.getMoney(), book.getComment() });
			i++;

		}
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		table.getColumnModel().getColumn(1).setPreferredWidth(30);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		scrollPane.setViewportView(table);
	}

}
