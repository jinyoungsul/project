package contents;

import java.awt.*;

import javax.swing.*;

public class MemberContents extends JPanel{
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString("ȸ��������", 450, 400);
		System.out.println("ȸ�������� ����");
	}
}
