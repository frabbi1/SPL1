package tool;

import java.awt.List;
import java.util.LinkedList;

public class SuffixTrie {
	
	SuffixTrie[] branch = new SuffixTrie[10000];
	LinkedList<Integer> itr; 
	StringClass str = new StringClass();
	
	public SuffixTrie() {
		itr = new LinkedList<Integer>();
		for(int i=0; i<10000; i++) {
			branch[i] = null;
		}
	}
	
	public void addSuffixString(String suffix, int index) {
		itr.add(index);
		int len = str.getLength(suffix);
		
		if(len>0) {
			char target = suffix.charAt(0);
			if(branch[target]==null ) branch[target] = new SuffixTrie();
			
			branch[target].addSuffixString(suffix.substring(1), index+1);
		}
	}
	
	public LinkedList<Integer> findPattern(String pattern){
		if(str.getLength(pattern) == 0) {
			return itr;
		}
		if(branch[pattern.charAt(0)] != null) {
			return branch[pattern.charAt(0)].findPattern(pattern.substring(1));
		}
		return null;
	}

}
