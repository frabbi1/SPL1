package tool;

public class StringClass {
	
	public StringClass() {
		
	}
	
	public int getLength(String str) {
		int len = 0;
		
		char[] temp = makeCharArray(str);
		for (char c: temp) {
			len++;
			
		}
		
		return len;
	}
	
	public char[] makeCharArray(String str){
		
		int len = getLength(str);
		char[] temp = new char[len];
		
		for(int i=0; i<len; i++) {
			temp[i] = str.charAt(i);
		}
		
		return temp;
	}
	
	public String charToString(char[] charArray) {
		String output = null;
		for (char c : charArray) {
			output += c;
		}
		
		return output;
	}
	
	public String charToString(char ch) {
		String output = "" + ch;
		return output;
	}
	
	public String toLower(String str) {
		int len = getLength(str);
		//char []output = new char[len];
		String output = "";
		for(int i=0; i<len; i++) {	
			if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
				output += (char) (str.charAt(i)+32);
			}
			else {
				output += str.charAt(i);
			}
		}
		
		return output;
	}
	
	public String toUpper(String str) {
		int len = getLength(str);
		String output = "";
		for(int i=0; i<len; i++) {	
			if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
				output += (char) (str.charAt(i)-32);
			}
			else {
				output += str.charAt(i);
			}
		}
		
		return output;
	}
	
	public String intToString(int value) {
		int digit;
		int quotient = value;
		String output = "";
		while(quotient > 0) {
			
			digit = quotient % 10;
			quotient = 	quotient / 10;
			output += (char)(digit+48);
			
		}
		
		String revOutput = "";
		
		for(int i=getLength(output)-1; i>=0; i--) {
			revOutput += output.charAt(i);
		}
		
		return revOutput;
		
	}
}
