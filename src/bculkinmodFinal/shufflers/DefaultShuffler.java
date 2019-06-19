package bculkinmodFinal.shufflers;

import bculkinmodFinal.Card;
import java.util.Collections;
import java.util.List;

/**
*
* @author benadam
*/
class DefaultShuffler implements Shuffler {
	public DefaultShuffler() {
		// Do nothing
	}
	
	@Override
	public void shuffle(List<Card> crds) {
		Collections.shuffle(crds);
	}
	
	@Override
	public String getShuffleName() {
		return "Java Collections";
	}
	
	@Override
	public void customize() {
		System.out.println("Can't customize default shuffler");
	}
}