package bculkinmodFinal.shufflers;

import bculkinmodFinal.Card;
import java.util.List;

/**
* Various shuffles for a list of cards This is designed for those who want a
* more authentically shuffled deck that was not shuffled with machine
* precision, as well as if one wants to be able to replay a given hand.
*
* @author benadam
*/
public interface Shuffler {
	
	/**
	* Shuffle a list of cards in place.
	*
	* @param crds The list of cards to shuffle
	*/
	void shuffle(List<Card> crds);
	
	/**
	* The name of this shuffle. This is for card sharks who know the names and
	* how the various shuffles work.
	*
	* @return The name of the shuffle this Shuffler implements
	*/
	String getShuffleName();
	
	/**
	* Call to tell a shufler to run its configuration program.
	*
	*/
	void customize();
}