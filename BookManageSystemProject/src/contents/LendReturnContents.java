package contents;

import java.awt.*;

import javax.swing.*;

public class LendReturnContents extends JPanel{
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawString("대여반납 페이지", 450, 400);
	}
}
