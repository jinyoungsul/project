package main;

import java.awt.Dimension;
import java.awt.RenderingHints.Key;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JDialog{
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JLabel shopLabel = new JLabel("매장번호");
	private JLabel passwordLabel = new JLabel("비밀번호");
	private JTextField shopTextField=new JTextField("우리매장");
	private JPasswordField passwordTextField=new JPasswordField();
	private JButton loginBtn = new JButton("Login");
	public Login() {
		setSize(300,200);
		setTitle("Login");
		getContentPane().setLayout(null);
		
		setLocation((int)screenSize.getWidth()/2-getWidth()/2, (int)screenSize.getHeight()/2-getHeight()/2);
		shopLabel.setBounds(55, 49, 70, 26);
		passwordLabel.setBounds(55, 73, 70, 26);
		getContentPane().add(shopLabel);
		getContentPane().add(passwordLabel);
		shopTextField.setHorizontalAlignment(SwingConstants.CENTER);
		shopTextField.setEditable(false);
		
		shopTextField.setBounds(112, 52, 116, 20);
		getContentPane().add(shopTextField);
		shopTextField.setColumns(10);
		shopTextField.disable();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(112, 76, 116, 20);
		passwordTextField.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add( passwordTextField);
		loginBtn.setBounds(99, 109, 70, 20);
		loginBtn.addActionListener(new LoginBtnListener());
		passwordTextField.addKeyListener(new LoginBtnListener());
		getContentPane().add(loginBtn);
		setVisible(true);
	}
	class LoginBtnListener implements ActionListener,KeyListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(passwordTextField.getText().equals("1234")){
				
				dispose();
				new Main();
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(passwordTextField.getText().equals("1234")){
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ENTER:
					dispose();
					new Main();
					break;

				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	public static void main(String[] args) {
		 new Login();
		
		
	}
}
