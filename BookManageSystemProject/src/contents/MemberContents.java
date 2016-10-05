package contents;

import java.awt.*;

import javax.swing.*;

public class MemberContents extends JPanel{
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.drawString("회원페이지", 450, 400);
		System.out.println("회원페이지 수정");
	}
}
