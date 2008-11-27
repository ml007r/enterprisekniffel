package de.fh_fortmund.cw.kniffel.ejb3.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import de.fh_fortmund.cw.kniffel.model.KniffelZeile;
import de.fh_fortmund.cw.kniffel.model.KniffelZettel;
import de.fh_fortmund.cw.kniffel.model.Spieler;

@Stateful
public class KniffelSteuerungImpl implements KniffelSteuerung {

	/*
	 * private static final Log logger = LogFactory
	 * .getLog(KniffelSteuerungImpl.class);
	 */

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
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#diceAll()
	 */
	public void diceAll() {
		wuerfelSteuerung.diceAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#diceSelected(
	 * int[])
	 */
	public void diceSelected() {
		wuerfelSteuerung.diceSelected();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#lockCubes(int[])
	 */
	public void lockCube(int cube) {
		wuerfelSteuerung.lockCube(cube);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.WuerfelSteuerung#unlockCubes(int
	 * [])
	 */
	public void unlockCube(int cube) {
		wuerfelSteuerung.unlockCube(cube);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#erstelleNeuesSpiel
	 * (int)
	 */
	public KniffelZettel erstelleNeuesSpiel(int spielerAnzahl) {
		List<Spieler> spielerList = new ArrayList<Spieler>(spielerAnzahl);
		this.spiel = new KniffelZettel(spielerList);
		return this.spiel;
	}

	public KniffelZettel getKniffelZettel() {
		return this.spiel;
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
	private void setValue(KniffelZeile zeile, int value) {
		spiel.getAktuellerSpieler().getSpalte().getZelle(zeile).setWert(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#set1er()
	 */
	public void set1er() {
		setValue(KniffelZeile.ONE, wuerfelSteuerung.getCubeSum(1));
		setNextPlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#set2er()
	 */
	public void set2er() {
		setValue(KniffelZeile.TWO, wuerfelSteuerung.getCubeSum(2));
		setNextPlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#set3er()
	 */
	public void set3er() {
		setValue(KniffelZeile.THREE, wuerfelSteuerung.getCubeSum(3));
		setNextPlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#set4er()
	 */
	public void set4er() {
		setValue(KniffelZeile.FOUR, wuerfelSteuerung.getCubeSum(4));
		setNextPlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#set5er()
	 */
	public void set5er() {
		setValue(KniffelZeile.FIVE, wuerfelSteuerung.getCubeSum(5));
		setNextPlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#set6er()
	 */
	public void set6er() {
		setValue(KniffelZeile.SIX, wuerfelSteuerung.getCubeSum(6));
		setNextPlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setChance()
	 */
	public void setChance() {
		int sum = 0;

		// Alle Augen zählen
		for (int i : wuerfelSteuerung.getAllCubeValues()) {
			sum += i;
		}

		setValue(KniffelZeile.CHANCE, sum);
		setNextPlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setDreierPasch()
	 */
	public void setDreierPasch() {
		int[] paschArr = new int[6];
		boolean validPasch = false;

		for (int j : wuerfelSteuerung.getAllCubeValues()) {
			paschArr[j - 1]++;

			// Wenn eine Zahl 3x vorkam, kann die Summe geschrieben werden
			if (paschArr[j - 1] == 3) {
				setValue(KniffelZeile.THREE_OAK, j * 3);
				validPasch = true;
				break;
			}
		}

		if (!validPasch) {
			setValue(KniffelZeile.THREE_OAK, 0);
		}

		setNextPlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setViererPasch()
	 */
	public void setViererPasch() {
		int[] paschArr = new int[6];
		boolean validPasch = false;

		for (int j : wuerfelSteuerung.getAllCubeValues()) {
			paschArr[j - 1]++;

			// Wenn eine Zahl 3x vorkam, kann die Summe geschrieben werden
			if (paschArr[j - 1] == 4) {
				setValue(KniffelZeile.FOUR_OAK, j * 4);
				validPasch = true;
				break;
			}
		}

		if (!validPasch) {
			setValue(KniffelZeile.FOUR_OAK, 0);
		}

		setNextPlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setFullHouse()
	 */
	public void setFullHouse() {
		int[] fullHouseArr = new int[6];

		// Würfelaugen zählen
		for (int j : wuerfelSteuerung.getAllCubeValues()) {
			fullHouseArr[j - 1]++;
		}

		boolean valid2er = false;
		boolean valid3er = false;

		for (int i : fullHouseArr) {
			if (fullHouseArr[i] == 2) {
				valid2er = true;
			} else if (fullHouseArr[i] == 3) {
				valid3er = true;
			}
		}

		if (valid2er && valid3er) {
			setValue(KniffelZeile.FULL_HOUSE, 25);
		}

		setNextPlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setKleineStrasse
	 * ()
	 */
	public void setKleineStrasse() {
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
			setValue(KniffelZeile.STREET_1, 0);
			setNextPlayer();
			return;
		}

		boolean validStreet = true;

		for (int i = startAt; i < startAt + 4; i++) {
			if (smallStreetArr[i] == 0) {
				validStreet = false;
			}
		}

		if (validStreet) {
			setValue(KniffelZeile.STREET_1, 30);
		} else {
			setValue(KniffelZeile.STREET_1, 0);
		}

		setNextPlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setGrosseStrasse
	 * ()
	 */
	public void setGrosseStrasse() {
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
			setValue(KniffelZeile.STREET_2, 0);
			setNextPlayer();
			return;
		}

		boolean validStreet = true;

		for (int i = startAt; i < startAt + 5; i++) {
			if (bigStreetArr[i] == 0) {
				validStreet = false;
			}
		}

		if (validStreet) {
			setValue(KniffelZeile.STREET_2, 40);
		} else {
			setValue(KniffelZeile.STREET_2, 0);
		}

		setNextPlayer();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung#setKniffel()
	 */
	public void setKniffel() {
		boolean validKniffel = true;
		int kniffelNumber = wuerfelSteuerung.getCubeValue(0);

		for (int i : wuerfelSteuerung.getAllCubeValues()) {
			if (i != kniffelNumber) {
				validKniffel = false;
				break;
			}
		}

		if (validKniffel) {
			setValue(KniffelZeile.YAHTZEE, 50);
		} else {
			setValue(KniffelZeile.YAHTZEE, 0);
		}

		setNextPlayer();
	}
}
