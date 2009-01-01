package de.fh_dortmund.cw.kniffel.client.widgets;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

import de.fh_dortmund.cw.kniffel.client.Kniffel;
import de.fh_dortmund.cw.kniffel.model.Wuerfel;

/**
 * 
 * @author tbs
 * 
 */
public class SingleCubeView {

	// Datenkomponente
	private Wuerfel wuerfel;

	// Viewkomponente
	private Image image;

	/**
	 * 
	 * @param wuerfel
	 */
	public SingleCubeView(final Wuerfel wuerfel) {
		this.wuerfel = wuerfel;
		initImage();
	}

	/**
	 * 
	 */
	protected void initImage() {
		image = new Image();
		image.setSize("48", "48");
		image.setTitle("Cube #" + wuerfel.getId());
		image.addClickListener(new ClickListener() {
			public void onClick(Widget arg0) {
				wuerfel.setGesperrt(!wuerfel.isGesperrt());

				// Würfel wurde jetzt gesperrt
				if (wuerfel.isGesperrt()) {
					Kniffel.getKniffelService().lockCube(wuerfel.getId(),
							new AsyncCallback<Void>() {
								public void onFailure(Throwable arg0) {
									Window.alert("Würfel #" + wuerfel.getId()
											+ " wurde nicht gesperrt!");
									wuerfel.setGesperrt(!wuerfel.isGesperrt());
								}

								public void onSuccess(Void arg0) {
									switchImage();
								}
							});
				} else {
					Kniffel.getKniffelService().unlockCube(wuerfel.getId(),
							new AsyncCallback<Void>() {
								public void onFailure(Throwable arg0) {
									Window.alert("Würfel #" + wuerfel.getId()
											+ " wurde nicht entsperrt!");
									wuerfel.setGesperrt(!wuerfel.isGesperrt());
								}

								public void onSuccess(Void arg0) {
									switchImage();
								}
							});
				}
			}
		});

		switchImage();
	}

	/**
	 * 
	 */
	protected void switchImage() {
		String url = wuerfel.isGesperrt() ? "images/" + wuerfel.getWert()
				+ "L.jpeg" : "images/" + wuerfel.getWert() + ".jpeg";
		image.setUrl(url);
	}

	/**
	 * 
	 * @return
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * 
	 * @return
	 */
	public Wuerfel getWuerfel() {
		return wuerfel;
	}
}
