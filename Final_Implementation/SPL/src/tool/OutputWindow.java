package tool;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OutputWindow extends InfoProcessing{
	private JFrame mainFrame;
	private String title;
	
	
	public OutputWindow(String pdfText){
		super(pdfText);
		initialize();
		addPanels();
		
	}
	
	public void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Analyzer");
		mainFrame.setBounds(100,50,693,500);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		
	}
	public void addPanels() {
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(0, 102, 102));
		panel1.setBounds(0, 0, 681, 490);
		panel1.setLayout(null);
		
		JButton author = new JButton("Author Details");
		
		author.setForeground(new Color(0, 0, 0));
		author.setBounds(142,106,400,30);
		author.setBackground(new Color(220, 220, 220));
		author.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel1.add(author);
		
		
		JButton abs = new JButton("Abstract");
		abs.setForeground(new Color(0, 0, 0));
		abs.setBounds(142,147,400,30);
		abs.setBackground(new Color(220, 220, 220));
		abs.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel1.add(abs);
		
		JButton keyW = new JButton("Key Words");
		keyW.setForeground(new Color(0, 0, 0));
		keyW.setBounds(142,188,400,30);
		keyW.setBackground(new Color(220, 220, 220));
		keyW.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel1.add(keyW);
		
		
		JButton ref = new JButton("REFERENCE");
		ref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frm = new JFrame("Reference");
				frm.setBounds(20,20,1300,700);
				
				Graph graph = new Graph(frm, countReference(),refNum);
				frm.getContentPane().add(graph);
				frm.setVisible(true);
				
			}
		});
		ref.setFont(new Font("Tahoma", Font.BOLD, 14));
		ref.setBounds(142, 229, 400, 34);
		panel1.add(ref);
		
		JButton btnTitle = new JButton("TITLE");
		btnTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frameTitle = new JFrame();
				
				JPanel pan = new JPanel();
				frameTitle.setTitle("Title of the Paper");
				frameTitle.setBounds(100,50,700,200);
				frameTitle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pan.setBounds(0,0,693,200);
				pan.setBackground(new Color(0, 102, 102));
				pan.setLayout(null);
				
				ArrayList<String> titleString;
				titleString = getTitle();
				int gap = 25;
				for (String entity : titleString) {
					JLabel lbl = new JLabel(entity);
					lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
					lbl.setBounds(6, gap, 680, 20);
					gap+=25;
					lbl.setForeground(Color.WHITE);
					pan.add(lbl);
				}
				JButton back = new JButton("BACK");
				back.setBounds(300,100,80,50);
				pan.add(back);
				mainFrame.setVisible(false);
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frameTitle.setVisible(false);
						mainFrame.setVisible(true);
					}
				});
				frameTitle.getContentPane().add(pan);
				frameTitle.setVisible(true);
				
				
			}
		});
		btnTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTitle.setBounds(142, 61, 400, 34);
		panel1.add(btnTitle);
		
		author.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame frameAuthor = new JFrame();
				
				JPanel pan = new JPanel();
				frameAuthor.setTitle("Authors");
				frameAuthor.setBounds(100,50,700,600);
				frameAuthor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pan.setBounds(0,0,693,600);
				pan.setBackground(new Color(0, 102, 102));
				pan.setLayout(null);
				
				ArrayList<String> author;
				author = getAuthors();
				int gap = 25;
				for (String entity : author) {
					JLabel lbl = new JLabel(entity);
					lbl.setFont(new Font("Tahoma", Font.BOLD, 16));
					lbl.setBounds(6, gap, 680, 20);
					gap+=25;
					lbl.setForeground(Color.WHITE);
					pan.add(lbl);
				}
				JButton back = new JButton("BACK");
				back.setBounds(300,500,80,50);
				pan.add(back);
				mainFrame.setVisible(false);
				back.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						frameAuthor.setVisible(false);
						mainFrame.setVisible(true);
					}
				});
				frameAuthor.getContentPane().add(pan);
				frameAuthor.setVisible(true);
				
			}
		});
		mainFrame.getContentPane().add(panel1);
		
		JButton exit = new JButton("EXIT");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.setVisible(false);
			}
		});
		exit.setFont(new Font("Tahoma", Font.BOLD, 14));
		exit.setBounds(226, 291, 207, 52);
		panel1.add(exit);
		mainFrame.setVisible(true);
	}
}
