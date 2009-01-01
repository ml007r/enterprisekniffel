package de.fh_dortmund.cw.kniffel.client.widgets;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;

import de.fh_dortmund.cw.kniffel.client.Kniffel;
import de.fh_dortmund.cw.kniffel.model.KniffelZeile;

/**
 * 
 * @author tbs
 * 
 */
public class KniffelZettelView {

	private final String[] rowTitleLabel = { "&nbsp;", "1 er", "2 er", "3 er",
			"4 er", "5 er", "6 er", "Summe (oben)", "Bonus",
			"Gesamtsumme (oben)", "&nbsp;", "Dreierpasch", "Viererpasch",
			"Full House", "Kleine Straße", "Große Straße", "Kniffel", "Chance",
			"Gesamtsumme (oben)", "Gesamtsumme" };

	// Widget
	private Grid widget;

	//
	private WuerfelView wuerfelView;

	//
	private Integer aktuellerSpielerId;

	/**
	 * 
	 * @param zettel
	 */
	public KniffelZettelView(Integer spielerAnzahl) {
		widget = new Grid(rowTitleLabel.length, 1 + spielerAnzahl);
		widget.setStylePrimaryName("kniffelZettel");
		widget.setCellPadding(0);
		widget.setCellSpacing(0);
		createWidgetContent();

		// Listener
		widget.addTableListener(new TableListener() {
			public void onCellClicked(SourcesTableEvents arg0, int arg1,
					int arg2) {

				// Wenn eine Zelle des aktuellen Spielers angeklickt wurde
				if (arg2 == aktuellerSpielerId) {
					setValue(arg1, arg2);
				}
			}
		});

		aktuellerSpielerId = 1;
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
	protected void setSpielerId() {
		Kniffel.getKniffelService().getAktellerSpieler(
				new AsyncCallback<Integer>() {
					public void onFailure(Throwable arg0) {
						aktuellerSpielerId = 1;
					}

					public void onSuccess(Integer arg0) {
						aktuellerSpielerId = arg0;
					}
				});
	}

	/**
	 * 
	 */
	protected void createWidgetContent() {
		// Zeilen

		for (int i = 0; i < widget.getRowCount(); i++) {
			// Spalten
			for (int j = 0; j < widget.getColumnCount(); j++) {

				widget.setWidget(i, j, new HTML(initLabelValue(i, j)));

				// Standard
				String cssStyleClass = "defaultCell";

				if (i == 0 && j == 0) {
					cssStyleClass = "titleCell";
				} else if (i == 0) {
					cssStyleClass = "titleRowCell";
				} else if (j == 0) {
					cssStyleClass = "titleColumnCell";
				}

				widget.getCellFormatter().setStylePrimaryName(i, j,
						cssStyleClass);
			}
		}
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	protected String initLabelValue(int row, int col) {
		if (col == 0) {
			return getRowLabel(row);
		} else if (row == 0) {
			return "Spieler #" + col;
		} else {
			return "&nbsp;";
		}
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	protected String getRowLabel(int row) {
		return rowTitleLabel[row];
	}

	/**
	 * 
	 * @param row
	 */
	protected void setValue(int row, int column) {
		final HTML cell = (HTML) widget.getWidget(row, column);

		// Nur wenn das Feld noch leer ist...
		if ("&nbsp;".equalsIgnoreCase(cell.getHTML())) {
			Kniffel.getKniffelService().setValue(findKniffelZeile(row),
					new AsyncCallback<Integer>() {
						public void onFailure(Throwable arg0) {
							Window
									.alert("Die Zelle kann nicht ausgewählt werden!");
						}

						public void onSuccess(Integer arg0) {
							cell.setHTML("" + arg0);

							wuerfelView.reinitCubes();

							setSpielerId();
							// TODO Summenfüllung
						}
					});
		} else {
			Window.alert("Die Zelle kann nicht ausgewählt werden!");
		}
	}

	/**
	 * 
	 * @param row
	 * @return
	 */
	protected KniffelZeile findKniffelZeile(int row) {

		// 1...6
		if (row > 0 && row < 7) {
			return KniffelZeile.values()[row - 1];
		}

		// Pasch...Chance
		if (row > 10 && row < 18) {
			return KniffelZeile.values()[row - 1];
		}

		return null;
	}

	/**
	 * 
	 * @param wuerfelView
	 */
	public void registerWuerfelView(WuerfelView wuerfelView) {
		this.wuerfelView = wuerfelView;
	}
}
