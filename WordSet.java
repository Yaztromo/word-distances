import java.util.*;

public class WordSet {
  private TreeSet<String> words;
  
  public WordSet(Collection<String> words) {
  	this.words = new TreeSet<String>(words);
  }

  public WordSet() {
    this.words = new TreeSet<String>();
  }
  
  public int count() {
  	return words.size();
  }
  
  public void removeWordsWithLetter(char letter) {
    String letterStr = String.format("%c", letter);
    TreeSet<String> wordsInt = new TreeSet<String>(words);
  	for (String word:wordsInt) {
  		if (word.contains(letterStr)) words.remove(word);
  	}
  }
  
  public void removeWordsWithLetterInPosition(char letter, int position) {
    TreeSet<String> wordsInt = new TreeSet<String>(words);
  	for (String word:wordsInt) {
  		if (word.charAt(position)==letter) words.remove(word);
  	}
  }
  
  public void removeWordsWithoutLetter(char letter) {
  	String letterStr = String.format("%c", letter);
    TreeSet<String> wordsInt = new TreeSet<String>(words);
  	for (String word:wordsInt) {
  		if (!word.contains(letterStr)) words.remove(word);
  	}
  }
  
  public void removeWordsWithoutLetterInPosition(char letter, int position) {
    TreeSet<String> wordsInt = new TreeSet<String>(words);
  	for (String word:wordsInt) {
  		if (word.charAt(position)!=letter) words.remove(word);
  	}
  }
  
  public void scoreAndFilter(ScoredWord sw, String solution) {
  	char[] wordArray = sw.getWordArray();
  	ScoredWord.LetterScore[] scores = sw.scoreAgainst(solution);
  	
  	for (int i=0;i<wordArray.length;i++) {
  		char letter = wordArray[i];
  		ScoredWord.LetterScore ls = scores[i];
  		
  		switch (ls) {
  			case GREEN:
  				removeWordsWithoutLetterInPosition(letter, i);
  				break;
  			case YELLOW:
  				removeWordsWithoutLetter(letter);
  				removeWordsWithLetterInPosition(letter, i);
  				break;
  			case BLACK:
  				removeWordsWithLetter(letter);
  				break;
  		}
  	}
  }
}
