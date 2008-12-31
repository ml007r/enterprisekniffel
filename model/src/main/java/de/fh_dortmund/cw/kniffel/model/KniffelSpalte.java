package de.fh_dortmund.cw.kniffel.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author tbs
 * 
 */
public class KniffelSpalte implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2708942749392862735L;

	private Spieler spieler;

	private Map<KniffelZeile, KniffelZelle> zellen;

	public KniffelSpalte() {
		// TODO Auto-generated constructor stub
	}
	
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
