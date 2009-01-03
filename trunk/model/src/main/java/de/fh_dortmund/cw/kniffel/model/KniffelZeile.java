package de.fh_dortmund.cw.kniffel.model;

import java.io.Serializable;

/**
 * 
 * @author tbs
 * 
 */
public enum KniffelZeile implements Serializable  {

	// Obere Hälfte
	ONE, TWO, THREE, FOUR, FIVE, SIX,

	// Obere Hälfte: Bonus & Summe
	SUM_TOP, BONUS_TOP, SUM_TOP_TOTAL,

	// Untere Hälfte
	THREE_OAK, FOUR_OAK, FULL_HOUSE, STREET_1, STREET_2, YAHTZEE, CHANCE,

	// Untere Hälfte: Summe + GesamtSumme
	SUM_BOTTOM_TOTAL, SUM_TOTAL
	
}
