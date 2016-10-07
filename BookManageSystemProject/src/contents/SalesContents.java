package contents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import main.Main;

public class SalesContents extends JPanel {
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
		salesTable.getSelectionModel().addListSelectionListener(new TableListSelectionListener());
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
	class TableListSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (e.getValueIsAdjusting()) return;
			new DetailSalesList();
			
		}
	}
	public class DetailSalesList extends JDialog {

		private final JPanel contentPanel = new JPanel();
		private JTextField textField;
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		private JTable table;
		private JTextField textField_4;
		private JTextField textField_5;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		public DetailSalesList() {
			setTitle("\uC0C1\uC138\uB0B4\uC5ED");
//			setBounds(452, 666);
			setSize(452,666);
			setLocation((int)screenSize.getWidth()/2-getWidth()/2, (int)screenSize.getHeight()/2-getHeight()/2);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("\uD68C\uC6D0\uC815\uBCF4");
				lblNewLabel.setBounds(12, 10, 57, 15);
				contentPanel.add(lblNewLabel);
			}
			{
				JPanel panel = new JPanel();
				panel.setBorder(new LineBorder(new Color(0, 0, 0)));
				panel.setBounds(12, 28, 412, 129);
				contentPanel.add(panel);
				panel.setLayout(null);
				{
					JLabel label = new JLabel("\uC774\uB984");
					label.setBounds(12, 10, 57, 15);
					panel.add(label);
				}
				{
					textField = new JTextField();
					textField.setBounds(62, 7, 57, 21);
					panel.add(textField);
					textField.setColumns(10);
				}
				{
					JLabel label = new JLabel("\uB098\uC774");
					label.setBounds(12, 35, 57, 15);
					panel.add(label);
				}
				{
					textField_1 = new JTextField();
					textField_1.setColumns(10);
					textField_1.setBounds(62, 32, 57, 21);
					panel.add(textField_1);
				}
				{
					JLabel label = new JLabel("\uC8FC\uC18C");
					label.setBounds(12, 60, 57, 15);
					panel.add(label);
				}
				{
					textField_2 = new JTextField();
					textField_2.setBounds(62, 57, 237, 21);
					panel.add(textField_2);
					textField_2.setColumns(10);
				}
				{
					JLabel label = new JLabel("\uC5F0\uB77D\uCC98");
					label.setBounds(12, 83, 57, 15);
					panel.add(label);
				}
				{
					textField_3 = new JTextField();
					textField_3.setColumns(10);
					textField_3.setBounds(62, 80, 237, 21);
					panel.add(textField_3);
				}
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(12, 180, 412, 361);
				contentPanel.add(scrollPane);
				{
					table = new JTable();
					table.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
							{null, null, null, null, null},
						},
						new String[] {
							"No", "날짜", "대여도서", "수량", "금액"
						}
					));
					scrollPane.setViewportView(table);
				}
			}
			{
				JLabel label = new JLabel("\uB300\uC5EC \uAD8C\uC218 : ");
				label.setBounds(103, 554, 76, 15);
				contentPanel.add(label);
			}
			{
				textField_4 = new JTextField();
				textField_4.setColumns(10);
				textField_4.setBounds(174, 551, 50, 21);
				contentPanel.add(textField_4);
			}
			{
				JLabel label = new JLabel("\uAD8C");
				label.setBounds(228, 554, 22, 15);
				contentPanel.add(label);
			}
			{
				JLabel label = new JLabel("\uB9E4\uCD9C \uAE08\uC561 : ");
				label.setBounds(258, 554, 76, 15);
				contentPanel.add(label);
			}
			{
				textField_5 = new JTextField();
				textField_5.setColumns(10);
				textField_5.setBounds(329, 551, 50, 21);
				contentPanel.add(textField_5);
			}
			{
				JLabel label = new JLabel("\uC6D0");
				label.setBounds(383, 554, 22, 15);
				contentPanel.add(label);
			}
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					okButton.setActionCommand("OK");
					okButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							dispose();
						}
					});
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
			}
			setVisible(true);
		}

	}
}
