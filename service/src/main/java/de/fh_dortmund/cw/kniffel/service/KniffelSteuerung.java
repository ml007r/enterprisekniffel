package de.fh_dortmund.cw.kniffel.service;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import de.fh_dortmund.cw.kniffel.exceptions.WuerfelException;
import de.fh_dortmund.cw.kniffel.model.KniffelZeile;
import de.fh_dortmund.cw.kniffel.model.KniffelZettel;
import de.fh_dortmund.cw.kniffel.model.Spieler;
import de.fh_dortmund.cw.kniffel.model.Wuerfel;

/**
 * 
 * @author tbs
 * 
 */
@Local
@Remote
public interface KniffelSteuerung {

	/**
	 * 
	 * @param spielerAnzahl
	 * @return
	 */
	void createNewGame(Integer playerCount);

	/**
	 * 
	 * @return
	 */
	KniffelZettel refresh();

	/**
	 * 
	 * @return
	 * @throws WuerfelException 
	 */
	List<Wuerfel> dice() throws WuerfelException;

	/**
	 * 
	 * @return
	 */
	List<Wuerfel> initDices();

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

	/**
	 * 
	 * @param cell
	 * @param playerId
	 * @return
	 */
	Integer getValue(KniffelZeile cell, Integer playerId);

	/**
	 * 
	 * @return
	 */
	Spieler getAktuellerSpieler();

}
