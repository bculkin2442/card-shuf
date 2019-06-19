package bculkinmodFinal.shufflers;

import bculkinmodFinal.Card;
import java.util.List;

/**
* A shuffler that doesn't shuffle
* @author benadam
*/
class NullShuffler implements Shuffler {
	public NullShuffler() {
		// Do nothing
	}
	
	@Override
	public void shuffle(List<Card> crds) {
		// Do nothing
	}
	
	@Override
	public String getShuffleName() {
		return "Null Shuffle";
	}
	
	@Override
	public void customize() {
		System.out.println("Null Shuffler has no customization options.");
	}
}