package bculkinmodFinal;

import java.util.List;

/**
* Utility class for comparing lists
*
* @author benadam
*/
public class LComparator {
	/**
	* Implements standard java compareTo method. Couldn't implement Comparator
	* because it would cause type errors
	* @param o1 The first list
	* @param o2 The second list
	* @return A integer comparing the two
	*/
	public static int compare(List<Card> o1, List<Card> o2) {
		if(o1.equals(o2)) {
			return 0;
		}
		
		try {
			for (int i = 0; i < o1.size(); i++) {
				Card card = o1.get(i);
				
				if (card.equals(o2.get(i))) {
					// Do nothing
				} else {
					return card.compareTo(o2.get(i));
				}
			}
			
			return -1;
		} catch (ArrayIndexOutOfBoundsException e) {
			// Second is shorter
			return 1;
		}
	}
}