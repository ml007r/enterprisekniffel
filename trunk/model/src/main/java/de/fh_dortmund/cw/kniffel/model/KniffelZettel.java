package de.fh_dortmund.cw.kniffel.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author tbs
 * 
 */
public class KniffelZettel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5398371425538431922L;

	private Date spielBeginn;

	private Map<Spieler, KniffelSpalte> spielerMap;

	private List<Spieler> spieler;

	private Spieler aktuellerSpieler;

	/**
	 * Die Spieleranzahl ist immer wichtig, daher ist der leere Konstruktor
	 * verboten.
	 * 
	 * für gwt muss ein leerer vorhanden sein :(
	 */
	public KniffelZettel() {
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
