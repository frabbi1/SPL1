package guiTest;

import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class IconAddition extends JFrame{
	
	private JLabel lb1, lb2;
	
	public IconAddition() {
		super("Icon");
		setLayout(new FlowLayout());
		setSize(960,960);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void showWindow() {
		lb1 = new JLabel("Lab1");
		add(lb1);
		
		Icon ic = new ImageIcon(getClass().getResource("bug1.png"));

		lb2 = new JLabel();
		lb2.setHorizontalAlignment(SwingConstants.LEFT);
		lb2.setIcon(ic);
		lb2.setText("icic");
		lb2.setToolTipText("Snake Icon");
		
		add (lb2);
		
	}
}
