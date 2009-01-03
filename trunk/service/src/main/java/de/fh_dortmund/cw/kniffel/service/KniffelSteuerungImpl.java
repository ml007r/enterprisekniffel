package de.fh_dortmund.cw.kniffel.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

import de.fh_dortmund.cw.kniffel.exceptions.WuerfelException;
import de.fh_dortmund.cw.kniffel.interceptor.LogInterceptor;
import de.fh_dortmund.cw.kniffel.model.KniffelZeile;
import de.fh_dortmund.cw.kniffel.model.KniffelZettel;
import de.fh_dortmund.cw.kniffel.model.Spieler;
import de.fh_dortmund.cw.kniffel.model.Wuerfel;

@Stateful
@Interceptors( { LogInterceptor.class } )
public class KniffelSteuerungImpl implements KniffelSteuerung {

	@EJB
	WuerfelSteuerung wuerfelSteuerung;

	/**
	 * Das aktuelle Spiel
	 */
	private KniffelZettel spiel;

	/**
	 * 
	 */
	public KniffelSteuerungImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#erstelleNeuesSpiel
	 * (int)
	 */
	public void createNewGame(Integer spielerAnzahl) {
		List<Spieler> spielerList = new ArrayList<Spieler>(spielerAnzahl);

		for (int i = 0; i < spielerAnzahl; i++) {
			spielerList.add(new Spieler(i + 1));
		}

		this.spiel = new KniffelZettel(spielerList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#dice()
	 */
	public List<Wuerfel> dice() throws WuerfelException {
		return wuerfelSteuerung.dice();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#initDices()
	 */
	public List<Wuerfel> initDices() {
		return wuerfelSteuerung.resetTrys();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#lockCubes(int[])
	 */
	public void lockCube(Integer cubeId) {
		wuerfelSteuerung.lockCube(cubeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#unlockCubes(int
	 * [])
	 */
	public void unlockCube(Integer cubeId) {
		wuerfelSteuerung.unlockCube(cubeId);
	}

	/**
	 * Setzt den aktuellen Spieler auf den nächsten Spieler
	 */
	private void setNextPlayer() {
		spiel.setAktuellerSpieler(spiel.getSpieler().get(
				spiel.getSpieler().indexOf(spiel.getAktuellerSpieler()) + 1
						% spiel.getSpieler().size()));
	}

	/**
	 * 
	 * @param value
	 */
	private Integer setValue(KniffelZeile zeile, Integer value) {
		spiel.getAktuellerSpieler().getSpalte().getZelle(zeile).setWert(value);
		setNextPlayer();
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#set1er()
	 */
	public Integer set1er() {
		return setValue(KniffelZeile.ONE, wuerfelSteuerung.getCubeSum(1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#set2er()
	 */
	public Integer set2er() {
		return setValue(KniffelZeile.TWO, wuerfelSteuerung.getCubeSum(2));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#set3er()
	 */
	public Integer set3er() {
		return setValue(KniffelZeile.THREE, wuerfelSteuerung.getCubeSum(3));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#set4er()
	 */
	public Integer set4er() {
		return setValue(KniffelZeile.FOUR, wuerfelSteuerung.getCubeSum(4));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#set5er()
	 */
	public Integer set5er() {
		return setValue(KniffelZeile.FIVE, wuerfelSteuerung.getCubeSum(5));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#set6er()
	 */
	public Integer set6er() {
		return setValue(KniffelZeile.SIX, wuerfelSteuerung.getCubeSum(6));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setChance()
	 */
	public Integer setChance() {
		int sum = 0;

		// Alle Augen zählen
		for (int i : wuerfelSteuerung.getAllCubeValues()) {
			sum += i;
		}

		return setValue(KniffelZeile.CHANCE, sum);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setDreierPasch()
	 */
	public Integer setDreierPasch() {
		int[] paschArr = new int[6];

		for (int j : wuerfelSteuerung.getAllCubeValues()) {
			paschArr[j - 1]++;

			// Wenn eine Zahl 3x vorkam, kann die Summe geschrieben werden
			if (paschArr[j - 1] == 3) {
				return setValue(KniffelZeile.THREE_OAK, j * 3);
			}
		}

		return setValue(KniffelZeile.THREE_OAK, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setViererPasch()
	 */
	public Integer setViererPasch() {
		int[] paschArr = new int[6];

		for (int j : wuerfelSteuerung.getAllCubeValues()) {
			paschArr[j - 1]++;

			// Wenn eine Zahl 3x vorkam, kann die Summe geschrieben werden
			if (paschArr[j - 1] == 4) {
				return setValue(KniffelZeile.FOUR_OAK, j * 4);
			}
		}

		return setValue(KniffelZeile.FOUR_OAK, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setFullHouse()
	 */
	public Integer setFullHouse() {
		int[] fullHouseArr = new int[6];

		// Würfelaugen zählen
		for (int j : wuerfelSteuerung.getAllCubeValues()) {
			fullHouseArr[j - 1]++;
		}

		boolean valid2er = false;
		boolean valid3er = false;

		// TODO Prüfen...
		for (int i : fullHouseArr) {
			if (fullHouseArr[i] == 2) {
				valid2er = true;
			} else if (fullHouseArr[i] == 3) {
				valid3er = true;
			}
		}

		if (valid2er && valid3er) {
			return setValue(KniffelZeile.FULL_HOUSE, 25);
		}

		return setValue(KniffelZeile.FULL_HOUSE, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setKleineStrasse
	 * ()
	 */
	public Integer setKleineStrasse() {
		int[] smallStreetArr = new int[6];

		// Würfelaugen zählen
		for (int j : wuerfelSteuerung.getAllCubeValues()) {
			smallStreetArr[j - 1]++;
		}

		int startAt = 0;

		// Es wurde keine 1 gewürftelt, Straße beginnt mindestens mit 2
		if (smallStreetArr[0] == 0) {
			startAt = 1;
		}

		// Es wurde auch keine 2 gewürfelt, Straße beginnt mindestens mit 3
		else if (smallStreetArr[1] == 0) {
			startAt = 2;
		}

		// Straße beginnt nicht mit 1, 2 oder 3 => Kein gültige Straße => 0
		// Punkte
		else if (smallStreetArr[2] == 0) {
			return setValue(KniffelZeile.STREET_1, 0);
		}

		boolean validStreet = true;

		for (int i = startAt; i < startAt + 4; i++) {
			if (smallStreetArr[i] == 0) {
				validStreet = false;
			}
		}

		if (validStreet) {
			return setValue(KniffelZeile.STREET_1, 30);
		}

		return setValue(KniffelZeile.STREET_1, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setGrosseStrasse
	 * ()
	 */
	public Integer setGrosseStrasse() {
		int[] bigStreetArr = new int[6];

		// Würfelaugen zählen
		for (int j : wuerfelSteuerung.getAllCubeValues()) {
			bigStreetArr[j - 1]++;
		}

		int startAt = 0;

		// Es wurde keine 1 gewürftelt, Straße beginnt mindestens mit 2
		if (bigStreetArr[0] == 0) {
			startAt = 1;
		}

		// Straße beginnt nicht mit 1 oder 2 => Kein gültige Straße => 0
		// Punkte
		else if (bigStreetArr[1] == 0) {
			return setValue(KniffelZeile.STREET_2, 0);
		}

		boolean validStreet = true;

		for (int i = startAt; i < startAt + 5; i++) {
			if (bigStreetArr[i] == 0) {
				validStreet = false;
			}
		}

		if (validStreet) {
			return setValue(KniffelZeile.STREET_2, 40);
		}

		return setValue(KniffelZeile.STREET_2, 0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setKniffel()
	 */
	public Integer setKniffel() {
		int kniffelNumber = wuerfelSteuerung.getCubeValue(1);

		for (int i : wuerfelSteuerung.getAllCubeValues()) {
			if (i != kniffelNumber) {
				return setValue(KniffelZeile.YAHTZEE, 0);
			}
		}

		return setValue(KniffelZeile.YAHTZEE, 50);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#refresh()
	 */
	public KniffelZettel refresh() {
		return this.spiel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_dortmund.cw.kniffel.service.KniffelSteuerung#getValue(de.fh_dortmund
	 * .cw.kniffel.model.KniffelZeile, java.lang.Integer)
	 */
	public Integer getValue(KniffelZeile cell, Integer playerId) {
		for (Spieler s : spiel.getSpieler()) {
			if (s.getId() == playerId) {
				s.getSpalte().getZelle(cell).getWert();
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_dortmund.cw.kniffel.service.KniffelSteuerung#getAktuellerSpieler()
	 */
	public Spieler getAktuellerSpieler() {
		return spiel.getAktuellerSpieler();
	}
}
