package de.fh_fortmund.cw.kniffel.ejb3.service;

/**
 * Braucht kein [at]Remote, da der Zugriff nur innerhalb des Containers von der
 * Spielsteuerung zulässig ist.
 * 
 * @author tbs
 * 
 */
public interface WuerfelSteuerung {

	/**
	 * 
	 * @param cube
	 * @return
	 */
	public int getCubeValue(int cube);

	/**
	 * 
	 * @return
	 */
	public int[] getAllCubeValues();

	/**
	 * Würfelt alle Würfel.
	 */
	public void diceAll();

	/**
	 * Würfelt die markierten Würfel
	 * 
	 * @param cubes
	 */
	public void diceSelected();

	/**
	 * Sperrt die markierten Würfel.
	 * 
	 * @param cube
	 */
	public void lockCube(int cube);

	/**
	 * 
	 * @param cube
	 */
	public void unlockCube(int cube);

}
