package de.fh_dortmund.cw.kniffel.model;

import java.io.Serializable;

/**
 * 
 * @author tbs
 * 
 */
public class Spieler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3690412025188387040L;

	private String name;

	private KniffelSpalte spalte;

	public Spieler() {
	}

	public Spieler(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public KniffelSpalte getSpalte() {
		return spalte;
	}

	public void setSpalte(KniffelSpalte spalte) {
		this.spalte = spalte;
	}
}
