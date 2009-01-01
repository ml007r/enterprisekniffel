package de.fh_dortmund.cw.kniffel.client.widgets;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.fh_dortmund.cw.kniffel.client.Kniffel;

/**
 * 
 * @author tbs
 * 
 */
public class SpielerView {

	// 
	private Grid widget = new Grid(1, 3);

	private final Label label = new Label("Spieleranzahl:");

	private final TextBox textbox = new TextBox();

	private final Button startButton = new Button("Spiel Starten!");

	/**
	 * 
	 */
	public SpielerView() {
		widget.setWidget(0, 0, label);

		initTextBox();
		widget.setWidget(0, 1, textbox);

		initButton();
		widget.setWidget(0, 2, startButton);
	}

	/**
	 * 
	 * @return
	 */
	public Grid getWidget() {
		return widget;
	}

	/**
	 * 
	 */
	protected void initTextBox() {
		textbox.setText("1");
		textbox.setMaxLength(1);
		textbox.addChangeListener(new ChangeListener() {
			public void onChange(Widget arg0) {
				try {
					int i = Integer.parseInt(((TextBox) arg0).getText());
					if (i == 0) {
						throw new Exception();
					}
				} catch (Exception e) {
					Window
							.alert("Falsche Eingabe! Bitte eine Zahl zwischen 1 und 9 eingeben!");
				}
			}
		});
	}

	/**
	 * 
	 */
	protected void initButton() {
		startButton.addClickListener(new ClickListener() {
			public void onClick(Widget arg0) {
				Kniffel.getKniffelService().createNewGame(
						Integer.parseInt(textbox.getText()),
						new AsyncCallback<Void>() {
							public void onFailure(Throwable arg0) {
								Window.alert(arg0.getMessage());
							}

							public void onSuccess(Void arg0) {
								Kniffel.startGame(Integer.parseInt(textbox
										.getText()));
							}
						});
			}
		});
	}
}
