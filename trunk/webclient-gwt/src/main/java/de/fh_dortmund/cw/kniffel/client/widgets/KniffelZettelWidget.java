package de.fh_dortmund.cw.kniffel.client.widgets;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;

/**
 * 
 * @author tbs
 * 
 */
public class KniffelZettelWidget extends FlexTable {

	/**
	 * Matrix der Werte
	 */
	private Integer[][] matrix;

	public KniffelZettelWidget() {

		// Allgemeine Einstellungen
		this.setBorderWidth(1);
		initRowNames();

	}

	/**
	 * Fügt eine neue Spalte rechts an
	 */
	public void addPlayer() {

	}

	/**
	 * Entfernt eine Spalte rechts
	 */
	public void removePlayer() {

	}

	/**
	 * 
	 */
	protected void initRowNames() {
		int i = 0;
		this.setWidget(i++, 1, new Label("")); // Zeile der Spielernamen
		this.setWidget(i++, 1, new Label("1 er"));
		this.setWidget(i++, 1, new Label("2 er"));
		this.setWidget(i++, 1, new Label("3 er"));
		this.setWidget(i++, 1, new Label("4 er"));
		this.setWidget(i++, 1, new Label("5 er"));
		this.setWidget(i++, 1, new Label("6 er"));
		this.setWidget(i++, 1, new Label("Summe (oben)"));
		this.setWidget(i++, 1, new Label("Bonus"));
		this.setWidget(i++, 1, new Label("Gesamtsumme (oben)"));
		this.setWidget(i++, 1, new Label("")); // Grenze oben <-> unten
		this.setWidget(i++, 1, new Label("Dreierpasch"));
		this.setWidget(i++, 1, new Label("Viererpasch"));
		this.setWidget(i++, 1, new Label("Full House"));
		this.setWidget(i++, 1, new Label("Kleine Straße"));
		this.setWidget(i++, 1, new Label("Große Straße"));
		this.setWidget(i++, 1, new Label("Kniffel"));
		this.setWidget(i++, 1, new Label("Chance"));
		this.setWidget(i++, 1, new Label("Summe (unten)"));
		this.setWidget(i++, 1, new Label("")); // Grenze oben <-> unten
		this.setWidget(i++, 1, new Label("Summe (oben)"));
		this.setWidget(i++, 1, new Label("Gesamtsumme"));
	}
}
