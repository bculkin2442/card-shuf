package bculkinmodFinal;

import bculkinmodFinal.shufflers.Shufflers;
import java.util.Scanner;

/**
*
* @author benadam
*/
public class PokerV1 {
	/**
	* Main method
	* @param args Commandline args 
	*/
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Deck d;
		
		boolean again = true;
		
		while (again) {
			System.out.print("Do you want to use a custom shuffle? (y or n) ");
			
			if (scan.next().equalsIgnoreCase("y")) {
				d = Deck.readyDeck(Shufflers.newCustom());
			} else {
				d = Deck.readyDeck(Shufflers.compositeShuffler(Shufflers.riffleShuffle(),
					Shufflers.deckCutter()));
			}
			
			Hand[] hs = d.multiDeal(2);
			
			for (int i = 0; i < hs.length; i++) {
				Hand hand = hs[i];
				
				System.out.println("Hand " + (i + 1) + " is " + hand.toString());
			}
			
			int i = hs[0].compareTo(hs[1]);
			
			if (i > 0) {
				System.out.println("Hand 1 won.");
			} else if (i < 0) {
				System.out.println("Hand 2 won.");
			} else {
				System.out.println("Hands 1 & 2 tied.");
			}
			
			System.out.print("Do you want to play again? (y or n) ");
			again = scan.next().equalsIgnoreCase("y");
		}
	}
}
