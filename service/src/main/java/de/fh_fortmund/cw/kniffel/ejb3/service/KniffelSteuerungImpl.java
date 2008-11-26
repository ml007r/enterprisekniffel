package de.fh_fortmund.cw.kniffel.ejb3.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;

import de.fh_fortmund.cw.kniffel.model.KniffelZettel;
import de.fh_fortmund.cw.kniffel.model.Spieler;

@Stateful
public class KniffelSteuerungImpl implements KniffelSteuerung {

	/*
	 * private static final Log logger = LogFactory
	 * .getLog(KniffelSteuerungImpl.class);
	 */

	/**
	 * Das aktuelle Spiel
	 */
	private KniffelZettel spiel;

	public KniffelZettel erstelleNeuesSpiel(int spielerAnzahl) {
		List<Spieler> spielerList = new ArrayList<Spieler>(spielerAnzahl);
		this.spiel = new KniffelZettel(spielerList);
		return this.spiel;
	}

	public KniffelZettel getKniffelZettel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void set1er() {
		// TODO Auto-generated method stub

	}

	public void set2er() {
		// TODO Auto-generated method stub

	}

	public void set3er() {
		// TODO Auto-generated method stub

	}

	public void set4er() {
		// TODO Auto-generated method stub

	}

	public void set5er() {
		// TODO Auto-generated method stub

	}

	public void set6er() {
		// TODO Auto-generated method stub

	}

	public void setChance() {
		// TODO Auto-generated method stub

	}

	public void setDreierPasch() {
		// TODO Auto-generated method stub

	}

	public void setFullHouse() {
		// TODO Auto-generated method stub

	}

	public void setGrosseStrasse() {
		// TODO Auto-generated method stub

	}

	public void setKleineStrasse() {
		// TODO Auto-generated method stub

	}

	public void setKniffel() {
		// TODO Auto-generated method stub

	}

	public void setViererPasch() {
		// TODO Auto-generated method stub

	}

}
