package main;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

import javax.swing.*;

import contents.*;

public class Main extends JFrame implements Runnable{
	private String[] dayArray = {null,"일요일","월요일","화요일","수요일","목요일","금요일","토요일"};
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JButton memberBtn = new JButton("회원관리");
	private JButton bookBtn = new JButton("도서관리");
	private JButton lendReturnBtn = new JButton("대여반납 관리");
	private JButton salesBtn = new JButton("매출관리");
	
	private JPanel btnPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	
	private JLabel dateLabel = new JLabel();
	
//	private BookContents bookContentsPanel = new BookContents();
//	private LendReturnContents lendReturnContentsPanel = new LendReturnContents();
//	private MemberContents memberContentsPanel = new MemberContents();
//	private SalesContents salesContentsPanel = new SalesContents();
	
	private Thread thread = new Thread(this);
	public Main(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("도서대여관리 System");
		setSize(989,800);
		setResizable(false);
		setLocation((int)screenSize.getWidth()/2-getWidth()/2, (int)screenSize.getHeight()/2-getHeight()/2);
		getContentPane().setLayout(null);
		
		
		btnPanel.setBounds(0, 0, 984, 86);
		getContentPane().add(btnPanel);
		btnPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		memberBtn.addActionListener(new MainBtnListener());
		bookBtn.addActionListener(new MainBtnListener());
		lendReturnBtn.addActionListener(new MainBtnListener());
		salesBtn.addActionListener(new MainBtnListener());
		
		btnPanel.add(memberBtn);
		btnPanel.add(bookBtn);
		btnPanel.add(lendReturnBtn);
		btnPanel.add(salesBtn);
		
		centerPanel.setBounds(0, 84, 984, 639);
		centerPanel.setLayout(new BorderLayout());
		getContentPane().add(centerPanel);
		bottomPanel.setBounds(0, 723, 984, 42);
		
		getContentPane().add(bottomPanel);
		bottomPanel.add(dateLabel);
		thread.start();
		setVisible(true);
		
	}
	
	class MainBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			centerPanel.removeAll();
			if(e.getSource()==memberBtn){
				centerPanel.add(new MemberContents());
				centerPanel.validate();
				centerPanel.revalidate();
				centerPanel.repaint();
				System.out.println("멤버버튼");
			} else if(e.getSource()==bookBtn){
				centerPanel.add(new BookContents());
				centerPanel.validate();
				centerPanel.revalidate();
				centerPanel.repaint();
				System.out.println("북버튼");
			} else if(e.getSource()==lendReturnBtn){
				centerPanel.add(new LendReturnContents());
				centerPanel.validate();
				centerPanel.revalidate();
				centerPanel.repaint();
				System.out.println("대여버튼");
			} else if(e.getSource()==salesBtn){
				centerPanel.add(new SalesContents());
				centerPanel.validate();
				centerPanel.revalidate();
				centerPanel.repaint();
				System.out.println("판매버튼");
			}
		}
		 
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int year;
		int month;
		int date;
		int hour;
		int minute;
		int second;
		String day;
		
		while(true){
			try {
				Calendar calendar = Calendar.getInstance();
				year = calendar.get(Calendar.YEAR);
				month = calendar.get(Calendar.MONTH)+1;
				date = calendar.get(Calendar.DATE);
				day = dayArray[calendar.get(Calendar.DAY_OF_WEEK)];
				hour = calendar.get(Calendar.HOUR_OF_DAY);
				minute = calendar.get(Calendar.MINUTE);
				second = calendar.get(Calendar.SECOND);
				
				dateLabel.setText(year+"년 "+month+"월 "+date+"일 "+day+" "+hour+"시 "+minute+"분 "+second+"초");
				bottomPanel.revalidate();
				bottomPanel.repaint();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
