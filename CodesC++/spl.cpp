#include<iostream>
#include<fstream>
#include<sstream>
#include<vector>

using namespace std;

struct map{
	string key;
	int value;
};

string line="";
vector<string> stopWord;
//------------------------------------------
int stringSize(string st){
	int i=0;
	while(st[i]){
		i++;
	}
	return i;
}
//-------------------------------------------
int makeNum(string num){
	int len = stringSize(num);
	int sum =0;
	for( int i=0; i<len; i++){
		sum = sum*10;
		sum += (num[i]-48);
	}
	return sum;
}
//-------------------------------------------
string makeSmaller(string word){
	for(int i=0; i<stringSize(word); i++){
		if(word[i]>='A' && word[i]<='Z') word[i] = word[i]+32;
		else continue;
	}
	return word;
}
//-------------------------------------------	
int checkExistence(string word, vector<map> &wordMap){
	if(wordMap.empty()) return -1;
	int len = wordMap.size();
	for(int i=0; i<len; i++){
		if(wordMap[i].key == word) return i;
	}
	return -1;
}
//--------------------------------------------
string cleanUp(string text){
	
	int textSize = text.length();
	for(int i=0; i<textSize; i++){
		if(text[i] == '.' || text[i] == ',' || text[i] == ':'|| text[i] == '('|| text[i] == ')'
		|| text[i] == ';'|| text[i] == '>'|| text[i] == '<'|| text[i] == '$' || text[i] == '?') 
			text[i] = ' ';
	}
	return text;
}
//----------------------------------------------
bool is_stopWord( string w ){
	int len = stopWord.size();
	for( int i=0; i<len; i++ ){
		if(w == stopWord[i]) return true;
	}
	return false;
}
//-----------------------------------------------
void listStopwords(){
	
	ifstream sw;
	sw.open("stop_words.txt");
	
	if(sw.is_open()){
		string temp;
		while(!sw.eof()){
			getline(sw,temp);
			stopWord.push_back(temp);	
		}	
	}
}

//-----------------------------------------------
void tokenize(){
	
	string lineCopy = line;
	lineCopy = cleanUp(lineCopy);
	istringstream iss(lineCopy);
	vector<map> wordMap;
	listStopwords();
	
	string word;
	
	while(iss>>word){
		map temp;
		word = makeSmaller(word);
		
		if(is_stopWord(word)) continue;
		
		temp.key = word;
		int index = checkExistence(word,wordMap);
		if(index==-1){
			temp.value=1;
			wordMap.push_back(temp);	
		}
		else{
			wordMap[index].value++;
		}
		
	}
	
	int len = wordMap.size();
	
	cout << "Word   ------------>  Occurrences" << endl;
	for(int i=0; i<len; i++){
		cout << wordMap[i].key << " ------------> " << wordMap[i].value << endl;
	}
	cout << endl;
}
//-------------------------------------------
void extractPaperDetails(){
	string temp = line;
	istringstream iss(temp);
	
	string title = "", authorName = "", superVname = "", emails[2], institute[2], varsity[2];
	emails[0] = ""; emails[1] = ""; 
	institute[0] = ""; institute[1] = "";
	varsity[0] = ""; varsity[1] = "";
	
	string word;
	
	while(iss >> word){
		if(word == "Md") break;
		title += word;
		title += " ";
	}
	cout << "TITLE: " << title << endl << endl;
	authorName += word;
	
	for(int i=0; i<10; i++){
		getline(iss, word);
		if(i==0) authorName += word;
		else if(i==1) institute[0] += word;
		else if(i==2) varsity[0] += word;
		else if(i==4) emails[0] += word;
		else if(i==5) superVname += word;
		else if(i==6) institute[1] += word;
		else if(i==7) varsity[1] += word;
		else if(i==9) emails[1] += word;
	}
	cout<< "FIRST AUTHOR DETAILS:\n"
		<< authorName << endl
		<< emails[0] << endl
		<< institute[0] << endl
		<< varsity[0] << endl << endl
		<< "SECOND AUTHOR DETAILS:\n"
		<< superVname << endl
		<< emails[1] << endl
		<< institute[1] << endl
		<< varsity[1] << endl;	
}
//---------------------------------------------------------

int main(){
	ifstream iFile;
	iFile.open("file1.txt");
	
	if(iFile.is_open()){
				
		while(!iFile.eof()){
			string temp;
			getline(iFile,temp);
			line = line+temp +"\n";
		}
		
		tokenize();
		
// first author	of every reference and number of references
		istringstream iss2(line);
		string word;
		while(iss2>>word){
			word = makeSmaller(word);
			if(word == "references"){
				break;
			}
		}
		string author[100];
		int refNum=0;
		cout << "FIRST AUTHOR OF EVERY REFERENCES:\n";
		while(iss2>>word){
			if(word[0]=='[' && word[stringSize(word)-1]==']' && (word[1])>='0'&& word[1]<='9'){
				string authorName = "";
				char c;
				iss2.get(c);
				while(iss2.get(c) && c!=','){
					authorName += c;
				}
				
				istringstream issE(authorName);
				string temp1, temp2="";
				for(int j=0; issE>>temp1; j++){
					if(j!=0 && temp1=="and"){
						break;
					}
					temp2 += temp1;
				}
				authorName=temp2;
				author[refNum] = authorName;
				refNum++;
				cout << authorName << endl;
			}
		}
		
		cout <<"TOTAL REFERENCES: " << refNum << endl  <<endl;
				
//reference occurrences in the file
		cout << "OCCURRENCES OF REFERENCES:\n";
		istringstream iss3(line);
		int ref[refNum]={};
		while( iss3 >> word ){
			word  = makeSmaller(word);
			if(word == "references") break;
			int len = stringSize(word);
			if(word[0] == '[' && (word[len-1] == ']' || word[len-2] == ']')){
				string num = "";
				for(int i=1; i<len; i++){
					if(word[i] == '[' || word[i] == ']'){
						if(num == "") continue;
						int x = makeNum(num);
						ref[x-1]++;
						num = "";
						continue;
					}
					if(!isdigit(word[i])){
						break;
					}
					else{
						num += word[i];
					}
				}
			}
		}
		
		for( int i=0; i<refNum; i++ ){
			cout << "ref " << i+1 << ':' << ref[i] << endl;
		}
		cout << endl;
//-----------------------------------	
		extractPaperDetails();
		//extractKeywords();	
	}
	else{
		cout << "Invalid file\n";
	}
	iFile.close();
	
	return 0;
}

