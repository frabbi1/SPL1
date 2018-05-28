package tool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoProcessing {
	
	private String text = "";
	ArrayList<String> lines = new ArrayList<String>();
	private ArrayList<String> title = new ArrayList<String>();
	private ArrayList<String> authorDetails = new ArrayList<String>();
	public int refNum =0;
	private int titleLineNo = 0;
	
	
	ArrayList<SuffixTrie> roots = new ArrayList<SuffixTrie>();
	StringClass str = new StringClass();
	
	public InfoProcessing(String pdfText) {
		this.text = pdfText;
		tokenize();
		makeArrayList();
		ProcessTitle();
		processAuthors();
		//getFirstAuthor();
		countReference();
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
			if(m.getKey().equals("\n")) {
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
	
	public void makeArrayList(){
		String line = "";
		
		try {
			FileReader fr = new FileReader("output1.txt");
			BufferedReader bReader = new BufferedReader(fr);
			line = bReader.readLine();
			while(line != null) {
				lines.add(line);
				
				line = bReader.readLine();
			}
			fr.close();
			bReader.close();
			
				
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error opening output text");
		}
		
	}
	public void ProcessTitle() {
		boolean flag = true;
		for(int i=0; i<lines.size(); i++) {
			SuffixTrie treeRoot = createSuffixTree(str.toLower(lines.get(i)));
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader("NameList.txt"));
				String name = br.readLine();
				while(name!=null) {
					if(isPatternExist(name, treeRoot)) {
						titleLineNo = i;
						flag = false;
						break;
					}
					name = br.readLine();
				}
				if(flag==true) title.add(lines.get(i));
				br.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error Opening file : NameList.txt");
			}
			if(flag == false) break;	
			
		}
		//System.out.println(title);	
	
		
	}
	public ArrayList<String> getTitle() {
		return title;
	}
	
	
	public void processAuthors() {
		
		int lineNo = 0;
		for(int x=0; x<lines.size();x++) {
			SuffixTrie root = createSuffixTree(str.toLower(lines.get(x)));
			if(lineNo < titleLineNo) {
				lineNo++;
				continue;
			}
			if(isPatternExist("abstract", root)) {
				break;
			}
			else {
				authorDetails.add(lines.get(lineNo));
			}
			lineNo++;
		}
		
		System.out.println(authorDetails);
	}
	
	public ArrayList<String> getAuthors() {
		return authorDetails;
	}
	
	public int[] countReference() {
		
		int size = lines.size();

		String num = "";
		refNum = 0;
		for (int n = size-1; n>=0; n--) {
			if(str.toLower(lines.get(n)).equals("references") || str.toLower(lines.get(n)).equals("reference")) {
				break;
			}
			if(lines.get(n).equals("")) continue;
			String[] word = lines.get(n).split(" ");
			String firstWord = word[0];
			if(firstWord.charAt(0) == '[' && firstWord.charAt(str.getLength(firstWord)-1) == ']'
					&& (firstWord.charAt(1) >= '0' && firstWord.charAt(1) <= '9')) {
				
				for(int i = 1; i<str.getLength(firstWord)-1;i++) {
					num = num+firstWord.charAt(i);
				}
				break;
			}
		}
		
		refNum = str.stringToInt(num);
		
		int[] reference = new int[refNum];
		
		for (String line : lines) {
			if(str.toLower(line).equals("references") || str.toLower(line).equals("reference")) {
				break;
			}
			
			String[] words = line.split(" ");
			
			for (String word : words) {
				int len = str.getLength(word);
				if(len>2 && word.charAt(0) == '[' && (word.charAt(len-1) == ']' || word.charAt(len-2) == ']')) {
					num = "";
					for(int ind=0; ind<len; ind++) {
						if(word.charAt(ind)=='[' || word.charAt(ind)==']') {
							if(num.equals("")) continue;
							int val = str.stringToInt(num);
							reference[val-1]++;
							num = "";
							continue;
						}
						
						if(!(word.charAt(ind)>='0' && word.charAt(ind)<='9')) {
							break;
						}
						else {
							num = num + word.charAt(ind);
						}
					}
				}
			}
		}	
		return reference;
	}
	
	public SuffixTrie createSuffixTree(String text ) {
		SuffixTrie root = new SuffixTrie();
		int len = str.getLength(text);
		for(int i=0; i<len; i++) {
			root.addSuffixString(text.substring(i), i);
		}
		return root;
	}
	
	public  boolean isPatternExist(String pattern, SuffixTrie r) {

		LinkedList<Integer> val = r.findPattern(pattern);
		
		if(val == null) return false;
		else return true;
	}
	
	public int getPatternOccurance(String pattern, SuffixTrie r) {
		LinkedList<Integer> val = r.findPattern(pattern);
		
		if(val == null) return 0;
		else return val.size();
	}
	
	
}
