package bculkinmodFinal.shufflers;

import bculkinmodFinal.Card;
import java.util.InputMismatchException;
import java.util.List;

/**
*
* @author benadam
*/
public class MultiShuffler implements Shuffler {
	private int nm;
	private Shuffler shf;
	
	public MultiShuffler(int nm, Shuffler shf) {
		this.nm = nm;
		this.shf = shf;
	}
	
	@Override
	public void shuffle(List<Card> crds) {
		for (int i = 0; i < nm; i++) {
			shf.shuffle(crds);
		}
	}
	
	@Override
	public String getShuffleName() {
        // This is more informative, but less decent to look at
        //return "Multi Shuffle: " + shf.getShuffleName() + " x " + nm;
        return shf.getShuffleName() + " x " + nm;
    }
    
    @Override
    public void customize() {
    	System.out.println("Customizing Multiple Shuffle");
    	System.out.print("Enter command ( m for help ): ");
    	
    	String tst = Shufflers.scan.next();
    	while (true) {
    		switch (tst) {
    		case "m":
    			System.out.println("l - print current shuffler parameters");
    			System.out.println("t - set # of times this shuffler shuffles");
    			System.out.println("s - set shuffler this is shuffling with");
    			System.out.println("c - customize shuffler this is shuffling with");
    			System.out.println("q - quit customizing this shuffler");
    			System.out.println("m - display this help message");
    			break;
    		case "l":
    			System.out.println("Shuffles " + shf.getShuffleName() + "  " + nm + " times");
    			break;
    		case "t":
    			System.out.print("Enter # of times to shuffle: ");
    			
    			try {
    				nm = Shufflers.scan.nextInt();
    			} catch (InputMismatchException e) {
    				System.out.println("? UI");
    			}
    			break;
    		case "s":
    			shf = Shufflers.newCustom();
    			break;
    		case "c":
    			shf.customize();
    			break;
    		case "q":
    			System.out.println("Done customizing multi-shuffler");
    			return;
    		default:
    			System.out.println("? UC");
    		}
    		
    		System.out.print("Enter command ( m for help ): ");
    		tst = Shufflers.scan.next();
    	}
    }  
}