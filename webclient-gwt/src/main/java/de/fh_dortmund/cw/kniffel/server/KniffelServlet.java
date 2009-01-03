package de.fh_dortmund.cw.kniffel.server;

import java.util.List;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.fh_dortmund.cw.kniffel.client.rpc.KniffelService;
import de.fh_dortmund.cw.kniffel.exceptions.WuerfelException;
import de.fh_dortmund.cw.kniffel.model.KniffelZeile;
import de.fh_dortmund.cw.kniffel.model.KniffelZettel;
import de.fh_dortmund.cw.kniffel.model.Spieler;
import de.fh_dortmund.cw.kniffel.model.Wuerfel;
import de.fh_dortmund.cw.kniffel.service.KniffelSteuerung;

/**
 * 
 * @author tbs
 * 
 */
@SuppressWarnings("serial")
public class KniffelServlet extends RemoteServiceServlet implements
		KniffelService {

	@EJB
	KniffelSteuerung kniffelSteuerung;

	/**
	 * 
	 */
	public KniffelServlet() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#createNewGame(java
	 * .lang.Integer)
	 */
	public void createNewGame(Integer playerCount) {
		kniffelSteuerung.createNewGame(playerCount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#refresh()
	 */
	public KniffelZettel refresh() {
		return kniffelSteuerung.refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#dice()
	 */
	public List<Wuerfel> dice() throws WuerfelException {
		return kniffelSteuerung.dice();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#lockCube(java.lang
	 * .Integer)
	 */
	public void lockCube(Integer cubeId) {
		kniffelSteuerung.lockCube(cubeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#unlockCube(java.lang
	 * .Integer)
	 */
	public void unlockCube(Integer cubeId) {
		kniffelSteuerung.unlockCube(cubeId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#setValue(de.fh_dortmund
	 * .cw.kniffel.model.KniffelZeile)
	 */
	public Integer setValue(KniffelZeile cell) {
		if (cell == null) {
			throw new IllegalArgumentException();
		} else if (cell == KniffelZeile.ONE) {
			return kniffelSteuerung.set1er();
		} else if (cell == KniffelZeile.TWO) {
			return kniffelSteuerung.set2er();
		} else if (cell == KniffelZeile.THREE) {
			return kniffelSteuerung.set3er();
		} else if (cell == KniffelZeile.FOUR) {
			return kniffelSteuerung.set4er();
		} else if (cell == KniffelZeile.FIVE) {
			return kniffelSteuerung.set5er();
		} else if (cell == KniffelZeile.SIX) {
			return kniffelSteuerung.set6er();
		} else if (cell == KniffelZeile.THREE_OAK) {
			return kniffelSteuerung.setDreierPasch();
		} else if (cell == KniffelZeile.FOUR_OAK) {
			return kniffelSteuerung.setViererPasch();
		} else if (cell == KniffelZeile.FULL_HOUSE) {
			return kniffelSteuerung.setFullHouse();
		} else if (cell == KniffelZeile.STREET_1) {
			return kniffelSteuerung.setKleineStrasse();
		} else if (cell == KniffelZeile.STREET_2) {
			return kniffelSteuerung.setGrosseStrasse();
		} else if (cell == KniffelZeile.YAHTZEE) {
			return kniffelSteuerung.setKniffel();
		} else if (cell == KniffelZeile.CHANCE) {
			return kniffelSteuerung.setChance();
		} else {
			throw new IllegalArgumentException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#initDices()
	 */
	public List<Wuerfel> initDices() {
		return kniffelSteuerung.initDices();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#getValue(de.fh_dortmund
	 * .cw.kniffel.model.KniffelZeile, java.lang.Integer)
	 */
	public Integer getValue(KniffelZeile cell, Integer playerId) {
		return kniffelSteuerung.getValue(cell, playerId);
	}

	public Spieler getAktellerSpieler() {
		return kniffelSteuerung.getAktuellerSpieler();
	}
}
