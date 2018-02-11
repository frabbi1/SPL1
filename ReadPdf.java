

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ReadPdf {
	
	File file=null;
	
	public ReadPdf(File file) {
		this.file = file;
	}
	
	public String extractText() throws IOException{
		PDDocument doc = PDDocument.load(file);
		PDFTextStripper textStrip = new PDFTextStripper();
		//textStrip.setStartPage(1);
		//textStrip.setEndPage(1);
	    return textStrip.getText(doc);
	   // return textStrip.getPageStart();
	}
	
	
}
