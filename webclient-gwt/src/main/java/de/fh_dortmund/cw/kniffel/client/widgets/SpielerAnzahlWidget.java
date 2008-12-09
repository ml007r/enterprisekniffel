package de.fh_dortmund.cw.kniffel.client.widgets;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author tbs
 * 
 */
public class SpielerAnzahlWidget extends SimplePanel {

	private Label label = new Label();

	private TextBox textbox = new TextBox();

	private Button startButton = new Button();

	/**
	 * 
	 */
	public SpielerAnzahlWidget() {

		// Label
		label.setText("Spieleranzahl:");
		label.setVisible(true);

		// Eingabe der Spieleranzahl
		textbox.addChangeListener(new ChangeListener() {
			public void onChange(Widget arg0) {

			}
		});
		textbox.setMaxLength(1);
		textbox.setVisible(true);

		// Spiel starten
		startButton.setText("Spiel Starten!");
		startButton.addClickListener(new ClickListener() {
			public void onClick(Widget arg0) {
				Window.alert("Juhuu");
			}
		});
		startButton.setVisible(true);

		// Allgemeine Einstellungen des Panels
		this.add(label);
		this.add(textbox);
		this.add(startButton);
		
		this.setSize("1000", "50");
		this.setVisible(true);
	}
}
