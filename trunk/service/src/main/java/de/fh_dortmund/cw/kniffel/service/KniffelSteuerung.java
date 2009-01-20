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
	 * @param row
	 * @return
	 */
	Integer setValue(KniffelZeile row);

	/**
	 * 
	 * @param cell
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	Integer getValue(KniffelZeile cell, Integer playerId) throws Exception;

	/**
	 * 
	 * @return
	 */
	Spieler getAktuellerSpieler();

}
