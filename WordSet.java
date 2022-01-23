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

  public void scoreAndFilter(char[] guessArray, char[] letterScore) {
  	for (int i=0;i<guessArray.length;i++) {
  		char letter = guessArray[i];
  		char score = letterScore[i];
  		
  		switch (score) {
  			case 'G':
  				removeWordsWithoutLetterInPosition(letter, i);
  				break;
  			case 'Y':
  				removeWordsWithoutLetter(letter);
  				removeWordsWithLetterInPosition(letter, i);
  				break;
  			case 'B':
  				// Only remove if all instances of this letter in the guessword are also black
  				boolean remove = true;
  				for(int j=0;j<guessArray.length;j++) {
  					if (j==i) continue;
  					if (letter==guessArray[j] && letterScore[j]!='B') {
  						remove=false;
  						break;
  					}
  				}
  				if (remove) {
  					removeWordsWithLetter(letter);
  				}
  				break;
  			default:
  				break;
  		}
  	}
  }
  
  public void scoreAndFilter(String guess, char[] letterScore) {
  	char[] guessArray = guess.toCharArray();
		scoreAndFilter(guessArray, letterScore);
  }
  
  public void scoreAndFilter(ScoredWord sw, String solution) {
  	char[] guessArray = sw.getWordArray();
  	ScoredWord.LetterScore[] scores = sw.scoreAgainst(solution);
  	char[] letterScore = new char[scores.length];
  	
  	int i=0;
  	for(ScoredWord.LetterScore ls:scores) {
  		switch (ls) {
  			case GREEN:		letterScore[i++]='G'; break;
  			case YELLOW: 	letterScore[i++]='Y'; break;
  			case BLACK:		letterScore[i++]='B'; break;
  		}
  	}
  	
		scoreAndFilter(guessArray, letterScore);
  }
  
  public String toString() {
  	StringBuilder sb = new StringBuilder();
  	int i=0;
  	for(String word:words) {
  		sb.append(word).append(", ");
			if (++i % 10 == 0) sb.append('\n');
  	}
  	return sb.toString();
  }
}
