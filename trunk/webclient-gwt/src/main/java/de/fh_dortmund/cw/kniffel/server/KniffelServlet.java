package de.fh_dortmund.cw.kniffel.server;

import java.util.List;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.fh_dortmund.cw.kniffel.client.rpc.KniffelService;
import de.fh_dortmund.cw.kniffel.exceptions.WuerfelException;
import de.fh_dortmund.cw.kniffel.model.KniffelZeile;
import de.fh_dortmund.cw.kniffel.model.KniffelZettel;
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
		}

		switch (cell.ordinal()) {
		case 1:
			return kniffelSteuerung.set1er();
		case 2:
			return kniffelSteuerung.set2er();
		case 3:
			return kniffelSteuerung.set3er();
		case 4:
			return kniffelSteuerung.set4er();
		case 5:
			return kniffelSteuerung.set5er();
		case 6:
			return kniffelSteuerung.set6er();
		case 7:
			return kniffelSteuerung.setDreierPasch();
		case 8:
			return kniffelSteuerung.setViererPasch();
		case 9:
			return kniffelSteuerung.setFullHouse();
		case 10:
			return kniffelSteuerung.setKleineStrasse();
		case 11:
			return kniffelSteuerung.setGrosseStrasse();
		case 12:
			return kniffelSteuerung.setChance();
		default:
			break;
		}

		return null;
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

	public Integer getAktellerSpieler() {
		return kniffelSteuerung.getAktuellerSpieler();
	}
}
