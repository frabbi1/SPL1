
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.apache.pdfbox.contentstream.operator.text.NextLine;
import org.apache.pdfbox.text.PDFTextStripper;

public class MainClass{

	public static void main(String[] args) throws IOException{
		
		String fileName;
		Scanner inp = new Scanner(System.in);
		
		fileName = inp.nextLine();
		
		File f =  new File(fileName);
		ReadPdf obj1  = new ReadPdf(f) ; 
		String text = obj1.extractText();
		
		FileWriter fw = new FileWriter("output.txt");
		fw.write(text);
		fw.close();
		System.out.println(text);
		//obj1.countWord(text);
		
	}

}
