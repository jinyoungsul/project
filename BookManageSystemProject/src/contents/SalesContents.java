package contents;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;

import dao.SalesDAO;
import dto.SalesDTO;

public class SalesContents extends JPanel {
	private UtilDateModel startModel = new UtilDateModel();
	private UtilDateModel endModel = new UtilDateModel();
	private JDatePanelImpl startDatePanel;
	private JDatePanelImpl endDatePanel;
	private JDatePickerImpl startDatePicker;
	private JDatePickerImpl endDatePicker;
	private Calendar calendar = Calendar.getInstance();
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	private JLabel selectDateLabel;
	private JLabel selectDateSwingDashLabel;
	private JButton searchBtn;
	private JButton statisticsBtn;
	private JTable salesTable;
	private JScrollPane scrollPane;
	private String[] columnNames = { "NO", "날짜", "화원이름", "회원번호", "대여도서", "금액" };
	private JLabel memberLabel;
	private JLabel memberCountLabel;
	private JLabel bookLabel;
	private JLabel bookCountLabel;
	private JLabel moneyLabel;
	private JLabel moneyCountLabel;

	private DefaultTableModel model;
	private JTable table;
	
	private String startDate;
	private String endDate;
	
	private SalesDAO dao = new SalesDAO();
	
	public SalesContents() {
		setLayout(null);

		Properties properties = new Properties();
		properties.put("text.today", "오늘날짜 ");
		////////////////// 시작 날짜 선택
		startDatePanel = new JDatePanelImpl(startModel, properties);
		startModel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
		startModel.setSelected(true);
		startDatePicker = new JDatePickerImpl(startDatePanel, new DateLabelFormatter());
		startDatePicker.setBounds(110, 64, 202, 27);
		startDatePicker.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
		startDatePicker.setShowYearButtons(true);
		
		///////////////// 끝 날짜 선택
		endDatePanel = new JDatePanelImpl(endModel, properties);
		endModel.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
		endModel.setSelected(true);
		endDatePicker = new JDatePickerImpl(endDatePanel, new DateLabelFormatter());
		endDatePicker.setBounds(370, 64, 202, 27);
		endDatePicker.getJFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
		endDatePicker.setShowYearButtons(true);
		
		//날짜 조회 시작 날짜 끝날짜 
		startDate = ""+startModel.getYear()+"-"+(startModel.getMonth()+1)+"-"+startModel.getDay();
		endDate = ""+endModel.getYear()+"-"+(endModel.getMonth()+1)+"-"+endModel.getDay();
		
		add(startDatePicker);
		add(endDatePicker);

		///////////////////////////// Label 및 Button GUI
		selectDateLabel = new JLabel("\uB0A0\uC9DC\uC120\uD0DD");
		selectDateLabel.setBounds(25, 68, 62, 18);
		add(selectDateLabel);

		selectDateSwingDashLabel = new JLabel("~");
		selectDateSwingDashLabel.setBounds(335, 68, 62, 18);
		add(selectDateSwingDashLabel);

		searchBtn = new JButton("\uC870\uD68C");
		searchBtn.setBounds(721, 64, 105, 27);
		searchBtn.addActionListener(new MyBtnListener());
		add(searchBtn);

		statisticsBtn = new JButton("\uD1B5\uACC4");
		statisticsBtn.setBounds(840, 64, 105, 27);
		statisticsBtn.addActionListener(new MyBtnListener());
		add(statisticsBtn);

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
		model = new DefaultTableModel(columnNames, 0);
		printTable(startDate,endDate);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

	}

	class DateLabelFormatter extends AbstractFormatter {

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
	/* 상세내용 보기 보류
	class TableListSelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting())
				return;
			new DetailSalesList();
		}
	}
	*/
	class MyBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == statisticsBtn) {
				PolyLineBarSalesChart polyChart = new PolyLineBarSalesChart();
				startDate = ""+startModel.getYear()+"-"+(startModel.getMonth()+1)+"-"+startModel.getDay();
				endDate = ""+endModel.getYear()+"-"+(endModel.getMonth()+1)+"-"+endModel.getDay();
				JFreeChart chart = polyChart.getChart(dao.selectSumMoney(startDate, endDate));
				ChartFrame frame1 = new ChartFrame("하루매출", chart);
				frame1.setSize(800, 800);
				frame1.setLocation((int) (screenSize.getWidth() / 2 - frame1.getWidth() / 2),
						(int) (screenSize.getHeight() / 2 - frame1.getHeight() / 2));
				frame1.setVisible(true);

			}
			if(e.getSource()==searchBtn){
				startDate = ""+startModel.getYear()+"-"+(startModel.getMonth()+1)+"-"+startModel.getDay();
				endDate = ""+endModel.getYear()+"-"+(endModel.getMonth()+1)+"-"+endModel.getDay();
				model.setRowCount(0);
				System.out.println(startDate+" "+endDate);
				printTable(startDate,endDate);
			}
		}

	}

	public void printTable(String startDate,String endDate) {
		// 테이블
		
		ArrayList<SalesDTO> salesDTO = dao.selectSalesTable(startDate,endDate);
		
		int num = 1;
		int sum = 0;
		int memberCount = dao.selectMemberCount(startDate, endDate);
		for(SalesDTO i : salesDTO){
			Object[] row = {num,i.getSalesDate(),i.getName(),i.getMemberNo(),i.getBookName(),i.getMoney()};
			model.addRow(row);
			num++;
			sum += i.getMoney(); 
		}
		salesTable = new JTable(model);
		scrollPane = new JScrollPane(salesTable);
		scrollPane.setBounds(25, 103, 920, 473);
		salesTable.setFillsViewportHeight(true);
		moneyCountLabel.setText(""+sum);
		bookCountLabel.setText(""+(num-1));
		memberCountLabel.setText(""+memberCount);
		DefaultTableCellRenderer tableCellRenderer = new DefaultTableCellRenderer();
		tableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for(int c=0;c<salesTable.getColumnCount();c++)
			salesTable.getColumnModel().getColumn(c).setCellRenderer(tableCellRenderer);

//		salesTable.getSelectionModel().addListSelectionListener(new TableListSelectionListener());		상세내용 보기
		add(scrollPane);
	}
}
