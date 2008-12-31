package de.fh_fortmund.cw.kniffel.ejb3.service;

import java.util.List;

import javax.ejb.Remote;

import de.fh_dortmund.cw.kniffel.model.KniffelZettel;

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
	KniffelZettel createNewGame(Integer playerCount);

	/**
	 * 
	 * @return
	 */
	KniffelZettel refresh();

	/**
	 * 
	 * @return
	 */
	List<Integer> dice();

	/**
	 * 
	 * @param cubeId
	 */
	void lockCube(Integer cubeId);

	/**
	 * 
	 * @param cubeId
	 */
	void unlockCube(Integer cubeId);

	/**
	 * 
	 * @return
	 */
	Integer set1er();

	/**
	 * 
	 * @return
	 */
	Integer set2er();

	/**
	 * 
	 * @return
	 */
	Integer set3er();

	/**
	 * 
	 * @return
	 */
	Integer set4er();

	/**
	 * 
	 * @return
	 */
	Integer set5er();

	/**
	 * 
	 * @return
	 */
	Integer set6er();

	/**
	 * 
	 * @return
	 */
	Integer setDreierPasch();

	/**
	 * 
	 * @return
	 */
	Integer setViererPasch();

	/**
	 * 
	 * @return
	 */
	Integer setFullHouse();

	/**
	 * 
	 * @return
	 */
	Integer setKleineStrasse();

	/**
	 * 
	 * @return
	 */
	Integer setGrosseStrasse();

	/**
	 * 
	 * @return
	 */
	Integer setKniffel();

	/**
	 * 
	 * @return
	 */
	Integer setChance();
}
