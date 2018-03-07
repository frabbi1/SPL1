package tool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class InfoProcessing {
	
	private String text = "";
	public InfoProcessing(String pdfText) {
		this.text = pdfText;
		tokenize();
		getTitleAndAuthors();
		//countRef();
	}
	
	public void tokenize() {	
		
		Map< String, Integer > wm = new HashMap<String, Integer>();
		String line = text.toLowerCase();
		String[] token = line.split("[^A-Za-z0-9]");
		
		for( String s: token) {
			if(!is_stopWord(s)) {
				if(!wm.containsKey(s)) {
					wm.put(s, 1);
				}
				else {
					wm.put(s, wm.get(s)+1);
				}
			}
			
		}
		
		for( Map.Entry<String, Integer> m: wm.entrySet()) {
			if(m.getKey().equals("")) {
				continue;
			}
			System.out.println(m.getKey() + ": " + m.getValue());
		}
		
	}
	
	public boolean is_stopWord(String word) {
		
		try {
			FileReader fr  = new FileReader("stop_words.txt");
			BufferedReader br  =  new BufferedReader(fr);
			
			String temp = br.readLine();
			while(true) {
				if(temp==null) {
					break;
				}
				if(word.equals(temp)) {
					return true;
				}
				temp = br.readLine();
			}
			br.close();
			fr.close();
		} catch (Exception e) {
			System.out.println("Error in Stop Word file");
			return true;
		}
		return false;
	}
	
	public void getTitleAndAuthors() {
		String title = "";
		for(int i=0; true; i++) {
			if(text.charAt(i)=='\n') {
				break;
			}
			title = title + String.valueOf(text.charAt(i));
			
		}
		
		System.out.println(title);
	}
	
	/*public void countRef() {
		String line = text.toLowerCase();
		String[] words = line.split(" ");
		boolean flag = false;
		for (String x : words) {
			int l =x.length();
			if(flag==true) {
				if(x.charAt(0)=='[' && (x.charAt(l-1)==']' || x.charAt(l-2)==']')) {
					
				}
			}
			if(x.equals("references")) {
				flag = true;
			}
		}
	}*/
	
}
