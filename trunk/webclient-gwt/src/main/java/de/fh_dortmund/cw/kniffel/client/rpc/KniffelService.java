package de.fh_dortmund.cw.kniffel.client.rpc;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * 
 * @author tbs
 * 
 */
public interface KniffelService extends RemoteService {

	void erstelleNeuesSpiel(int spielerAnzahl);

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

	Integer[][] getKniffelZettel();
}
