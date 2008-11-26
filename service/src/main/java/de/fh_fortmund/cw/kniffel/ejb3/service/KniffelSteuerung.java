package de.fh_fortmund.cw.kniffel.ejb3.service;

import javax.ejb.Remote;

import de.fh_fortmund.cw.kniffel.model.KniffelZettel;

/**
 * 
 * @author tbs
 * 
 */
@Remote
public interface KniffelSteuerung {

	/**
	 * 
	 * @param spielerAnzahl
	 * @return
	 */
	public KniffelZettel erstelleNeuesSpiel(int spielerAnzahl);

	
	/**
	 * WŸrfelt alle WŸrfel.
	 */
	public void diceAll();

	/**
	 * WŸrfelt die markierten WŸrfel
	 * 
	 * @param cubes
	 */
	public void diceSelected();

	/**
	 * Sperrt die markierten WŸrfel.
	 * 
	 * @param cube
	 */
	public void lockCube(int cube);

	/**
	 * 
	 * @param cube
	 */
	public void unlockCube(int cube);
	
	
	
	/**
	 * 
	 */
	public void set1er();

	/**
	 * 
	 */
	public void set2er();

	/**
	 * 
	 */
	public void set3er();

	/**
	 * 
	 */
	public void set4er();

	/**
	 * 
	 */
	public void set5er();

	/**
	 * 
	 */
	public void set6er();

	/**
	 * 
	 */
	public void setDreierPasch();

	/**
	 * 
	 */
	public void setViererPasch();

	/**
	 * 
	 */
	public void setFullHouse();

	/**
	 * 
	 */
	public void setKleineStrasse();

	/**
	 * 
	 */
	public void setGrosseStrasse();

	/**
	 * 
	 */
	public void setKniffel();

	/**
	 * 
	 */
	public void setChance();

	/**
	 * 
	 * @return
	 */
	public KniffelZettel getKniffelZettel();

}
