import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		// Initialize the word sets
		TreeSet<String> allWords = loadWords("all-words.txt");
		TreeSet<String> solutionWords = loadWords("solutions.txt");
		String bestWord = null;
		int bestScore = Integer.MAX_VALUE;
		
		// For every word in allWords, load the next word and score it against all the words in solutionWords
		for(String nextWord:allWords) {
			int totalScoreForNextWord = 0;
			ScoredWord sw = new ScoredWord(nextWord);
		
			for (String nextSolution:solutionWords) {
				WordSet words = new WordSet(solutionWords);
				words.scoreAndFilter(sw, nextSolution);
				totalScoreForNextWord+=words.count();
			}
			
			if (totalScoreForNextWord<bestScore) {
				bestWord = nextWord;
				bestScore = totalScoreForNextWord;
			}
			
			// Print out the total score for this guess word
			System.out.printf("\"%s\", %d\n", nextWord, totalScoreForNextWord);
		}
		
		// At the end, print out the best word and its score:
		System.err.printf("Best word: \"%s\", Score: %d\n", bestWord, bestScore);
	}
	
	public static TreeSet<String> loadWords(String filename) throws Exception {
		TreeSet<String> ret = new TreeSet<String>();
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line;
		
		while ((line=br.readLine())!=null) {
			ret.add(line);
		}
		
		return ret;
	}
}
