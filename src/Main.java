import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		@SuppressWarnings("unused")
		int numOfNodes;
		int startNode;
		int numOfFinalNodes;
		ArrayList<Integer> finalNodes = new ArrayList<Integer>();
		int numOfTransitions;
		ArrayList<Transition> transitionList = new ArrayList<Transition>();
		
		@SuppressWarnings("resource")
		Scanner keyboardFile = new Scanner(System.in);
		System.out.print("Enter filePath: ");
		String fileName = keyboardFile.nextLine();
		System.out.println("\n");
		File f = new File(fileName);

		BufferedReader reader = new BufferedReader(new FileReader(f));
		
		/*
		 * START READING DATA FROM FILE
		 */
		
		numOfNodes = Integer.valueOf(reader.readLine());
		startNode = Integer.valueOf(reader.readLine());
		numOfFinalNodes = Integer.valueOf(reader.readLine());
		
		String line = reader.readLine();
		String[] atrFinalNodes = line.split(" ");
		for(int i = 0; i < numOfFinalNodes; i++) {
			finalNodes.add(Integer.valueOf(atrFinalNodes[i]));
		}
		
		numOfTransitions = Integer.valueOf(reader.readLine());
		for(int i = 0; i < numOfTransitions; i++) {
			String lineTransitions = reader.readLine();
			String[] atrTransitions = lineTransitions.split(" ");
			transitionList.add(new Transition(Integer.valueOf(atrTransitions[0]), atrTransitions[1].charAt(0), Integer.valueOf(atrTransitions[2])));
		}
		reader.close();
		
		/*
		 * END OF READING DATA FROM FILE
		 */
		
		/*
		 * START WHILE LOOP FOR GETTING WORDS FROM USER
		 */
		Boolean flagContinue = true;
		while(flagContinue) {
			
			Boolean letterError = false;
			Boolean noNextNode = false;
			/*
			 * User prompt to give word via keyboard
			 */
			System.out.print("Insert your word: ");
			Scanner keyboard = new Scanner(System.in);
			String word = keyboard.nextLine();
			
			//Println line code for debugging purposes
			//System.out.println("startNode: " + startNode);
			int currentNode = startNode;
			/*
			 * Separate string to char and start iterating char array to find next node
			 */
			char[] wordInChar = word.toCharArray();
			int i;
			for(i = 0; i < wordInChar.length; i++) {
				
				if(Transition.letterInAlphabet(wordInChar[i])) {
					int nextNode = Transition.findNextNode(wordInChar[i], currentNode, transitionList);
					if(nextNode > 0) {
						//Println line code for debugging purposes
						//System.out.println("currentNode: " + currentNode + "  \tLetter: " + wordInChar[i] + " \tnextNode: " + nextNode);
						currentNode = nextNode;
					}
					else {
						noNextNode = true;
						letterError = true;
						break;
					}
				}
				else {
					letterError = true;
					break;
				}
			}
			
			if(!letterError) {
				/*
				 * Checking if the finalNode of transition is equal to one of the DFA's finals
				 */
				Boolean endInFinalNode = false;
				for(int x = 0; x < numOfFinalNodes; x++) {
					if(currentNode == finalNodes.get(x))
						endInFinalNode = true;
				}
				/*
				 * Message on the screen depending on whether the previous if statement is true or false
				 */
				if(endInFinalNode)
					System.out.println("This DFA was stopped in final node\n");
				else
					System.out.println("This DFA was not stopped in final node\n");
			}
			else if (letterError && !noNextNode)
				System.out.println("There was a problem with a letter of the word since it is not contained in the DFA's alphabet\n");
			else if (noNextNode && letterError)
				System.out.println("There was a problem with a letter of the word because a node dosen't support transition with this letter\n"); 
			/*
			 * Asking user if want to continue with another word
			 */
			System.out.println("Do you want to continue??");
			System.out.print("Respond (YES or NO): ");
			String answer = keyboard.nextLine();
			if(answer.equalsIgnoreCase("NO")) {
				flagContinue = false;
				keyboard.close();
			}
			System.out.println("\n");
		}
	}
}
