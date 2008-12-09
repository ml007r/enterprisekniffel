package de.fh_dortmund.cw.kniffel.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;

import de.fh_fortmund.cw.kniffel.model.KniffelZettel;

/**
 * 
 * @author tbs
 * 
 */
public interface KniffelService extends RemoteService {

	//KniffelZettel erstelleNeuesSpiel(int spielerAnzahl);

	void diceAll();

	void diceSelected();

	void lockCube(int cube);

	void unlockCube(int cube);

	void set1er();

	void set2er();

	void set3er();

	void set4er();

	void set5er();

	void set6er();

	void setDreierPasch();

	void setViererPasch();

	void setFullHouse();

	void setKleineStrasse();

	void setGrosseStrasse();

	void setKniffel();

	void setChance();

	//KniffelZettel getKniffelZettel();
}
