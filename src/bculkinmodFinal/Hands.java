package bculkinmodFinal;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
* All of the possible hand rankings
*
* @author benadam
*/
public enum Hands {
	High, Pair, TwoPair, ThreeOfAKind, Straight, Flush, FullHouse, FourOfAKind,
	StraightFlush;
	
	private static boolean rankStraight(List<Card> crds) {
		if (crds.size() != 5) {
			return false;
		}
		
		Collections.sort(crds);
		
		Rank cRank = crds.get(0).getRnk();
		
		int i = 0;
		
		try {
			for (i = 0; i < crds.size(); i++) {
				Card card = crds.get(i);
				
				if (card.getRnk() != cRank) {
					return false;
				}
				
				cRank = cRank.getNext();
			}
			
			return true;
		} catch (AssertionError e) {
			// If this is true, high card in the straight was an ace
			// Else, we hit the hand upper bound. No straight here
			return i == crds.size() - 1;
		}
	}
	
	private static boolean rankFlush(List<Card> crds) {
		if (crds.size() != 5) {
			return false;
		}
		
		Suit s = crds.get(0).getSuit();
		
		for (Card card : crds) {
			if (card.getSuit() != s) {
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean rankMultiple(int threshold, List<Card> crds, List<Card> cs) {
		if (crds.size() < threshold) {
			return false;
		}
		
		boolean flag;
		
		for (int i = 0; i < crds.size(); i++) {
			flag = false;
			
			Card card = crds.get(i);
			
			List<Card> cs1 = new LinkedList<>(crds);
			
			cs1.remove(card);
			
			for (int j = 0; j < cs1.size(); j++) {
				Card card1 = cs1.get(j);
				
				if (!card1.equals(card)) {
					flag = true;
					continue;
				}
				
				if (threshold > 2) {
					List<Card> cs2 = new LinkedList<>(cs1);
					
					cs2.remove(card1);
					
					for (int k = 0; k < cs2.size(); k++) {
						Card card2 = cs2.get(k);
						
						if (!card2.equals(card1)) {
							flag = true;
							continue;
						}
						
						if (threshold > 3) {
							List<Card> cs3 = new LinkedList<>(cs2);
							
							cs3.remove(card2);
							
							for (int l = 0; l < cs3.size(); l++) {
								Card card3 = cs3.get(l);
								
								if (!card3.equals(card2)) {
									flag = true;
									continue;
								}
								
								if (flag == false) {
									cs.add(card);
									cs.add(card1);
									cs.add(card2);
									cs.add(card3);
									
									return true;
								}
							}
						}
						
						if (flag == false) {
							cs.add(card);
							cs.add(card1);
							cs.add(card2);
							
							return true;
						}
					}
				}
				
				if (flag == false) {
					cs.add(card);
					cs.add(card1);
					
					return true;
				}
			}
		}
		
		return false;
	}
	
	/** 
	* Rank a hand according to poker rules
	* @param hd The hand to compare against
	* @return A pair of the type of hand and if necessary, the cards involved
	*/
	public static Pair<Hands, List<Card>> generalRank(Hand hd) {
		List<Card> cs = new LinkedList<>();
		
		if (rankStraight(hd.getCrds()) && rankFlush(hd.getCrds())) {
			// Handle straight flush
			return new Pair<>(StraightFlush, hd.getCrds());
		} else if (rankMultiple(4, hd.getCrds(), cs)) {
			// Handle four of a kind
			return new Pair<>(FourOfAKind, cs);
		} else if (rankMultiple(3, hd.getCrds(), cs)) {
			// Handle Full House
			List<Card> crds = new LinkedList<>(hd.getCrds());
			
			crds.removeAll(cs);
			
			List<Card> rt = new LinkedList<>(cs);
			
			if (rankMultiple(2, crds, cs)) {
				rt.addAll(cs);
				
				return new Pair(FullHouse, rt);
			}
		} else if (rankFlush(hd.getCrds())) {
			// Handle Flush
			return new Pair(Flush, hd.getCrds());
		} else if (rankStraight(hd.getCrds())) {
			// Handle Straight
			return new Pair(Straight, hd.getCrds());
		} else if (rankMultiple(3, hd.getCrds(), cs)) {
			// Handle three of a kind
			return new Pair(ThreeOfAKind, cs);
		} else if (rankMultiple(2, hd.getCrds(), cs)) {
			// Handle Two Pair
			List<Card> crds = new LinkedList<>(hd.getCrds());
			
			crds.removeAll(cs);
			
			List<Card> rt = new LinkedList<>(cs);
			
			if (rankMultiple(2, crds, cs)) {
				rt.addAll(cs);
				return new Pair(TwoPair, rt);
			}
		} else if (rankMultiple(2, hd.getCrds(), cs)) {
			// Handle One Pair
			return new Pair(Pair, cs);
		} else {
			// Handle high card
			Collections.sort(hd.getCrds());
			
			return new Pair(High, Arrays.asList(hd.getCrds().get(hd.getCrds().size() - 1)));
		}
		
		return null;
    }
}
