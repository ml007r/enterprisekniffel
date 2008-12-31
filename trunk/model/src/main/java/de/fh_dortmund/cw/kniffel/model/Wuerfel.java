package de.fh_dortmund.cw.kniffel.model;

import java.io.Serializable;

/**
 * 
 * @author tbs
 * 
 */
public class Wuerfel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2035106195448096327L;

	private Integer id;

	private Integer wert;

	private boolean gesperrt;

	// leerer konstruktor muss für gwt vorhanden sein :(
	public Wuerfel() {
		// TODO Auto-generated constructor stub
	}
	
	public Wuerfel(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
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
