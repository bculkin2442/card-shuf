/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bculkinmodFinal;

/**
* Rank of a playing card
*
* @author benadam
*/
public enum Rank {
	Two, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack,
	Queen, King, Ace;
	
	public Rank getNext() {
		switch (ordinal()) {
		case 0:
			return Three;
		case 1:
			return Four;
		case 2:
			return Five;
		case 3:
			return Six;
		case 4:
			return Seven;
		case 5:
			return Eight;
		case 6:
			return Nine;
		case 7:
			return Ten;
		case 8:
			return Jack;
		case 9:
			return Queen;
		case 10:
			return King;
		case 11:
			return Ace;
		default:
			throw new AssertionError("Can't go higher than an Ace");
		}
	}
	
	public Rank getPrev() {
		switch (ordinal()) {
		case 1:
			return Two;
		case 2:
			return Three;
		case 3:
			return Four;
		case 4:
			return Five;
		case 5:
			return Six;
		case 6:
			return Seven;
		case 7:
			return Eight;
		case 8:
			return Nine;
		case 9:
			return Ten;
		case 10:
			return Jack;
		case 11:
			return Queen;
		case 12:
			return King;
		default:
			throw new AssertionError("Can't go lower than a two");
		}
	}
}
