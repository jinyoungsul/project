package contents;

import java.awt.*;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.JFormattedTextField.*;

import org.jdatepicker.impl.*;

public class SalesContents extends JPanel{
	private UtilDateModel startModel = new UtilDateModel();
	private UtilDateModel endModel = new UtilDateModel();
	private JDatePanelImpl startDatePanel; 
	private JDatePanelImpl endDatePanel;
	private JDatePickerImpl startDatePicker;
	private JDatePickerImpl endDatePicker;
	private Calendar calendar = Calendar.getInstance();
	
	private JLabel selectDateLabel;
	private JLabel selectDateSwingDashLabel;
	private JButton searchBtn;
	private JButton statisticsBtn;
	private JTable salesTable;
	private JScrollPane scrollPane;
	private String[] columnNames = {"NO","날짜","화원이름","회원번호","대여도서","총 수량","금액"};
	private JLabel memberLabel;
	private JLabel memberCountLabel;
	private JLabel bookLabel;
	private JLabel bookCountLabel;
	private JLabel moneyLabel;
	private JLabel moneyCountLabel;
	
	public SalesContents() {
		setLayout(null);
		
		Properties properties = new Properties();
		properties.put("text.today", "오늘날짜 ");
		//////////////////시작 날짜 선택
		startDatePanel = new JDatePanelImpl(startModel,properties);
		startModel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
		startModel.setSelected(true);
		startDatePicker = new JDatePickerImpl(startDatePanel,new DateLabelFormatter());
		startDatePicker.setBounds(110, 64, 202, 27);
		startDatePicker.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
		startDatePicker.setShowYearButtons(true);
		/////////////////끝 날짜 선택
		endDatePanel = new JDatePanelImpl(endModel,properties);
		endModel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
		endModel.setSelected(true);
		endDatePicker = new JDatePickerImpl(endDatePanel,new DateLabelFormatter());
		endDatePicker.setBounds(370, 64, 202, 27);
		endDatePicker.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
		endDatePicker.setShowYearButtons(true);
		
		add(startDatePicker);
		add(endDatePicker);
		
		/////////////////////////////Label 및 Button GUI
		selectDateLabel = new JLabel("\uB0A0\uC9DC\uC120\uD0DD");
		selectDateLabel.setBounds(25, 68, 62, 18);
		add(selectDateLabel);
		
		selectDateSwingDashLabel = new JLabel("~");
		selectDateSwingDashLabel.setBounds(335, 68, 62, 18);
		add(selectDateSwingDashLabel);
		
		searchBtn = new JButton("\uC870\uD68C");
		searchBtn.setBounds(721, 64, 105, 27);
		add(searchBtn);
		
		statisticsBtn = new JButton("\uD1B5\uACC4");
		statisticsBtn.setBounds(840, 64, 105, 27);
		add(statisticsBtn);
		
		////////////////////////////////테이블 GUI
		Object[][] data = {
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null},
				{null,null,null,null,null,null,null}};
		salesTable = new JTable(data,columnNames);
		scrollPane = new JScrollPane(salesTable);
		scrollPane.setBounds(25, 103, 920, 473);
		salesTable.setFillsViewportHeight(true);
		add(scrollPane);
		
		memberLabel = new JLabel("\uC774\uC6A9\uD68C\uC6D0 : ");
		memberLabel.setFont(new Font("굴림", Font.BOLD, 15));
		memberLabel.setBounds(284, 588, 80, 18);
		add(memberLabel);
		
		memberCountLabel = new JLabel("0 \uBA85");
		memberCountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		memberCountLabel.setFont(new Font("굴림", Font.BOLD, 15));
		memberCountLabel.setBounds(370, 588, 62, 18);
		add(memberCountLabel);
		
		bookLabel = new JLabel("\uB300\uC5EC\uAD8C\uC218 : ");
		bookLabel.setFont(new Font("굴림", Font.BOLD, 15));
		bookLabel.setBounds(486, 588, 80, 18);
		add(bookLabel);
		
		bookCountLabel = new JLabel("0 \uAD8C");
		bookCountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		bookCountLabel.setFont(new Font("굴림", Font.BOLD, 15));
		bookCountLabel.setBounds(572, 588, 62, 18);
		add(bookCountLabel);
		
		moneyLabel = new JLabel("\uB9E4\uCD9C\uAE08\uC561 : ");
		moneyLabel.setFont(new Font("굴림", Font.BOLD, 15));
		moneyLabel.setBounds(704, 588, 80, 18);
		add(moneyLabel);
		
		moneyCountLabel = new JLabel("0 \uC6D0");
		moneyCountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		moneyCountLabel.setFont(new Font("굴림", Font.BOLD, 15));
		moneyCountLabel.setBounds(790, 588, 62, 18);
		add(moneyCountLabel);
		
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
		
	}
	class DateLabelFormatter extends AbstractFormatter{

		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parse(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}
			return "";
		}
		
	}
}
