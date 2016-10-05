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
		g.drawString("판매페이지", 450, 400);
		System.out.println("커밋 테스트 - 설진영");
	}
}
