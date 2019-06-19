package bculkinmodFinal;

import java.util.ArrayList;
import java.util.List;

/** A standard five-card hand of playing cards..
*
* @author benadam
*/
public class Hand implements Comparable<Hand> {
	private final List<Card> crds = new ArrayList<>(5);
	
	/**
	* Get all of the cards in a hand
	* @return crds
	*/
	public List<Card> getCrds() {
		return crds;
	}
	
	@Override
	public String toString() {
		StringBuilder bldr = new StringBuilder();
		
		bldr.append("a hand of ").append(crds.size()).append(" cards\n");
		bldr.append("These are: ");
		
		for (Card card : crds) {
			bldr.append(card).append(", ");
		}
		
		bldr.deleteCharAt(bldr.lastIndexOf(","));
		
		return bldr.toString();
	}
	
	@Override
	public int compareTo(Hand h) {
		Pair<Hands, List<Card>> p = Hands.generalRank(this);
		
		System.out.println("Hand 1 is a " + p.getLeft() + " " + p.getRight());
		
		Pair<Hands, List<Card>> p1 = Hands.generalRank(h);
		
		System.out.println("Hand 2 is a " + p.getLeft() + " " + p1.getRight());
		
		if(p.getLeft() == p1.getLeft()) {
			return LComparator.compare(p.getRight(), p1.getRight());
		} else {
			return p.getLeft().compareTo(p1.getLeft());
		}
	}	
}