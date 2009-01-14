package de.fh_dortmund.cw.kniffel.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;

import de.fh_dortmund.cw.kniffel.client.rpc.KniffelService;
import de.fh_dortmund.cw.kniffel.client.rpc.KniffelServiceAsync;
import de.fh_dortmund.cw.kniffel.client.widgets.WuerfelView;
import de.fh_dortmund.cw.kniffel.client.widgets.KniffelZettelView;
import de.fh_dortmund.cw.kniffel.client.widgets.SpielerView;
import de.fh_dortmund.cw.kniffel.model.KniffelZettel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Kniffel implements EntryPoint {

	/**
	 * AJAX Service
	 */
	private static KniffelServiceAsync kniffelService;

	// HTML Body
	private static RootPanel body = RootPanel.get();

	// Eingabeformular
	private SpielerView spielerView;

	// Kniffelzettel
	private static KniffelZettelView kniffelZettelView;

	//
	private static WuerfelView wuerfelView;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {

		// AJAX Service
		kniffelService = (KniffelServiceAsync) GWT.create(KniffelService.class);
		ServiceDefTarget target = (ServiceDefTarget) kniffelService;

		if (GWT.isScript()) {
			String url = GWT.getModuleBaseURL();
			url += "/Kniffel";
			target.setServiceEntryPoint(url);
		} else {
			target.setServiceEntryPoint("/Kniffel");
		}

		spielerView = new SpielerView();
		body.add(spielerView.getWidget());
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
	 * @param zettel
	 */
	public static void startGame(Integer spielerAnzahl) {
		body.clear();

		kniffelZettelView = new KniffelZettelView(spielerAnzahl);
		body.add(kniffelZettelView.getWidget());

		
		
		
		
		wuerfelView = new WuerfelView(kniffelZettelView);
		body.add(wuerfelView.getWidget());
	}
}
