package contents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.table.*;

import dao.BookDao;
import dao.LendReturnDAO;
import dto.BookDTO;
import dto.LendReturnDTO;

public class LendReturnContents extends JPanel {
	public JTable table_1;
	public JTable table_2;
	private Font f1;
	private JTextField textField;
	public DefaultTableModel model21, model;
	private LendReturnContents lendReturnContents;

	public LendReturnContents() {
		lendReturnContents = this;
		MyLisner lisner = new MyLisner();
		f1 = new Font("bold", Font.BOLD, 13);
		setLayout(null);
		String[] columnNames2 = {};
		DefaultTableModel model2 = new DefaultTableModel(columnNames2, 0);
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(29, 221, 907, 334);
		add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		String[] columnNames = { "No", "대여번호", "도서이름", "저자", "대여일", "반납일", "연체여부", "대여료" };
		model = new DefaultTableModel(columnNames, 0);
		table_1.setModel(model);
		table_2 = new JTable();
		table_2.setBounds(29, 74, 269, 64);
		//////////////////////////////////////////////
		table_2.setModel(new DefaultTableModel(
				new Object[][] { { "회원번호", null }, { "이름", null }, { "휴대폰", null }, { "비고", null }, },
				new String[] { "", "" }));
		///////////////////
		add(table_2);

		JButton button = new JButton("반납");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (table_2.getValueAt(0, 1) != null) {
					int row = table_1.getSelectedRow();
					int memberNo = Integer.parseInt(table_2.getValueAt(0, 1).toString());
					int lendReturnNO = Integer.parseInt(model.getValueAt(row, 1).toString());
					LendReturnDAO dao = new LendReturnDAO();
					dao.updateLendReturn(lendReturnNO);
					model.setRowCount(0);
					int i = 1;
					ArrayList<Object[]> lendReturnList = dao.selectLendReturnList(memberNo);
					Iterator<Object[]> ir = lendReturnList.iterator();
					while (ir.hasNext()) {
						Object[] data = ir.next();
						int lendReturnNo = Integer.parseInt((String) data[0]);
						String bookName = (String) data[1];
						String author = (String) data[2];
						String receivelendDate = (String) data[3];
						String receivereturnDate = (String) data[4];
						String isOverdue = (String) data[5];
						int money = Integer.parseInt((String) data[6]);
						Object[] raw = { i, lendReturnNo, bookName, author, receivelendDate, receivereturnDate,
								isOverdue, money };
						model.addRow(raw);
						DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
						tableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
						for(int c=0;c<table_1.getColumnCount();c++)
							table_1.getColumnModel().getColumn(c).setCellRenderer(tableCellRenderer);

						i++;
					}
				}
			}
		});
		button.setBounds(800, 583, 130, 32);
		add(button);

		textField = new JTextField();
		textField.setBounds(750, 114, 116, 21);
		add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("등록");
		btnNewButton_1.setBounds(870, 114, 60, 21);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LendReturnDAO lenddao = new LendReturnDAO();
				if (table_2.getValueAt(0, 1) != null) {

					int bookCode = Integer.parseInt(textField.getText());
					BookDTO book = new BookDao().selectBook(bookCode);
					int memberNo = Integer.parseInt(table_2.getValueAt(0, 1).toString());
					Calendar calender = Calendar.getInstance();
					String lendDate = calender.get(Calendar.YEAR) + "-" + (calender.get(Calendar.MONTH) + 1) + "-"
							+ calender.get(Calendar.DATE);
					String returnDate = calender.get(Calendar.YEAR) + "-" + (calender.get(Calendar.MONTH) + 1) + "-"
							+ (calender.get(Calendar.DATE) + 3);
					LendReturnDTO lendReturn = new LendReturnDTO(memberNo, book.getBookNumber(), lendDate, returnDate,
							book.getMoney());
					book = new BookDao().selectIsLend(bookCode);
					if (book.getLending().equals("true")) {
						JOptionPane.showMessageDialog(new JFrame(),
							    book.getTitle()+"은 이미 대여중입니다.");
						

					} else {
						lenddao.insertLendReturn(lendReturn);
						model.setRowCount(0);
						int i = 1;
						ArrayList<Object[]> lendReturnList = lenddao.selectLendReturnList(memberNo);
						Iterator<Object[]> ir = lendReturnList.iterator();
						while (ir.hasNext()) {
							Object[] data = ir.next();
							int lendReturnNo = Integer.parseInt((String) data[0]);
							String bookName = (String) data[1];
							String author = (String) data[2];
							String receivelendDate = (String) data[3];
							String receivereturnDate = (String) data[4];
							String isOverdue = (String) data[5];
							int money = Integer.parseInt((String) data[6]);
							Object[] raw = { i, lendReturnNo, bookName, author, receivelendDate, receivereturnDate,
									isOverdue, money };
							model.addRow(raw);
							DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
							tableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
							for(int c=0;c<table_1.getColumnCount();c++)
								table_1.getColumnModel().getColumn(c).setCellRenderer(tableCellRenderer);

							i++;
						}
					}
				}
			}
		});
		add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("회원검색");
		btnNewButton_2.addActionListener(lisner);
		btnNewButton_2.setBounds(810, 64, 120, 21);
		add(btnNewButton_2);

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.BLACK);
		g.setFont(f1);
		g.drawString("회원정보", 30, 65);
		g.drawString("도서코드", 690, 130);
	}

	public class MyLisner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new search(lendReturnContents);

		}

	}

	public DefaultTableModel getModel() {
		return model;
	}
}
