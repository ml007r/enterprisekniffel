package de.fh_fortmund.cw.kniffel.ejb3.service;

/**
 * Braucht kein [at]Remote, da der Zugriff nur innerhalb des Containers von der
 * Spielsteuerung zul�ssig ist.
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
	 * W�rfelt alle W�rfel.
	 */
	public void diceAll();

	/**
	 * W�rfelt die markierten W�rfel
	 * 
	 * @param cubes
	 */
	public void diceSelected();

	/**
	 * Sperrt die markierten W�rfel.
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
