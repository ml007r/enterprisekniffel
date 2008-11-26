package de.fh_fortmund.cw.kniffel.model;

/**
 * 
 * @author tbs
 * 
 */
public class Spieler {

	private String name;

	private KniffelSpalte spalte;

	protected Spieler() {
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
