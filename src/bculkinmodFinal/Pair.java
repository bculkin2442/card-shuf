package bculkinmodFinal;

import java.util.Objects;

/** Utility class for holding pairs of objects
*
* @author benadam
* @param <T1> The type of the left side
* @param <T2> The type of the right side
*/
public class Pair<T1, T2> {
	
	private T1 left;
	
	/**
	* Get the value of left
	*
	* @return the value of left
	*/
	public T1 getLeft() {
		return left;
	}
	
	/**
	* Set the value of left
	*
	* @param left new value of left
	*/
	public void setLeft(T1 left) {
		this.left = left;
	}
	
	private T2 right;
	
	/**
	* Get the value of right
	*
	* @return the value of right
	*/
	public T2 getRight() {
		return right;
	}
	
	/**
	* Set the value of right
	*
	* @param right new value of right
	*/
	public void setRight(T2 right) {
		this.right = right;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		
		hash = 37 * hash + Objects.hashCode(this.left);
		hash = 37 * hash + Objects.hashCode(this.right);
		
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
		final Pair<T1, T2> other = (Pair<T1, T2>) obj;
		
		if (!Objects.equals(this.left, other.left)) {
			return false;
		}
		
		return Objects.equals(this.right, other.right);
	}
	
	@Override
	public String toString() {
		return "Pair{" + "left=" + left + ", right=" + right + '}';
	}
	
	/**
	* Empty constructor
	*/
	public Pair() {
	}
	
	/** 
	* Full constructor for a pair of objects
	* @param left The left side of the pair
	* @param right The right side of the pair
	*/
	public Pair(T1 left, T2 right) {
		this.left = left;
		this.right = right;
	}	
}