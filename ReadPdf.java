

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
	
	public void countWord(String text) {
		
		
		Map< String, Integer > wm = new HashMap<String, Integer>();
		
			
		String line = text.toLowerCase();
		String[] token = line.split("[^a-z0-9]");
		
		int totalWord = 0;
			
		for( String s: token) {
			if(!wm.containsKey(s)) {
				wm.put(s, 1);
			}
			else {
				wm.put(s, wm.get(s)+1);
			}
			totalWord++;
		}
		
		
		System.out.println("TOTAL: " + totalWord);
		System.out.println();
		
		int highestCount = -1;
		String highestString = "";
		
		
		int lowestCount = totalWord+1;
		String lowestString = "";
		
		
		for( Map.Entry<String, Integer> m: wm.entrySet()) {
			if(m.getKey().equals("")) {
				continue;
			}
			if(highestCount < m.getValue()) {
				highestCount = m.getValue();
				highestString = m.getKey();
			}
			
			if(lowestCount > m.getValue()) {
				lowestCount  = m.getValue();
				lowestString = m.getKey();
			}
			
			System.out.println(m.getKey() + ": " + m.getValue());
		}
		
		System.out.println("\n" + "HIGHEST:");
		System.out.println("WORD: "+ highestString + "\n"+ "COUNT: " + highestCount);
		System.out.println();
		System.out.println("LOWEST:");
		System.out.println("WORD: "+ lowestString + "\n"+ "COUNT: " + lowestCount);
		
	}
}
