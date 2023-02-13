import java.io.*;
import java.util.Scanner;

public class arrayOfDifferentLengths {
	
	private int TotalOfWord = 0;
	private int TotalOfUniqueWord = 0;
	private int counterLine = 0;
	private int counterWords = 0;

	private linkedList<WordInformation>[] arrayOfDifferentLengths;
	private WordInformation sortedArray[];
	
    
	
	//Method 1
    public arrayOfDifferentLengths(String f) throws FileNotFoundException {
    	
    	arrayOfDifferentLengths = new linkedList[50];
    	
    	File file = new File(f);
    	Scanner input = new Scanner(file);
    	
    	while(input.hasNextLine()) {
    		String line = input.nextLine();
    		String tmp = "";
    		
    		if(line.length() != 0) counterLine++;
    		counterWords = 0;
    		
    		String isAdd = "no";
    		
    		for(int i=0; i<line.length(); i++) {
    			
    			char ch = line.charAt(i);
    			String chS = "" + ch;
    			chS = chS.toLowerCase();
    			
    			if(chS.hashCode() != 32) {
    				if(CheckChar(line, i)) {
    					tmp += chS;
    					isAdd = "yes";
    				}
    			}
    			else {
    				if(isAdd == "yes") {
    					AddWord(tmp);
    					isAdd = "no";
    					tmp = "";
    				}
    			}
    		}
    		
    		if(isAdd == "yes") {
    			AddWord(tmp);
				isAdd = "no";
				tmp = "";
			}
    	}

		sortedArray = new WordInformation[TotalOfUniqueWord];

		// fill the sortedArray :

		int counter = 0;
		for (int i = 0; i < arrayOfDifferentLengths.length; i++) {

			if (arrayOfDifferentLengths[i] != null) {
				arrayOfDifferentLengths[i].findfirst();

				while (!arrayOfDifferentLengths[i].outOfList()) {

					sortedArray[counter] = arrayOfDifferentLengths[i].retrieve();
					counter++;
					arrayOfDifferentLengths[i].findNext();
				}
			}
		}

		// selection sorting :

		for (int i = 0; i < sortedArray.length; i++) {
			int min = i;

			for (int j = i + 1; j < sortedArray.length; j++) {
				if (sortedArray[j].occList.length() < sortedArray[min].occList.length()) {
					min = j;
				}
			}
			WordInformation tmp = sortedArray[i];
			sortedArray[i] = sortedArray[min];
			sortedArray[min] = tmp;
		}

	}

	// private method for adding the word :

	private void AddWord(String word) {
		TotalOfWord++;
		counterWords++;
		int lengthOfWord = word.length();

		WordOccurrence list = new WordOccurrence(counterLine, counterWords);

		linkedList<WordOccurrence> l = new linkedList<>();
		l.insert(list);
		WordInformation temp = new WordInformation(word, l);

		// 1- if this is the first time we add in this length:
		if (arrayOfDifferentLengths[lengthOfWord] == null) {
			arrayOfDifferentLengths[lengthOfWord] = new linkedList<>();
			arrayOfDifferentLengths[lengthOfWord].insert(temp);

			TotalOfUniqueWord++;
			return;
		}

		// 2- if the word exist and we just want to add the new place:
		arrayOfDifferentLengths[lengthOfWord].findfirst();
		arrayOfDifferentLengths[lengthOfWord].retrieve().size++;

		while (!arrayOfDifferentLengths[lengthOfWord].outOfList()) {

			if (arrayOfDifferentLengths[lengthOfWord].retrieve().word.equals(word)) {
				arrayOfDifferentLengths[lengthOfWord].retrieve().occList.insert(list);
				return;

			}

			arrayOfDifferentLengths[lengthOfWord].findNext();
		}

		// 3- if the word dosen't exist in the list :
		arrayOfDifferentLengths[lengthOfWord].insert(temp);
		TotalOfUniqueWord++;
	}

    
    
    
    // private method to check if the char valid or not :
    
    private boolean CheckChar(String line, int locat) {
		String l1 = "" + line.charAt(locat);
		l1 = l1.toLowerCase();
		int c1 = l1.hashCode();
		if(c1 >= 97 && c1 <= 122) {
			return true;
		}
		
		if(locat == 0 || locat == line.length()-1) return false;
		
		String l2 = "" + line.charAt(locat-1);
		int c2 = l2.hashCode();
		String l3 = "" + line.charAt(locat+1);
		int c3 = l3.hashCode();
		
		if((c2 >= 97 && c2 <= 122) && (c3 >= 97 && c3 <= 122)) return true;
		
		return false;
	}
    


	//Method 2
	public void documentLength(){
		System.out.println("would be = " +TotalOfWord);
	}
	
	
	//Method 3
	public void UniqueWord(){
		System.out.println("Unique words Would be = " +TotalOfUniqueWord);
	}
	
