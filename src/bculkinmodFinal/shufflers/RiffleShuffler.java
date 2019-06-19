package bculkinmodFinal.shufflers;

import bculkinmodFinal.Card;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
*
* @author benadam
*/
class RiffleShuffler implements Shuffler {
	private double talent = 1;
	
	// Which deck to put first. False means top first.
	private boolean preferred = false;
	
	public RiffleShuffler() {
		// Do nothing
	}
	
	@Override
	public void shuffle(List<Card> crds) {
		// Roughly split the deck in half.
		int mid = Shufflers.semiRandom(talent, 26);
		
		List<Card> top = crds.subList(0, mid);
		List<Card> btm = crds.subList(mid, 52);
		
		// Interleave the decks
		Iterator<Card> topIt = top.iterator();
		Iterator<Card> btmIt = btm.iterator();
		
		LinkedList<Card> tmp = new LinkedList<>();
		
		if (preferred) {
			interleave(btmIt, topIt, tmp);
		} else {
			interleave(topIt, btmIt, tmp);
		}
		
		crds.clear();
		crds.addAll(tmp);
	}
	
	@Override
	public String getShuffleName() {
		return "Rifle Shuffle";
	}
	
	@Override
	public void customize() {
		System.out.println("Customizing Riffle Shuffle.");
		System.out.print("Enter command ( m for help ): ");
		
		String tst = Shufflers.scan.next();
		
		while (true) {
			switch (tst) {
			case "m":
				System.out.println("t - set shuffler talent");
				System.out.println("p - set preferred deck");
				System.out.println("m - show this help message");
				System.out.println("l - print current shuffler parameters");
				System.out.println("q - stop customizing this shuffler");
				break;
			case "t":
				System.out.print("How talented is the shuffler (0.0 - 4.0) ? ");
		
				try {
					talent = Shufflers.scan.nextDouble();
				} catch (InputMismatchException imx) {
					System.out.println("? UI");
				}
				break;
			case "p":
				System.out.print("Should the bottom deck be preferred ( True or False ) ? ");
				
				try {
					preferred = Shufflers.scan.nextBoolean();
				} catch (InputMismatchException imx) {
					System.out.println("? UI");
				}
				break;
			case "l":
				System.out.println("Shuffler Talent: " + talent);
				System.out.println("Is Bottom Preferred: " + preferred);
				break;
			case "q":
				System.out.println("Finished customizing shuffler.");
				return;
			default:
				System.out.println("? UC");
			}
			
			tst = Shufflers.scan.next();
			System.out.print("Enter command ( m for help ): ");
		}
		
	}
	
	private void interleave(Iterator<Card> topIt, Iterator<Card> btmIt, LinkedList<Card> tmp) {
		while (topIt.hasNext() || btmIt.hasNext()) {
			if (topIt.hasNext()) {
				tmp.add(topIt.next());
			}
			
			if (btmIt.hasNext()) {
				tmp.add(btmIt.next());
			}
		}
	}
}