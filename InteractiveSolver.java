import java.util.*;
import java.io.*;

public class InteractiveSolver {
	public static void main(String[] args) throws Exception {
		TreeSet<String> solutionWords = Main.loadWords("solutions.txt");
		
		WordSet ws = new WordSet(solutionWords);

		while(ws.count()>1) {
			String guess = "roate";
			String clues = "BBBBG";
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			// Ask for the guessword
			System.out.print("Enter your guessword: ");
			guess = br.readLine().toLowerCase();
			
			// Ask for the clue returned by Wordle
			System.out.print("Enter your resulting clue (G, Y, B): ");
			clues = br.readLine().toUpperCase();
			
			ws.scoreAndFilter(guess, clues.toCharArray());
			System.out.printf("Possible solutions (%d):\n", ws.count());
			System.out.println(ws);
			System.out.println("---------------------------------------------------------------------");
		}
	}
}
