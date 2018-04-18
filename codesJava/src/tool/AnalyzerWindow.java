package tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AnalyzerWindow extends InfoProcessing{
	private JFrame frame;
	private String title;
	
	
	public AnalyzerWindow(String pdfText){
		super(pdfText);
		initialize();
		addPanels();
		
	}
	
	public void initialize() {
		frame = new JFrame();
		frame.setTitle("Analyzer");
		frame.setBounds(100,50,1000,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
	}
	public void addPanels() {
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(0, 102, 102));
		panel1.setBounds(10, 11, 964, 639);
		panel1.setLayout(null);
		JLabel lb1 = new JLabel("\t"+super.getTitle());
		lb1.setBounds(10,10,944,49);
		lb1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lb1.setForeground(new Color(255, 255, 255));
		panel1.add(lb1);
		
		JButton author = new JButton("Author Details");
		
		author.setForeground(new Color(0, 0, 0));
		author.setBounds(300,90,400,30);
		author.setBackground(new Color(220, 220, 220));
		author.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel1.add(author);
		
		
		JButton abs = new JButton("Abstract");
		abs.setForeground(new Color(0, 0, 0));
		abs.setBounds(300,140,400,30);
		abs.setBackground(new Color(220, 220, 220));
		abs.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel1.add(abs);
		
		JButton keyW = new JButton("Key Words");
		keyW.setForeground(new Color(0, 0, 0));
		keyW.setBounds(300,190,400,30);
		keyW.setBackground(new Color(220, 220, 220));
		keyW.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel1.add(keyW);
		frame.getContentPane().add(panel1);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(10, 11, 964, 639);
		JButton bt = new JButton("new");
		panel2.add(bt);
		panel2.setVisible(false);
		frame.getContentPane().add(panel2);
		
		author.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel1.setVisible(false);
				panel2.setVisible(true);
			}
		});
		frame.setVisible(true);
	}

}
