public class ScoredWord {
  enum LetterScore {GREEN, YELLOW, BLACK};
	private char[] wordArray;

  public ScoredWord(String word) {
  	wordArray=word.toCharArray();
  }
  
	public LetterScore[] scoreAgainst(String solution) {
		char[] solutionArray = solution.toCharArray();
		LetterScore[] ret = new LetterScore[wordArray.length];
		
		for(int i=0;i<wordArray.length;i++) {
			// We have a match
			if (wordArray[i]==solutionArray[i]) {
				ret[i]=LetterScore.GREEN;
				continue;
			}
			
			// The letter is in the word, but not in this position
			boolean matched=false;
			for(int j=i+1;j<wordArray.length;j++) {
				if (wordArray[i]==solutionArray[j]) {
					matched=true;
					break;
				}
			}	
			if (matched) ret[i]=LetterScore.YELLOW;
			else ret[i]=LetterScore.BLACK;
		}
		return ret;
	}
	
	public char[] getWordArray() {
		return wordArray;
	}
}
