package de.fh_dortmund.cw.kniffel.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

import de.fh_dortmund.cw.kniffel.model.KniffelZettel;
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
	KniffelZettel createNewGame(Integer playerCount);

	/**
	 * 
	 * @return
	 */
	List<Wuerfel> dice();

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
	 * @return
	 */
	Integer set1er();

	/**
	 * 
	 * @return
	 */
	Integer set2er();

	/**
	 * 
	 * @return
	 */
	Integer set3er();

	/**
	 * 
	 * @return
	 */
	Integer set4er();

	/**
	 * 
	 * @return
	 */
	Integer set5er();

	/**
	 * 
	 * @return
	 */
	Integer set6er();

	/**
	 * 
	 * @return
	 */
	Integer setDreierPasch();

	/**
	 * 
	 * @return
	 */
	Integer setViererPasch();

	/**
	 * 
	 * @return
	 */
	Integer setFullHouse();

	/**
	 * 
	 * @return
	 */
	Integer setKleineStrasse();

	/**
	 * 
	 * @return
	 */
	Integer setGrosseStrasse();

	/**
	 * 
	 * @return
	 */
	Integer setKniffel();

	/**
	 * 
	 * @return
	 */
	Integer setChance();
}
