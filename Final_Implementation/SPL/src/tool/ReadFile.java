package tool;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JOptionPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ReadFile {
	
	private File file = null;
	private String text = "";
	
	public ReadFile(File file) {
		this.file = file;
		this.text = extractText();
	}
	
	public String extractText() {
		try {
			
			PDDocument doc = PDDocument.load(file);
			PDFTextStripper textStrip = new PDFTextStripper();
			String output = textStrip.getText(doc);
			doc.close();
			//textStrip.setStartPage(2);
		    return output;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error Opening pdf File");
			return null;
		}
	}
	public String getPdfText() {
		return this.text;
	}
	
}
