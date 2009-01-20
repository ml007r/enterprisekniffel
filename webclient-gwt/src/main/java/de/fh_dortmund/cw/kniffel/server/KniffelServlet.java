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
	public Integer setValue(KniffelZeile row) {
		return kniffelSteuerung.setValue(row);
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
	public Integer getValue(KniffelZeile cell, Integer playerId)
			throws Exception {
		return kniffelSteuerung.getValue(cell, playerId);
	}

	public Spieler getAktellerSpieler() {
		return kniffelSteuerung.getAktuellerSpieler();
	}
}
