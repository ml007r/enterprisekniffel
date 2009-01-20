package de.fh_dortmund.cw.kniffel.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

import de.fh_dortmund.cw.kniffel.exceptions.WuerfelException;
import de.fh_dortmund.cw.kniffel.interceptor.LogInterceptor;
import de.fh_dortmund.cw.kniffel.model.KniffelZeile;
import de.fh_dortmund.cw.kniffel.model.KniffelZelle;
import de.fh_dortmund.cw.kniffel.model.KniffelZettel;
import de.fh_dortmund.cw.kniffel.model.Spieler;
import de.fh_dortmund.cw.kniffel.model.Wuerfel;

/**
 * 
 * @author tbs
 * 
 */
@Stateful
@Interceptors( { LogInterceptor.class })
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_dortmund.cw.kniffel.service.KniffelSteuerung#setValue(de.fh_dortmund
	 * .cw.kniffel.model.KniffelZeile)
	 */
	public Integer setValue(KniffelZeile row) {
		Integer result = 0;

		if (row == null) {
			throw new IllegalArgumentException();
		} else if (row == KniffelZeile.ONE) {
			result = wuerfelSteuerung.getCubeSum(1);
		} else if (row == KniffelZeile.TWO) {
			result = wuerfelSteuerung.getCubeSum(2);
		} else if (row == KniffelZeile.THREE) {
			result = wuerfelSteuerung.getCubeSum(3);
		} else if (row == KniffelZeile.FOUR) {
			result = wuerfelSteuerung.getCubeSum(4);
		} else if (row == KniffelZeile.FIVE) {
			result = wuerfelSteuerung.getCubeSum(5);
		} else if (row == KniffelZeile.SIX) {
			result = wuerfelSteuerung.getCubeSum(6);
		} else if (row == KniffelZeile.THREE_OAK) {
			result = calculateThreeOfAKind();
		} else if (row == KniffelZeile.FOUR_OAK) {
			result = calculateFourOfAKind();
		} else if (row == KniffelZeile.FULL_HOUSE) {
			result = calculateFullHouse();
		} else if (row == KniffelZeile.STREET_1) {
			result = calculateStreet1();
		} else if (row == KniffelZeile.STREET_2) {
			result = calculateStreet2();
		} else if (row == KniffelZeile.YAHTZEE) {
			result = calculateKniffel();
		} else if (row == KniffelZeile.CHANCE) {
			result = calculateChance();
		} else {
			throw new IllegalArgumentException();
		}

		setValue(row, result);
		generateSums();
		setNextPlayer();
		return result;
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
	public Integer getValue(KniffelZeile cell, Integer playerId)
			throws Exception {
		for (Spieler s : spiel.getSpieler()) {
			if (s.getId() == playerId) {
				return s.getSpalte().getZelle(cell).getWert();
			}
		}
		throw new Exception();
	}

	/**
	 * 
	 * @param cell
	 * @return
	 */
	private Integer getValue(KniffelZeile cell) {
		KniffelZelle kz = spiel.getAktuellerSpieler().getSpalte()
				.getZelle(cell);
		return kz == null ? 0 : kz.getWert();
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

	// ********************************************************************************************
	// PRIVATE

	/**
	 * Setzt den aktuellen Spieler auf den nächsten Spieler
	 */
	private void setNextPlayer() {
		int curPlayerPos = spiel.getSpieler().indexOf(
				spiel.getAktuellerSpieler());
		int nextPlayerPos = ++curPlayerPos % spiel.getSpieler().size();

		spiel.setAktuellerSpieler(spiel.getSpieler().get(nextPlayerPos));
	}

	/**
	 * 
	 * @param value
	 */
	private void setValue(KniffelZeile zeile, Integer value) {
		spiel.getAktuellerSpieler().getSpalte().getZelle(zeile).setWert(value);
	}

	/**
	 * Summenbildung
	 * 
	 * @throws Exception
	 */
	private void generateSums() {
		try {
			setValue(KniffelZeile.SUM_TOP, getSumTop());
		} catch (Exception e) {
			// nix
		}

		try {
			setValue(KniffelZeile.BONUS_TOP, getBonusTop());
		} catch (Exception e) {
			// nix
		}

		try {
			setValue(KniffelZeile.SUM_TOP_TOTAL, getSumTopTotal());
		} catch (Exception e) {
			// nix
		}

		try {
			setValue(KniffelZeile.SUM_BOTTOM_TOTAL, getSumBottom());
		} catch (Exception e) {
			// nix
		}

		try {
			setValue(KniffelZeile.SUM_TOTAL, getSumTotal());
		} catch (Exception e) {
			// nix
		}
	}

	/**
	 * 
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	private Integer getSumTop() throws Exception {
		return getValue(KniffelZeile.ONE)
				+ getValue(KniffelZeile.TWO)
				+ getValue(KniffelZeile.THREE)
				+ getValue(KniffelZeile.FOUR)
				+ getValue(KniffelZeile.FIVE)
				+ getValue(KniffelZeile.SIX);
	}

	/**
	 * 
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	private Integer getBonusTop( ) throws Exception {
		return getValue(KniffelZeile.SUM_TOP) > 63 ? 35 : 0;
	}

	/**
	 * 
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	private Integer getSumTopTotal() throws Exception {
		return getValue(KniffelZeile.SUM_TOP)
				+ getValue(KniffelZeile.BONUS_TOP);
	}

	/**
	 * 
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	private Integer getSumBottom() throws Exception {
		return getValue(KniffelZeile.THREE_OAK)
				+ getValue(KniffelZeile.FOUR_OAK)
				+ getValue(KniffelZeile.FULL_HOUSE)
				+ getValue(KniffelZeile.STREET_1)
				+ getValue(KniffelZeile.STREET_2)
				+ getValue(KniffelZeile.YAHTZEE)
				+ getValue(KniffelZeile.CHANCE);
	}

	/**
	 * 
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	private Integer getSumTotal() throws Exception {
		return getValue(KniffelZeile.SUM_TOP_TOTAL)
				+ getValue(KniffelZeile.SUM_BOTTOM_TOTAL);
	}

	/**
	 * 
	 * @return
	 */
	private Integer calculateThreeOfAKind() {

		boolean validTOAK = false;

		int[] paschArr = new int[6];
		Integer sum = 0;

		for (int j : wuerfelSteuerung.getAllCubeValues()) {
			paschArr[j - 1]++;
			sum += j;

			// Wenn eine Zahl 3x vorkam, ist der Pasch gültig.
			if (paschArr[j - 1] == 3) {
				validTOAK = true;
			}
		}

		if (validTOAK) {
			return sum;
		}

		return 0;
	}

	/**
	 * 
	 * @return
	 */
	private Integer calculateFourOfAKind() {

		boolean validTOAK = false;

		int[] paschArr = new int[6];
		Integer sum = 0;

		for (int j : wuerfelSteuerung.getAllCubeValues()) {
			paschArr[j - 1]++;
			sum += j;

			// Wenn eine Zahl 3x vorkam, ist der Pasch gültig.
			if (paschArr[j - 1] == 3) {
				validTOAK = true;
			}
		}

		if (validTOAK) {
			return sum;
		}

		return 0;
	}

	/**
	 * 
	 * @return
	 */
	private Integer calculateFullHouse() {
		int[] fullHouseArr = new int[6];

		// Würfelaugen zählen
		for (Integer j : wuerfelSteuerung.getAllCubeValues()) {
			fullHouseArr[j - 1]++;
		}

		boolean valid2er = false;
		boolean valid3er = false;

		// TODO Prüfen...
		for (int i : fullHouseArr) {
			if (i == 2) {
				valid2er = true;
			} else if (i == 3) {
				valid3er = true;
			}
		}

		if (valid2er && valid3er) {
			return 25;
		}

		return 0;
	}

	/**
	 * 
	 * @return
	 */
	private Integer calculateStreet1() {
		int[] smallStreetArr = new int[6];

		// Würfelaugen zählen
		for (Integer j : wuerfelSteuerung.getAllCubeValues()) {
			smallStreetArr[j - 1]++;
		}

		Integer startAt = 0;

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
			return 0;
		}

		boolean validStreet = true;

		for (Integer i = startAt; i < startAt + 4; i++) {
			if (smallStreetArr[i] == 0) {
				validStreet = false;
			}
		}

		if (validStreet) {
			return 30;
		}

		return 0;
	}

	/**
	 * 
	 * @return
	 */
	private Integer calculateStreet2() {
		int[] bigStreetArr = new int[6];

		// Würfelaugen zählen
		for (Integer j : wuerfelSteuerung.getAllCubeValues()) {
			bigStreetArr[j - 1]++;
		}

		Integer startAt = 0;

		// Es wurde keine 1 gewürftelt, Straße beginnt mindestens mit 2
		if (bigStreetArr[0] == 0) {
			startAt = 1;
		}

		// Straße beginnt nicht mit 1 oder 2 => Kein gültige Straße => 0
		// Punkte
		else if (bigStreetArr[1] == 0) {
			return 0;
		}

		boolean validStreet = true;

		for (Integer i = startAt; i < startAt + 5; i++) {
			if (bigStreetArr[i] == 0) {
				validStreet = false;
			}
		}

		if (validStreet) {
			return 40;
		}

		return 0;
	}

	/**
	 * 
	 * @return
	 */
	private Integer calculateKniffel() {
		Integer kniffelNumber = wuerfelSteuerung.getCubeValue(1);

		for (Integer i : wuerfelSteuerung.getAllCubeValues()) {
			if (i != kniffelNumber) {
				return 0;
			}
		}

		return 50;
	}

	/**
	 * 
	 * @return
	 */
	private Integer calculateChance() {
		Integer sum = 0;

		// Alle Augen zählen
		for (Integer i : wuerfelSteuerung.getAllCubeValues()) {
			sum += i;
		}

		return sum;
	}

}
