package contents;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.LendReturnDAO;
import dao.MemberDAO;
import dto.MemberDTO;

public class search extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
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

		textField = new JTextField();
		textField.setBounds(115, 36, 134, 30);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(387, 36, 153, 30);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(115, 91, 134, 30);
		panel.add(textField_2);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.setFont(f1);
		g.drawString("전화번호", 55, 85);
		g.drawString("회원번호", 327, 85);
		g.drawString("이름", 55, 141);
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
			
			dispose();
			
		}
	}
	public void selectTable() {

		
		

	}
}



