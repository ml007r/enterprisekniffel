package de.fh_fortmund.cw.kniffel.model;

/**
 * 
 * @author tbs
 * 
 */
public class KniffelZeile {

	private String name;

	/**
	 * Zeilen brauchen immer eine Bezeichnung, daher ist der leere Konstruktor
	 * verboten.
	 */
	protected KniffelZeile() {
	}

	public KniffelZeile(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
