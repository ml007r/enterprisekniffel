package de.fh_dortmund.cw.kniffel.client.widgets;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;

import de.fh_dortmund.cw.kniffel.client.Kniffel;
import de.fh_dortmund.cw.kniffel.exceptions.WuerfelException;
import de.fh_dortmund.cw.kniffel.model.Wuerfel;

/**
 * 
 * @author tbs
 * 
 */
public class WuerfelView {

	// 1 Zeile: 5 Würfel + 1 Button
	private Grid widget = new Grid(1, 6);

	private List<SingleCubeView> singleCubeViewList;

	private Button button;

	/**
	 * 
	 * @param zettel
	 */
	public WuerfelView(KniffelZettelView zettel) {
		zettel.registerWuerfelView(this);

		reinitCubes();
		initButton();
	}

	/**
	 * Initiales Wuerfeln der Wuerfel
	 * 
	 */
	public void reinitCubes() {
		singleCubeViewList = new ArrayList<SingleCubeView>(5);

		Kniffel.getKniffelService().initDices(new AsyncCallback<List>() {
			public void onFailure(Throwable arg0) {
				Window.alert("foo");
			}

			public void onSuccess(List arg0) {
				refreshCubes((List<Wuerfel>) arg0);
			}
		});
	}

	/**
	 * 
	 */
	protected void initButton() {
		button = new Button("Würfeln!");
		button.addClickListener(new ClickListener() {
			public void onClick(Widget arg0) {
				Kniffel.getKniffelService().dice(new AsyncCallback<List>() {
					public void onFailure(Throwable arg0) {
						// 3 Versuche sind um
						if (arg0 instanceof WuerfelException) {
							button.setEnabled(false);
							Window.alert("Keine weiteren Versuche mehr!");
						}
					}

					public void onSuccess(List arg0) {
						refreshCubes((List<Wuerfel>) arg0);
					}
				});
			}
		});
		widget.setWidget(0, 5, button);
	}

	/**
	 * 
	 * @param wuerfelList
	 */
	protected void refreshCubes(List<Wuerfel> wuerfelList) {
		singleCubeViewList.clear();
		for (Wuerfel w : wuerfelList) {
			SingleCubeView scv = new SingleCubeView(w);
			singleCubeViewList.add(scv);
			widget.setWidget(0, (w.getId() - 1), scv.getImage());
		}
	}

	/**
	 * 
	 * @return
	 */
	public Grid getWidget() {
		return widget;
	}
}
