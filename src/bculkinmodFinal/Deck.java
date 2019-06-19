/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bculkinmodFinal;

import bculkinmodFinal.shufflers.Shuffler;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
* 52-Card deck of playing cards
*
* @author benadam
*/
public class Deck {
	/**
	* Create a deck, shuffle it, and then return it.
	*
	* @param shf The shuffler to use for shuffling
	* @return A new shuffled deck, ready for use
	*/
	public static Deck readyDeck(Shuffler shf) {
		Deck dck = new Deck();
		
		System.out.println("Shuffling deck with " + shf.getShuffleName());
		
		shf.shuffle(dck.crds);
		
		System.out.println("Deck shuffled succesfully");
		
		return dck;
	}
	
	private final List<Card> crds = new LinkedList<>();
	// Private constructor of unshuffled decks.
	
	private Deck() {
		System.out.println("Creating deck");
		
		for (Rank rank : Rank.values()) {
			for (Suit suit : Suit.values()) {
				crds.add(new Card(suit, rank));
			}
		}
		
		System.out.println("Deck has " + crds.size() + " cards.");
	}
	
	/**
	* Deals a hand from the deck
	*
	* @return The dealt hand.
	*/
	public Hand deal() {
		Hand hnd = new Hand();
		
		final List<Card> hndCrds = hnd.getCrds();
		
		for (int i = 0; i < 5; i++) {
			hndCrds.add(((Queue<Card>) crds).remove());
		}
		
		return hnd;
	}
	
	/**
	* Deal a number of hands at the same time in standard poker fashion
	*
	* @param num The number of hands to deal
	* @return An array of hands of the specified size
	*/
	public Hand[] multiDeal(int num) {
		Hand[] hnd = new Hand[num];
		
		for (int i = 0; i < num; i++) {
			hnd[i] = new Hand();
		}
		
		for (int i = 0; i < 5; i++) {
			for (Hand hand : hnd) {
				hand.getCrds().add(((Queue<Card>) crds).remove());
			}
		}
		
		return hnd;
	}
	
	/**
	* Returns a full description of the deck. Should only be used for debugging
	*
	* @return A full description of the deck
	*/
	public String toStringD() {
		StringBuilder bldr = new StringBuilder();
		
		bldr.append("Deck currently has ").append(crds.size()).append(" cards in it\n");
		bldr.append("Contents of deck are: \n");
		
		for (Card card : crds) {
			bldr.append(card).append("\n");
		}
		
		return bldr.toString();
	}
	
	@Override
	public String toString() {
		return "Deck currently has " + crds.size() + " cards";
	}	
}