package de.fh_fortmund.cw.kniffel.ejb3.service;

import javax.ejb.Stateful;
import java.util.Random;

import de.fh_fortmund.cw.kniffel.model.Wuerfel;

@Stateful
public class WuerfelSteuerungImpl implements WuerfelSteuerung {

	/**
	 * Wuerfel im Spiel (Final!)
	 */
	private final Wuerfel[] wuerfel = new Wuerfel[5];
	
	
	public WuerfelSteuerungImpl() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#dice()
	 */
	public int dice() {
		double value = 0;
		
		do
		{
			value = Math.random()*10;
			System.out.println(value);
		}
		while(value < 0.5 || value > 6.5);
		
		value = Math.round(value);
		System.out.println(value);
		
		return (int) value;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#diceAll()
	 */
	public void diceAll() {
		for(int a=0; a<=4; a++){
			wuerfel[a].setWert(dice());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#diceSelected(
	 * int[])
	 */
	public void diceSelected() {
		for(int a=0; a<=4; a++){
			if(!wuerfel[a].isGesperrt())
				wuerfel[a].setWert(dice());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#getAllCubeValues
	 * ()
	 */
	public int[] getAllCubeValues() {
		int[] CubeValues = new int[5];
		
		for(int a=0; a<=4; a++){
			CubeValues[a] = wuerfel[a].getWert();
		}
		
		return CubeValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#getCubeValue(int)
	 */
	public int getCubeValue(int cube) {
		return wuerfel[cube].getWert();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#lockCubes(int[])
	 */
	public void lockCube(int cube) {
		wuerfel[cube].setGesperrt(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#unlockCubes(int
	 * [])
	 */
	public void unlockCube(int cube) {
		wuerfel[cube].setGesperrt(false);
	}

}
