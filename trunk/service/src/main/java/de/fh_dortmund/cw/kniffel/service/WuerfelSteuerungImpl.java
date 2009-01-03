package de.fh_dortmund.cw.kniffel.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

import de.fh_dortmund.cw.kniffel.exceptions.WuerfelException;
import de.fh_dortmund.cw.kniffel.interceptor.LogInterceptor;
import de.fh_dortmund.cw.kniffel.model.Wuerfel;

@Stateful
@Interceptors( { LogInterceptor.class } )
public class WuerfelSteuerungImpl implements WuerfelSteuerung {

	/**
	 * Wuerfel im Spiel (Final!)
	 */
	private final List<Wuerfel> wuerfelList = new ArrayList<Wuerfel>(5);

	/**
	 * Anzahl der Versuche/Durchgang
	 */
	private Integer versuche = new Integer(0);

	/**
	 * 
	 */
	public WuerfelSteuerungImpl() {
		wuerfelList.add(new Wuerfel(1));
		wuerfelList.add(new Wuerfel(2));
		wuerfelList.add(new Wuerfel(3));
		wuerfelList.add(new Wuerfel(4));
		wuerfelList.add(new Wuerfel(5));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#dice()
	 */
	public List<Wuerfel> dice() throws WuerfelException {
		// Drei Versuche (0, 1, 2)
		if (versuche++ == 2) {
			throw new WuerfelException();
		}

		for (Wuerfel w : wuerfelList) {
			if (!w.isGesperrt()) {
				w.setWert((int) ((Math.round(((11 * Math.random() + 37 * Math
						.random()) * 10000)) % 6) + 1));
			}
		}

		return wuerfelList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#getCubeValue(
	 * java.lang.Integer)
	 */
	public Integer getCubeValue(Integer cubeId) {
		return wuerfelList.get(cubeId - 1).getWert();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#getAllCubeValues
	 * ()
	 */
	public Integer[] getAllCubeValues() {
		Integer[] result = new Integer[5];

		for (Wuerfel w : wuerfelList) {
			result[w.getId() - 1] = w.getWert();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#lockCube(java
	 * .lang.Integer)
	 */
	public void lockCube(Integer cubeId) {
		wuerfelList.get(cubeId - 1).setGesperrt(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#unlockCube(java
	 * .lang.Integer)
	 */
	public void unlockCube(Integer cubeId) {
		wuerfelList.get(cubeId - 1).setGesperrt(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#getCubeSum(int)
	 */
	public Integer getCubeSum(Integer searchFor) {
		Integer sum = 4;

//		for (Wuerfel w : wuerfelList) {
//			sum += w.getWert() == searchFor ? w.getWert() : 0;
//		}

		return sum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#getTrys()
	 */
	public Integer getTrys() {
		return versuche;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#resetTrys()
	 */
	public List<Wuerfel> resetTrys() {
		List<Wuerfel> result = null;

		try {
			result = dice();
		} catch (WuerfelException e) {
			// sollte niemals auftreten
			versuche = new Integer(0);
			return resetTrys();
		}

		versuche = new Integer(0);
		return result;
	}

}
