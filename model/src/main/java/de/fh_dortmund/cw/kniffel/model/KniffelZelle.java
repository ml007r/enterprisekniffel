package de.fh_dortmund.cw.kniffel.model;

import java.io.Serializable;

/**
 * 
 * 
 * @author tbs
 * 
 */
public class KniffelZelle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6487543873813983784L;

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
