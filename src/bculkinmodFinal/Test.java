package bculkinmodFinal;

import bculkinmodFinal.shufflers.Shufflers;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
* Utility class for testing things and making sure they work correctly
*
* @author benadam
*/
public class Test {
	
	public static void main(String[] args) {
		// Deck testing
		System.out.println("Testing decks are created correctly.");
		Deck d = Deck.readyDeck(Shufflers.noShuffle());
		System.out.println(d.toStringD());
		
		// Shuffle testing
		System.out.println("\nTesting default shuffler");
		d = Deck.readyDeck(Shufflers.riffleShuffle());
		System.out.println(d.toStringD());
		
		// Dealing testing
		System.out.println("\nTesting hands deal right.");
		Hand h = d.deal();
		
		System.out.println(h + "\n");
		System.out.println("Testing simultaneous dealing of multiple hands");
		System.out.println(d.toStringD());
		
		Hand[] hs = d.multiDeal(2);
		
		for (Hand hand : hs) {
			System.out.println(hand);
		}
		
		// Rank up/down testing
		System.out.println(Rank.Ace.getPrev());
		System.out.println(Rank.Two.getNext());
	}
}