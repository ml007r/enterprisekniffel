package de.fh_fortmund.cw.kniffel.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author tbs
 * 
 */
public class KniffelSpalte {

	private Spieler spieler;

	private Map<KniffelZeile, KniffelZelle> zellen;

	public KniffelSpalte(Spieler spieler, Set<KniffelZeile> zeilen) {
		super();
		this.spieler = spieler;
		this.zellen = new HashMap<KniffelZeile, KniffelZelle>();
		generateMap(zeilen);
	}

	protected void generateMap(Set<KniffelZeile> zeilen) {
		for (KniffelZeile kz : zeilen) {
			zellen.put(kz, new KniffelZelle(this, kz));
		}
	}

	public Spieler getSpieler() {
		return spieler;
	}

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}

	public Map<KniffelZeile, KniffelZelle> getZellen() {
		return zellen;
	}

	public void setZellen(Map<KniffelZeile, KniffelZelle> zellen) {
		this.zellen = zellen;
	}

	/**
	 * Liefert eine konkrete Zelle anhand des Zeilennamens.
	 * 
	 * @param zeilenName
	 * @return
	 */
	public KniffelZelle getZelle(String zeilenName) {
		for (KniffelZeile kz : zellen.keySet()) {
			if (kz.getName().equals(zeilenName)) {
				return zellen.get(kz);
			}
		}
		return null;
	}

	/**
	 * Berechnet die Gesamtsumme der Spalte
	 * 
	 * @return
	 */
	public Integer getSpaltenSumme() {
		int summe = 0;
		for (KniffelZeile kz : zellen.keySet()) {
			summe += zellen.get(kz).getWert();
		}
		return new Integer(summe);
	}
}
