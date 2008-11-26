package de.fh_fortmund.cw.kniffel.ejb3.service;

import javax.ejb.Stateful;

import de.fh_fortmund.cw.kniffel.model.Wuerfel;

@Stateful
public class WuerfelSteuerungImpl implements WuerfelSteuerung{

	/**
	 * Wuerfel im Spiel (Final!)
	 */
	private final Wuerfel[] wuerfel = new Wuerfel[5];
	
	public WuerfelSteuerungImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#diceAll()
	 */
	public void diceAll() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#diceSelected(int[])
	 */
	public void diceSelected() {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#getAllCubeValues()
	 */
	public int[] getAllCubeValues() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#getCubeValue(int)
	 */
	public int getCubeValue(int cube) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#lockCubes(int[])
	 */
	public void lockCube(int cube) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * (non-Javadoc)
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#unlockCubes(int[])
	 */
	public void unlockCube(int cube) {
		// TODO Auto-generated method stub
		
	}

}
