package contents;

import java.awt.*;

import javax.swing.*;

public class BookContents extends JPanel{
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawString("도서페이지", 450, 400);
	}
}
