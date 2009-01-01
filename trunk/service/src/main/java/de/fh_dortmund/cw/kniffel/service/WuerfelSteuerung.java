package de.fh_dortmund.cw.kniffel.service;

import java.util.List;

import de.fh_dortmund.cw.kniffel.exceptions.WuerfelException;
import de.fh_dortmund.cw.kniffel.model.Wuerfel;

/**
 * Braucht kein [at]Remote, da der Zugriff nur innerhalb des Containers von der
 * Spielsteuerung zul�ssig ist.
 * 
 * @author tbs
 * 
 */
public interface WuerfelSteuerung {

	/**
	 * Würfelt die Würfel. Gesperrte Würfel werden nicht gewürfelt.
	 * 
	 * @return
	 * @throws WuerfelException 
	 */
	List<Wuerfel> dice() throws WuerfelException;
	
	/**
	 * 
	 * @param cube
	 * @return
	 */
	Integer getCubeValue(Integer cubeId);
	
	/**
	 * 
	 * @return
	 */
	Integer[] getAllCubeValues();

	/**
	 * Summiert alle Würfel mit der Suchzahl
	 * 
	 * searchFor = 1 (Summiere alle 1er)
	 * 
	 * @param searchFor
	 * @return
	 */
	Integer getCubeSum(Integer searchFor);

	/**
	 * Sperrt die markierten W�rfel.
	 * 
	 * @param cube
	 */
	void lockCube(Integer cubeId);

	/**
	 * 
	 * @param cube
	 */
	void unlockCube(Integer cubeId);

	/**
	 * Setzt die Anzahl der Wuerfelversuche beim Spielerwechsel zurück
	 */
	List<Wuerfel> resetTrys();

	/**
	 * Gibt die Anzahl der Versuche zurück.
	 * 
	 * @return
	 */
	Integer getTrys();
}
