package contents;

import java.awt.*;

import javax.swing.*;

public class SalesContents extends JPanel{
	public SalesContents() {
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawString("�Ǹ�������", 450, 400);
		System.out.println("Ŀ�� �׽�Ʈ - ������");
	}
}
