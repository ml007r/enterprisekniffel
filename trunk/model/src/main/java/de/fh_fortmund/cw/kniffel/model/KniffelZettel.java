package de.fh_fortmund.cw.kniffel.model;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author tbs
 * 
 */
public class KniffelZettel {

	private Date spielBeginn;

	private List<Spieler> spieler;

	private Spieler aktuellerSpieler;

	/**
	 * Die Spieleranzahl ist immer wichtig, daher ist der leere Konstruktor
	 * verboten.
	 */
	protected KniffelZettel() {
	}

	/**
	 * 
	 * @param spielerAnzahl
	 */
	public KniffelZettel(List<Spieler> spieler) {
		this.spielBeginn = new Date();
		this.spieler = spieler;
		this.aktuellerSpieler = spieler.get(0);
	}

	public Date getSpielBeginn() {
		return spielBeginn;
	}

	protected void setSpielBeginn(Date spielBeginn) {
		this.spielBeginn = spielBeginn;
	}

	public List<Spieler> getSpieler() {
		return spieler;
	}

	protected void setSpieler(List<Spieler> spieler) {
		this.spieler = spieler;
	}

	public Spieler getAktuellerSpieler() {
		return aktuellerSpieler;
	}

	public void setAktuellerSpieler(Spieler aktuellerSpieler) {
		this.aktuellerSpieler = aktuellerSpieler;
	}

}
