package bculkinmodFinal.shufflers;

import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
* Utility class for retrieving and constructing implementations of Shuffler
*
* @author benadam
*/
public class Shufflers {
	// TODO Add more shufflers and shuffler compositiors
	private static final Random rnd = new Random();
	static final Scanner scan = new Scanner(System.in);
	
	// Return a semi-random number on a cauchy distribution
	static int semiRandom(double sd, double mean) {
		int md = (int) Math.tan(Math.PI * (rnd.nextDouble() - 0.5));
		return (int) Math.min(Math.abs(md * sd + mean), 51);
	}
	
	/**
	* Returns a default implementation of the {@link Shuffler} interface.
	*
	* Currently, this is a shuffle that uses the {@link Collections#shuffle(java.util.List)}
	* method This is prone to change and should not be depended upon.
	*
	* @return A default shuffler that thoroughly shuffles a deck
	*/
	public static Shuffler defaultShuffle() {
		return new DefaultShuffler();
	}
	
	/**
	* Returns a shuffler that does no shuffling
	*
	* @return A shuffler that doesn't shuffle
	*/
	public static Shuffler noShuffle() {
		return new NullShuffler();
	}
	
	/**
	* Creates a shuffler that executes a series of shuffles in a fixed way
	*
	* @param shfs The list of shufflers to compose
	* @return A shuffler that composes multiple shuffles
	*/
	public static Shuffler compositeShuffler(final Shuffler... shfs) {
		return new CompositeShuffler(shfs);	
	}
	
	/**
	* Returns a shuffler that cuts the deck
	*
	* @return A shuffler that will cut the deck
	*/
	public static Shuffler deckCutter() {
		return new DeckCutter();
	}
	
	/**
	* Returns a shuffler that shuffles the deck using a riffle shuffle
	*
	* @return A shuffler that riffle shuffles
	*/
	public static Shuffler riffleShuffle() {
		// A riffle shuffle must be done multiple times to shuffle accurately
		return new MultiShuffler(semiRandom(1, 7), new RiffleShuffler());
	}
	
	public static Shuffler multiShuffle(int nm, Shuffler shf) {
		return new MultiShuffler(nm, shf);
	}
	
	public static Shuffler newCustom() {
		String ty;
		
		Shuffler sh = null;
		
		do {
			System.out.print("Enter type of shuffle (l to list shuffles): ");
			
			ty = scan.next();
			
			switch (ty) {
			case "l":
				System.out.println("co - composite \t dc - deck cutter");
				System.out.println("ds - default shuffle \t mu - multishuffle");
				System.out.println("ns - no shuffle \t rf - riffle shuffle");
				break;
			case "co":
				sh = Shufflers.compositeShuffler(Shufflers.newCustom());
				break;
			case "dc":
				sh = Shufflers.deckCutter();
				break;
			case "ds":
				sh = Shufflers.defaultShuffle();
				break;
			case "mu":
				System.out.print("Enter the number of times you want to shuffle: ");
			
				int inp = scan.nextInt();
				
				sh = Shufflers.multiShuffle(inp, Shufflers.newCustom());
				break;
			case "ns":
				sh = Shufflers.noShuffle();
				break;
			case "rf":
				sh = Shufflers.riffleShuffle();
				break;
			default:
				System.out.println("? US");
			}
		} while (sh == null);
		
		// This should never be null
		assert sh != null;
		
		sh.customize();
		
		return sh;
	}
	
	public static Shuffler newWrap(Shuffler git) {
		String ty;
		
		Shuffler sh = null;
		
		do {
			System.out.print("Enter type (l to list): ");
			ty = scan.next();
			switch (ty) {
			case "l":
				System.out.println("co - composite \t mu - multishuffle");
				break;
			case "co":
				sh = Shufflers.compositeShuffler(git);
				break;
			case "mu":
				System.out.print("Enter the number of times you want to shuffle: ");
				int inp = scan.nextInt();
				sh = Shufflers.multiShuffle(1, git);
				break;
			default:
				System.out.println("? US");
			}
		} while (sh == null);
		
		// This should never be null
		assert sh != null;
		
		sh.customize();
		return null;
	}	
}