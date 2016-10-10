package contents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

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