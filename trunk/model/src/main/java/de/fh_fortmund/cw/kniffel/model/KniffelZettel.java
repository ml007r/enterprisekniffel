package de.fh_fortmund.cw.kniffel.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author tbs
 * 
 */
public class KniffelZettel {

	private Date spielBeginn;

	private Map<Spieler, KniffelSpalte> spielerMap;

	private List<Spieler> spieler;

	private Spieler aktuellerSpieler;

	/**
	 * Die Spieleranzahl ist immer wichtig, daher ist der leere Konstruktor
	 * verboten.
	 */
	protected KniffelZettel() {
		this.spielBeginn = new Date();
		this.spielerMap = new HashMap<Spieler, KniffelSpalte>();
	}

	/**
	 * 
	 * @param spielerAnzahl
	 */
	public KniffelZettel(List<Spieler> spielerList) {
		this.aktuellerSpieler = spielerList.get(0);
		this.spieler = spielerList;
		for (Spieler s : spielerList) {
			spielerMap.put(s, new KniffelSpalte(s));
		}
	}

	public Date getSpielBeginn() {
		return spielBeginn;
	}

	public List<Spieler> getSpieler() {
		return spieler;
	}

	public Spieler getAktuellerSpieler() {
		return aktuellerSpieler;
	}

	public void setAktuellerSpieler(Spieler aktuellerSpieler) {
		this.aktuellerSpieler = aktuellerSpieler;
	}
}
