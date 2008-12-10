package de.fh_dortmund.cw.kniffel.client.widgets;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * 
 * @author tbs
 * 
 */
public class AktuellerSpielerWidget extends HorizontalPanel {

	private Label aktuellerSpieler = new Label();

	public AktuellerSpielerWidget() {
		this.add(new Label("Aktueller Spieler: "));
		this.add(aktuellerSpieler);
	}

	public void setAktuellerSpieler(String aktuellerSpieler) {
		this.aktuellerSpieler.setText(aktuellerSpieler);
	}
}
