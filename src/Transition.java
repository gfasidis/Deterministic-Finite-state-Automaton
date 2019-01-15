import java.util.ArrayList;

public class Transition {
	
	private int currentNode;
	private char letter;
	private int nextNode;
	private static ArrayList<Character> alphabet = new ArrayList<Character>();
	
	
	public Transition(int currentNode, char letter, int nextNode) {
		this.currentNode = currentNode;
		this.letter = letter;
		this.nextNode = nextNode;
		addLetterInAlphabet(this.letter);
	}


	public int getCurrentNode() {
		return currentNode;
	}


	public char getLetter() {
		return letter;
	}


	public int getNextNode() {
		return nextNode;
	}
	
	private static void addLetterInAlphabet(char aLetter) {
		if(!letterInAlphabet(aLetter))
			alphabet.add(aLetter);
	}
	
	public static boolean letterInAlphabet(char aLetter){
		for(Character aChar : alphabet)
			if(aChar == aLetter)
				return true;
		return false;
	}
	
	
	public static int findNextNode(char aLetter, int currNode, ArrayList<Transition> trArrayList) {
		
		for(Transition aTransition : trArrayList) {
			if((currNode == aTransition.getCurrentNode()) && (aLetter == aTransition.getLetter())) {
				return aTransition.getNextNode();
			}	
		}
		return -500;

		
	}
	
	
}