	//
	//Method 4
	public void totalOccurancesForWord(String w) {
		arrayOfDifferentLengths[w.length()].findfirst();
		
		while (!arrayOfDifferentLengths[w.length()].outOfList()) {
			if(arrayOfDifferentLengths[w.length()].retrieve().word.equalsIgnoreCase(w)) {
				System.out.println(arrayOfDifferentLengths[w.length()].retrieve().occList.length());
				return;
			}
			arrayOfDifferentLengths[w.length()].findNext();
		}
		System.out.println("0");
	}
	
	
	
	
	//Method 5
	public void totalWordsForLength(int l ) {
		if(l > 49) {
			System.out.println("0");
			return;
		}
		if(arrayOfDifferentLengths[l] != null){
    	arrayOfDifferentLengths[l].findfirst();
		System.out.println(arrayOfDifferentLengths[l].retrieve().size);
		return;
		}
		System.out.println("0");
	}
		
	//Method 6
	
	public void printAllWords() {
		for(int i=sortedArray.length-1; i>=0 ; i--) {
			System.out.println("(" +sortedArray[i].word+ "," +sortedArray[i].occList.length()+ ") ");
		}
	}
	
	//Method 7
	public void occurrences(String w) {
		int len = w.length();
		
		arrayOfDifferentLengths[len].findfirst();
		
		while(!arrayOfDifferentLengths[len].outOfList()) {
			if(arrayOfDifferentLengths[len].retrieve().word.equalsIgnoreCase(w)) {
				arrayOfDifferentLengths[len].retrieve().occList.findfirst();
				
				while(!arrayOfDifferentLengths[len].retrieve().occList.outOfList()) {
					System.out.print("(" +arrayOfDifferentLengths[len].retrieve().occList.retrieve().lineNo);
					System.out.print("," +arrayOfDifferentLengths[len].retrieve().occList.retrieve().position+ ") ");
					arrayOfDifferentLengths[len].retrieve().occList.findNext();
				}
				System.out.println("");
				return;
			}
			arrayOfDifferentLengths[len].findNext();
		}
		System.out.println("the word is not in the file");
	}
	
	//Method 8
	public void checkAdjacent(String w1,String w2) {
//		boolean flag = false;
		int w1L = w1.length(); 
		int w2L= w2.length();
		arrayOfDifferentLengths[w1L].findfirst(); 
		arrayOfDifferentLengths[w2L].findfirst();
		Node<WordInformation> word1=null;
		Node<WordInformation> word2=null;
		
		while ( word1 == null && !arrayOfDifferentLengths[w1L].outOfList()) {//to find the word in the linkedList
			if(arrayOfDifferentLengths[w1L].current.data.word.equalsIgnoreCase(w1))
				word1 = arrayOfDifferentLengths[w1L].current;
			else  arrayOfDifferentLengths[w1L].findNext();
		}
		while (word2 == null && !arrayOfDifferentLengths[w2L].outOfList()) {// ......
			if(arrayOfDifferentLengths[w2L].current.data.word.equalsIgnoreCase(w2)) 
				word2 = arrayOfDifferentLengths[w2L].current;
				
			else  arrayOfDifferentLengths[w2L].findNext();
		}
		if (word1 == null || word2 == null) {
			System.out.println("the word is not in the file");
			return; // if the words is not found null is returned
		}
		  arrayOfDifferentLengths[w1L].current.data.occList.findfirst();
		  linkedList<WordOccurrence> cur1 = arrayOfDifferentLengths[w1L].retrieve().occList;//pointer for the occ for word 1
		  
		arrayOfDifferentLengths[w2L].current.data.occList.findfirst();
		 linkedList<WordOccurrence> cur2 = arrayOfDifferentLengths[w2L].retrieve().occList;//pointer for the occ for word 2
		 System.out.println("the 1st word line number :"+cur1.retrieve().lineNo);
		 System.out.println("the 2st word line number :"+cur2.retrieve().lineNo);
		 System.out.println("The 1st word postion:"+cur1.retrieve().position);
		 System.out.println("The 2st word postion:"+cur2.retrieve().position);
		while (!cur1.outOfList() && !cur2.outOfList()) {
		  if(cur1.retrieve().lineNo == cur2.retrieve().lineNo) {
			 if((cur1.retrieve().position) - (cur2.retrieve().position) == 1 ||  (cur1.retrieve().position) - (cur2.retrieve().position) == -1) {
				 System.out.println("the two words (" +w1+ ") and (" +w2+ ") would be : true");
				 return;
			 }
			 if(cur1.retrieve().position > cur2.retrieve().position) {
				 cur2.findNext();
			 }
			 else {
				 cur1.findNext();
			 }
		  }
		  else if(!cur1.outOfList() && !cur2.outOfList()) {
			  
			  if(cur1.retrieve().lineNo > cur2.retrieve().lineNo) {
				  cur2.findNext();
			  }
			  else {
				  cur1.findNext();
			  }
		  }
		  else {
			  System.out.println("the two words (" +w1+ ") and (" +w2+ ") would be : false");
			  return;
		  }
		}
		System.out.println("the two words (" +w1+ ") and (" +w2+ ") would be : false");
	}
	
	/////

}
