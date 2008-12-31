package de.fh_dortmund.cw.kniffel.client.widgets;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.fh_dortmund.cw.kniffel.client.Kniffel;
import de.fh_dortmund.cw.kniffel.model.KniffelZettel;

/**
 * 
 * @author tbs
 * 
 */
public class SpielerAnzahlWidget extends HorizontalPanel {

	public SpielerAnzahlWidget() {

		// Label
		Label label = new Label("Spieleranzahl:");

		// Eingabe der Spieleranzahl
		final TextBox textbox = new TextBox();
		textbox.setText("1");
		textbox.setMaxLength(1);
		textbox.addChangeListener(new ChangeListener() {
			public void onChange(Widget arg0) {

			}
		});

		// Spiel starten
		final Button startButton = new Button("Spiel Starten!");
		startButton.addClickListener(new ClickListener() {
			public void onClick(Widget arg0) {
				Kniffel.getKniffelService().createNewGame(
						Integer.parseInt(textbox.getText()),
						new AsyncCallback<KniffelZettel>() {
							public void onFailure(Throwable arg0) {
								Window.alert(arg0.getMessage());
							}

							public void onSuccess(KniffelZettel arg0) {
								// 
								textbox.setReadOnly(true);
								startButton.setVisible(false);
							}
						});
			}
		});

		// Allgemeine Einstellungen des Panels
		this.add(label);
		this.add(textbox);
		this.add(startButton);
	}
}
