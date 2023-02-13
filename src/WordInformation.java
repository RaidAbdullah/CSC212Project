
public class WordInformation {
	String word;
	linkedList<WordOccurrence> occList =  new linkedList<WordOccurrence>();
	int size;
	
	public WordInformation(String w, linkedList<WordOccurrence> l) {
		this.word = w;
		this.occList = l;
		size = 1;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public linkedList<WordOccurrence> getOccList() {
		return occList;
	}
	
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	

}
