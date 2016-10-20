package contents;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import dao.*;
import dto.*;

public class search extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel nameLable;
	private JLabel phoneLable;
	private JLabel memberNoLable;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<MemberDTO> memberList = null;
	private Font f1;
	private LendReturnContents lendReutrnContents;
	public search(LendReturnContents lendReutrnContents) {
		this.lendReutrnContents = lendReutrnContents;
		MyLisner listener = new MyLisner();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		f1 = new Font("bold", Font.BOLD, 15);
		setVisible(true);
		setSize(600, 450);
		setLocation((int)screenSize.getWidth()/2-getWidth()/2, (int)screenSize.getHeight()/2-getHeight()/2);
		getContentPane().setLayout(null);

		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 584, 562);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(291, 191, -125, -184);
		panel.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(42, 161, 498, 186);
		panel.add(scrollPane_1);

		table = new JTable();
		String[] columnNames = {"회원번호", "이름", "전화번호","비고"};
		model = new DefaultTableModel(columnNames, 0 );
		table.setModel(model);
		table.getSelectionModel().addListSelectionListener(new TableListSelectionListener());
		scrollPane_1.setViewportView(table);

		JButton btnNewButton = new JButton("검색");
		btnNewButton.setBounds(387, 91, 153, 30);
		btnNewButton.addActionListener(listener);
		panel.add(btnNewButton);
		
		nameLable = new JLabel("이름");
		nameLable.setBounds(42, 79, 68, 55);
		panel.add(nameLable);
		
		phoneLable = new JLabel("전화번호");
		phoneLable.setBounds(42, 31, 100, 38);
		panel.add(phoneLable);
		
		memberNoLable = new JLabel("회원번호");
		memberNoLable.setBounds(291, 0, 100, 100);
		panel.add(memberNoLable);

		
		
		textField = new JTextField();
		textField.setBounds(125, 36, 134, 30);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(387, 36, 153, 30);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(125, 92, 134, 30);
		panel.add(textField_2);
	}

	public void paint(Graphics g) {
		super.paint(g);
	}

	public class MyLisner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(textField_1.getText().length()>0){
				int memberNo = Integer.parseInt(textField_1.getText());
				memberList = new MemberDAO().searchKeywordMember(memberNo);
			} else if(  textField_2.getText().length()>0 || textField.getText().length()>0){
				String name = textField_2.getText();
				String phoneNumber = textField.getText();
				memberList = new MemberDAO().searchKeywordMember(name,phoneNumber);
			}
		else {
				memberList = new MemberDAO().searchMember();
			}
			model.setRowCount(0);
			for(int i = 0 ; i < memberList.size();i++){
				
				model.addRow(new Object[]{memberList.get(i).getMemberNo(),memberList.get(i).getName(),memberList.get(i).getPhoneNumber(),memberList.get(i).getComment()});
			}
			DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
			tableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for(int c=0;c<table.getColumnCount();c++)
				table.getColumnModel().getColumn(c).setCellRenderer(tableCellRenderer);

			table.validate();
			table.revalidate();
			table.repaint();
		}
	}
	class TableListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) return;
			System.out.println("눌렸다!");
			int row = table.getSelectedRow();
			String num =  model.getValueAt(row , 0).toString();
			String who =  model.getValueAt(row , 1).toString();
			String ph =  model.getValueAt(row , 2).toString();
			String adress =  model.getValueAt(row , 3).toString();
			
			lendReutrnContents.table_2.setValueAt(num, 0, 1);
			lendReutrnContents.table_2.setValueAt(who, 1, 1);
			lendReutrnContents.table_2.setValueAt(ph, 2, 1);
			lendReutrnContents.table_2.setValueAt(adress, 3, 1);
			lendReutrnContents.getModel().setRowCount(0);
			int memberNo = Integer.parseInt(num);
			int i = 1;
			LendReturnDAO dao = new LendReturnDAO();
			ArrayList<Object[]> lendReturnList = dao.selectLendReturnList(memberNo);
			Iterator<Object[]> ir = lendReturnList.iterator();
			while(ir.hasNext()){
				Object[] data = ir.next();
				int lendReturnNo = Integer.parseInt((String)data[0]);
				String bookName = (String)data[1];
				String author = (String)data[2];
				String lendDate = (String)data[3];
				String returnDate = (String)data[4];
				String isOverdue = (String)data[5];
				int money = Integer.parseInt((String)data[6]);
				Object[] raw = {i,lendReturnNo,bookName,author,lendDate,returnDate,isOverdue,money};
				lendReutrnContents.getModel().addRow(raw);
				i++;
			}
			DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
			tableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			for(int c=0;c<lendReutrnContents.table_1.getColumnCount();c++)
				lendReutrnContents.table_1.getColumnModel().getColumn(c).setCellRenderer(tableCellRenderer);

			dispose();
			
		}
	}
	public void selectTable() {

		
		

	}
}



