package de.fh_dortmund.cw.kniffel.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import de.fh_dortmund.cw.kniffel.exceptions.WuerfelException;
import de.fh_dortmund.cw.kniffel.model.KniffelZeile;
import de.fh_dortmund.cw.kniffel.model.Spieler;
import de.fh_dortmund.cw.kniffel.model.Wuerfel;

/**
 * 
 * @author tbs
 * 
 */
public interface KniffelService extends RemoteService {

	/**
	 * 
	 * @param playerCount
	 * @return
	 */
	void createNewGame(Integer playerCount);

	/**
	 * 
	 * @return
	 * @throws WuerfelException
	 */
	List<Wuerfel> dice() throws WuerfelException;

	/**
	 * 
	 * @return
	 */
	List<Wuerfel> initDices();

	/**
	 * 
	 * @param cubeId
	 */
	void lockCube(Integer cubeId);

	/**
	 * 
	 * @param cubeId
	 */
	void unlockCube(Integer cubeId);

	/**
	 * 
	 * @param cell
	 * @return
	 */
	Integer setValue(KniffelZeile cell);

	/**
	 * 
	 * @param cell
	 * @param playerId
	 * @return
	 */
	Integer getValue(KniffelZeile cell, Integer playerId);

	/**
	 * 
	 * @return
	 */
	Spieler getAktellerSpieler();
}
