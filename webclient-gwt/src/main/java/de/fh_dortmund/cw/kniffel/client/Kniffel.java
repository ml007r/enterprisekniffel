package de.fh_dortmund.cw.kniffel.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;

import de.fh_dortmund.cw.kniffel.client.rpc.KniffelService;
import de.fh_dortmund.cw.kniffel.client.rpc.KniffelServiceAsync;
import de.fh_dortmund.cw.kniffel.client.widgets.SpielerAnzahlWidget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Kniffel implements EntryPoint {

	/**
	 * AJAX Service
	 */
	private static KniffelServiceAsync kniffelService;

	private static RootPanel spielerPanel = RootPanel.get("spielerPanel");

	private static RootPanel spielPanel = RootPanel.get("spielPanel");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	public void onModuleLoad() {

		// AJAX Service
		kniffelService = (KniffelServiceAsync) GWT.create(KniffelService.class);
		ServiceDefTarget target = (ServiceDefTarget) kniffelService;
		// target.setServiceEntryPoint("/de.fh_dortmund.cw.kniffel.Kniffel/Kniffel");
		target.setServiceEntryPoint("/Kniffel");

		spielerPanel.add(new SpielerAnzahlWidget());
		spielerPanel.setVisible(true);
	}

	/**
	 * 
	 * @return
	 */
	public static KniffelServiceAsync getKniffelServiceAsync() {
		return kniffelService;
	}

	/**
	 * 
	 */
	public void showKniffelZettel() {

	}
}
