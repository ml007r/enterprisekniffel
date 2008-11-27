package de.fh_fortmund.cw.kniffel.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author tbs
 * 
 */
public class KniffelSpalte {

	private Spieler spieler;

	private Map<KniffelZeile, KniffelZelle> zellen;

	public KniffelSpalte(Spieler spieler) {
		this.spieler = spieler;
		this.zellen = new HashMap<KniffelZeile, KniffelZelle>();
		generateMap();
	}

	public Spieler getSpieler() {
		return spieler;
	}

	public KniffelZelle getZelle(KniffelZeile zeile) {
		return zellen.get(zeile);
	}

	protected void generateMap() {
		for (KniffelZeile kz : KniffelZeile.values())
			zellen.put(kz, new KniffelZelle(this, kz));
	}
}
