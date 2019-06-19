package bculkinmodFinal;

/**
* A Standard playing card with rank and suit
*
* @author benadam
*/
public class Card implements Comparable<Card> {
	private final Suit suit;
	
	private final Rank rnk;
	
	/** 
	* Construct a playing card with suit and rank
	* @param suit The suit of the playing card
	* @param rnk The rank of the playing card
	*/
	public Card(Suit suit, Rank rnk) {
		this.suit = suit;
		this.rnk = rnk;
	}
	
	/**
	* Get the value of suit
	*
	* @return the value of suit
	*/
    public Suit getSuit() {
    	return suit;
    }
    
    /**
    * Get the value of rnk
    *
    * @return the value of rnk
    */
    public Rank getRnk() {
    	return rnk;
    }
    
    @Override
    public String toString() {
    	return rnk + " of " + suit;
    }
    
    @Override
    public int hashCode() {
    	int hash = 5;
    	
    	hash = 83 * hash + (this.suit != null ? this.suit.hashCode() : 0);
    	hash = 83 * hash + (this.rnk != null ? this.rnk.hashCode() : 0);
    	
    	return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == null) {
    		return false;
    	}
    	
    	if (getClass() != obj.getClass()) {
    		return false;
    	}
    	
    	final Card other = (Card) obj;
    	
    	if (this.suit != other.suit) {
    		return false;
    	}

    	return this.rnk == other.rnk;
    }
    
    @Override
    public int compareTo(Card o) {
    	return rnk.compareTo(o.getRnk());
    }
}