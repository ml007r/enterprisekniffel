package de.fh_dortmund.cw.kniffel.server;

import java.util.List;

import javax.ejb.EJB;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.fh_dortmund.cw.kniffel.client.rpc.KniffelService;
import de.fh_dortmund.cw.kniffel.model.KniffelZettel;
import de.fh_dortmund.cw.kniffel.model.Wuerfel;
import de.fh_fortmund.cw.kniffel.ejb3.service.KniffelSteuerung;

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
	public KniffelZettel createNewGame(Integer playerCount) {
		return kniffelSteuerung.createNewGame(playerCount);
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
	public List<Wuerfel> dice() {
		return null; // kniffelSteuerung.dice();
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
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#set1er()
	 */
	public Integer set1er() {
		return kniffelSteuerung.set1er();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#set2er()
	 */
	public Integer set2er() {
		return kniffelSteuerung.set2er();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#set3er()
	 */
	public Integer set3er() {
		return kniffelSteuerung.set3er();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#set4er()
	 */
	public Integer set4er() {
		return kniffelSteuerung.set4er();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#set5er()
	 */
	public Integer set5er() {
		return kniffelSteuerung.set5er();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#set6er()
	 */
	public Integer set6er() {
		return kniffelSteuerung.set6er();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#setDreierPasch()
	 */
	public Integer setDreierPasch() {
		return kniffelSteuerung.setDreierPasch();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#setViererPasch()
	 */
	public Integer setViererPasch() {
		return kniffelSteuerung.setViererPasch();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#setFullHouse()
	 */
	public Integer setFullHouse() {
		return kniffelSteuerung.setFullHouse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#setKleineStrasse()
	 */
	public Integer setKleineStrasse() {
		return kniffelSteuerung.setKleineStrasse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#setGrosseStrasse()
	 */
	public Integer setGrosseStrasse() {
		return kniffelSteuerung.setGrosseStrasse();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#setKniffel()
	 */
	public Integer setKniffel() {
		return kniffelSteuerung.setKniffel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fh_dortmund.cw.kniffel.client.rpc.KniffelService#setChance()
	 */
	public Integer setChance() {
		return kniffelSteuerung.setChance();
	}

}
