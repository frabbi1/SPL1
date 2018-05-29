package tool;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionEvent;

public class FilePath {

	private JFrame frmFilePathInput;
	private JTextField textField;
	private String pdfText = "";

	public void showWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FilePath window = new FilePath();
					window.frmFilePathInput.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FilePath() {
		initialize();
	}

	private void initialize() {
		
		frmFilePathInput = new JFrame();
		frmFilePathInput.getContentPane().setBackground(new Color(51, 102, 153));
		frmFilePathInput.getContentPane().setForeground(Color.WHITE);
		frmFilePathInput.setTitle("File Path Input");
		frmFilePathInput.setBounds(100, 100, 729, 398);
		frmFilePathInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFilePathInput.getContentPane().setLayout(null);
		frmFilePathInput.setResizable(false);
		
		JLabel lblEnterYourFile = new JLabel("ENTER YOUR FILE PATH:");
		lblEnterYourFile.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterYourFile.setBounds(227, 30, 283, 58);
		frmFilePathInput.getContentPane().add(lblEnterYourFile);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setBounds(72, 99, 587, 79);
		frmFilePathInput.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("PROCESS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String path = getPath();
				File inputFile = new File(path);
				
				ReadFile target = new ReadFile(inputFile);
				pdfText = target.getPdfText();
				frmFilePathInput.setVisible(false);
				
				
				//System.out.println(pdfText);\
				try {
					FileWriter fw = new FileWriter("output.txt");
					fw.write(pdfText);
					fw.close();
				} catch (Exception e) {
					// TODO: handle exception
					System.err.println("Error in writting file");;
				}
				//InfoProcessing info = new InfoProcessing(pdfText);
				if(pdfText != null) {
					OutputWindow aw = new OutputWindow(pdfText);
				}
				
							
			}
		});
		
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewButton.setBounds(222, 204, 229, 64);
		frmFilePathInput.getContentPane().add(btnNewButton);
	}
	
	public String getPath() {
		return textField.getText();
	}
}
