package tool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graph extends JPanel{
	private int[] array;
	private int item;
	StringClass str = new StringClass();
	
	public Graph(JFrame frame, int[] array, int item) {
		this.array = array;
		this.item=item;
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		g.setColor(new Color(0, 102, 102));
		g.fillRect(1, 1, 1298, 698);
		Graphics2D g2D=(Graphics2D) g;
		
		
		
		//System.out.println(item);
		double angle = (3.1416/item)*2;
		double a = angle;
		for(int i=0; i<item; i++) {
			int y = (int)(300*Math.sin(a));
			int x = (int)(300*Math.cos(a));
			//System.out.println(x +" " +y);
			BasicStroke bs = new BasicStroke(3+array[i]);
			g2D.setStroke(bs);
			g.setColor(Color.BLUE);
			g2D.drawLine(700, 350, 700+x, 350-y);
			//g2D.drawRect(700, 350, , height);
			g.setColor(Color.lightGray);
			g.fillOval(700+x-30, 350-y-30, 60,60);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Tahoma", Font.BOLD, 10));
			g.drawString("ref "+str.intToString(i+1), 700+x-10, 350-y);
			g.setColor(Color.RED);
			g.setFont(new Font("Tahoma", Font.BOLD, 12));
			g.drawString(str.intToString(array[i]),700+x-30, 350-y-30);
			a = angle+a;
		}
		
		g.setColor(Color.BLACK);
		g.fillOval(1300/2, 300, 100, 100);
		g.setColor(Color.WHITE);
		g.drawString("paper", 688, 345);
		
	}
	
	
}
