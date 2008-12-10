package de.fh_dortmund.cw.kniffel.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.fh_dortmund.cw.kniffel.client.rpc.KniffelService;
import de.fh_dortmund.cw.kniffel.client.rpc.KniffelServiceAsync;
import de.fh_dortmund.cw.kniffel.client.widgets.AktuellerSpielerWidget;
import de.fh_dortmund.cw.kniffel.client.widgets.CubesWidget;
import de.fh_dortmund.cw.kniffel.client.widgets.KniffelZettelWidget;
import de.fh_dortmund.cw.kniffel.client.widgets.SpielerAnzahlWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Kniffel implements EntryPoint {

	/**
	 * AJAX Service
	 */
	private static KniffelServiceAsync kniffelService;

	private static SpielerAnzahlWidget spielerAnzahl;

	private static AktuellerSpielerWidget aktuellerSpieler;

	private static KniffelZettelWidget zettel;

	private static CubesWidget cubes;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {

		// AJAX Service
		kniffelService = (KniffelServiceAsync) GWT.create(KniffelService.class);
		ServiceDefTarget target = (ServiceDefTarget) kniffelService;
		// target.setServiceEntryPoint("/Kniffel");
		// target.setServiceEntryPoint("/de.fh_dortmund.cw.kniffel.Kniffel/Kniffel");

		if (GWT.isScript()) {
			String url = GWT.getModuleBaseURL();
			url += "/Kniffel";
			target.setServiceEntryPoint(url);
		} else {
			target.setServiceEntryPoint("/Kniffel");
		}

		spielerAnzahl = new SpielerAnzahlWidget();
		aktuellerSpieler = new AktuellerSpielerWidget();

		HorizontalPanel oben = new HorizontalPanel();
		oben.add(spielerAnzahl);
		oben.add(aktuellerSpieler);

		zettel = new KniffelZettelWidget();
		cubes = new CubesWidget();

		HorizontalPanel unten = new HorizontalPanel();
		unten.add(zettel);
		unten.add(cubes);

		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(oben);
		vPanel.add(unten);

		RootPanel.get().add(vPanel);
	}

	/**
	 * 
	 * @return
	 */
	public static KniffelServiceAsync getKniffelService() {
		return kniffelService;
	}

	/**
	 * 
	 * @return
	 */
	public static SpielerAnzahlWidget getSpielerAnzahl() {
		return spielerAnzahl;
	}

	/**
	 * 
	 * @return
	 */
	public static AktuellerSpielerWidget getAktuellerSpieler() {
		return aktuellerSpieler;
	}

	/**
	 * 
	 * @return
	 */
	public static KniffelZettelWidget getZettel() {
		return zettel;
	}

	/**
	 * 
	 * @return
	 */
	public static CubesWidget getCubes() {
		return cubes;
	}
}
