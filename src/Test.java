import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		arrayOfDifferentLengths t = new arrayOfDifferentLengths("C:\\Users\\Raed\\Desktop\\tx.txt");	
		
		t.documentLength();
		System.out.println("____");
		t.UniqueWord();
		System.out.println("____");
		t.totalOccurancesForWord("data");
		System.out.println("____");
		t.totalWordsForLength(5);
		System.out.println("____");
	//___________________________________//
		t.checkAdjacent("and", "the");
		System.out.println("____");
		t.printAllWords();
		System.out.println("____");
	t.occurrences("is");
		
		
		

	}

}
