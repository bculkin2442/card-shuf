package bculkinmodFinal.shufflers;

import bculkinmodFinal.Card;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

/**
* A shuffler that will cut the deck
*
* @author benadam
*/
class DeckCutter implements Shuffler {
	private final Random rnd;
	private double talent = 1;
	
	public DeckCutter() {
		this.rnd = new Random();
	}
	
	@Override
	public void shuffle(List<Card> crds) {
		// Roughly split the deck in half. A human dealer isn't perfect. Neither
		// should a computer be so. The midpoint will be a random number on a cauchy 
		// distribution with a mean of 26 and a standard deviation of 1.
		int mid;
		
		mid = (int) Shufflers.semiRandom(talent, 26);
		
		// Split and rejoin in one action
		Collections.rotate(crds, mid);
	}
	
	@Override
	public String getShuffleName() {
		return "Deck Cut";
	}
	
	@Override
	public void customize() {
		System.out.println("Customizing Deck Cutter");
		System.out.print("Enter command ( m for help ): ");
		
		String tst = Shufflers.scan.next();
		
		while (true) {
			switch (tst) {
			case "m":
				System.out.println("t - set shuffler talent");
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
			case "l":
				System.out.println("Shuffler Talent: " + talent);
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
}