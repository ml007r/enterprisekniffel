package de.fh_fortmund.cw.kniffel.model;

/**
 * 
 * @author tbs
 *
 */
public class Wuerfel {

	private Integer wert;

	private boolean gesperrt;

	public Wuerfel() {
	}
	
	public Integer getWert() {
		return wert;
	}

	public void setWert(Integer wert) {
		this.wert = wert;
	}

	public boolean isGesperrt() {
		return gesperrt;
	}

	public void setGesperrt(boolean gesperrt) {
		this.gesperrt = gesperrt;
	}

}
