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

	private Integer id;

	private KniffelSpalte spalte;

	public Spieler() {
	}

	public Spieler(Integer id) {
		super();
		this.id = id;
	}

	public KniffelSpalte getSpalte() {
		return spalte;
	}

	public void setSpalte(KniffelSpalte spalte) {
		this.spalte = spalte;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "<Spieler #" + id + ">";
	}
}
