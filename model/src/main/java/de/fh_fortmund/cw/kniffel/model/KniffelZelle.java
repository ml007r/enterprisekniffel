package de.fh_fortmund.cw.kniffel.model;

/**
 * 
 * 
 * @author tbs
 * 
 */
public class KniffelZelle {

	private Integer wert;

	private KniffelSpalte spalte;

	private KniffelZeile zeile;

	protected KniffelZelle() {
		super();
	}

	public KniffelZelle(KniffelSpalte spalte, KniffelZeile zeile) {
		super();
		this.spalte = spalte;
		this.zeile = zeile;
	}

	public Integer getWert() {
		return wert;
	}

	public void setWert(Integer wert) {
		this.wert = wert;
	}

	public boolean isGesperrt() {
		return wert != null;
	}

	public KniffelSpalte getSpalte() {
		return spalte;
	}

	public void setSpalte(KniffelSpalte spalte) {
		this.spalte = spalte;
	}

	public KniffelZeile getZeile() {
		return zeile;
	}

	public void setZeile(KniffelZeile zeile) {
		this.zeile = zeile;
	}
}
