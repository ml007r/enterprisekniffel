package de.fh_dortmund.cw.kniffel.client.widgets;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SourcesTableEvents;
import com.google.gwt.user.client.ui.TableListener;

import de.fh_dortmund.cw.kniffel.client.Kniffel;
import de.fh_dortmund.cw.kniffel.model.KniffelZeile;
import de.fh_dortmund.cw.kniffel.model.Spieler;

/**
 * 
 * @author tbs
 * 
 */
public class KniffelZettelView {

	// Muss mit KniffelZeile übereinstimmen + 1 Zeile nbsp
	private final String[] rowTitleLabel = { "", "1 er", "2 er", "3 er",
			"4 er", "5 er", "6 er", "Summe (oben)", "Bonus",
			"Gesamtsumme (oben)", "Dreierpasch", "Viererpasch", "Full House",
			"Kleine Straße", "Große Straße", "Kniffel", "Chance",
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

		setSpieler();
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
	protected void setSpieler() {
		Kniffel.getKniffelService().getAktellerSpieler(
				new AsyncCallback<Spieler>() {
					public void onFailure(Throwable arg0) {
						aktuellerSpielerId = 1;
					}

					public void onSuccess(Spieler arg0) {
						aktuellerSpielerId = arg0.getId();

						for (int i = 0; i < rowTitleLabel.length; i++) {

							int lastPlayerColumn = aktuellerSpielerId == 1 ? widget
									.getColumnCount() - 1
									: aktuellerSpielerId - 1;

							widget.getWidget(i, lastPlayerColumn)
									.removeStyleName("currentPlayer");

							widget.getWidget(i, aktuellerSpielerId)
									.addStyleName("currentPlayer");
						}
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

		if ("&nbsp;".equals(cell.getHTML())) {
			Kniffel.getKniffelService().setValue(findKniffelZeile(row),
					new AsyncCallback<Integer>() {
						public void onFailure(Throwable arg0) {
							Window.alert(arg0.getMessage());
						}

						public void onSuccess(Integer arg0) {
							cell.setText("" + arg0);
							wuerfelView.reinitCubes();
							writeSum();
							setSpieler();
						}
					});
		} else {
			Window.alert("Diese Zelle kann nicht ausgewählt werden!");
		}
	}

	/**
	 * Schreibt die Summenfelder
	 */
	protected void writeSum() {

		// Summe oben
		final HTML sumTop = (HTML) widget.getWidget(7, aktuellerSpielerId);
		if ("&nbsp;".equals(sumTop.getHTML())) {
			Kniffel.getKniffelService().getValue(KniffelZeile.SUM_TOP,
					aktuellerSpielerId, new AsyncCallback<Integer>() {
						public void onFailure(Throwable arg0) {
							// keine Summe vorhanden -> nichts machen
							sumTop.setHTML("&nbsp;");
						}

						public void onSuccess(Integer arg0) {
							sumTop.setHTML("" + arg0);
						}
					});
		}

		// Bonus oben
		final HTML bonusTop = (HTML) widget.getWidget(8, aktuellerSpielerId);
		if ("&nbsp;".equals(bonusTop.getHTML())) {
			Kniffel.getKniffelService().getValue(KniffelZeile.BONUS_TOP,
					aktuellerSpielerId, new AsyncCallback<Integer>() {
						public void onFailure(Throwable arg0) {
							// keine Summe vorhanden -> nichts machen
							bonusTop.setHTML("&nbsp;");
						}

						public void onSuccess(Integer arg0) {
							bonusTop.setHTML("" + arg0);
						}
					});
		}

		// Bonus oben
		final HTML totalSumTop = (HTML) widget.getWidget(9, aktuellerSpielerId);
		if ("&nbsp;".equals(totalSumTop.getHTML())) {
			Kniffel.getKniffelService().getValue(KniffelZeile.SUM_TOP_TOTAL,
					aktuellerSpielerId, new AsyncCallback<Integer>() {
						public void onFailure(Throwable arg0) {
							// keine Summe vorhanden -> nichts machen
							totalSumTop.setHTML("&nbsp;");
						}

						public void onSuccess(Integer arg0) {
							totalSumTop.setHTML("" + arg0);
						}
					});
		}

		// Summe unten
		final HTML sumBottom = (HTML) widget.getWidget(17, aktuellerSpielerId);
		if ("&nbsp;".equals(sumBottom.getHTML())) {
			Kniffel.getKniffelService().getValue(KniffelZeile.SUM_BOTTOM_TOTAL,
					aktuellerSpielerId, new AsyncCallback<Integer>() {
						public void onFailure(Throwable arg0) {
							// keine Summe vorhanden -> nichts machen
							sumBottom.setHTML("&nbsp;");
						}

						public void onSuccess(Integer arg0) {
							sumBottom.setHTML("" + arg0);
						}
					});
		}

		// Gesamtsumme
		final HTML totalSum = (HTML) widget.getWidget(18, aktuellerSpielerId);
		if ("&nbsp;".equals(totalSum.getHTML())) {
			Kniffel.getKniffelService().getValue(KniffelZeile.SUM_TOTAL,
					aktuellerSpielerId, new AsyncCallback<Integer>() {
						public void onFailure(Throwable arg0) {
							// keine Summe vorhanden -> nichts machen
							totalSum.setHTML("&nbsp;");
						}

						public void onSuccess(Integer arg0) {
							totalSum.setHTML("" + arg0);
							if (aktuellerSpielerId == widget.getColumnCount()) {
								wuerfelView.getWidget().clear();
								Window.alert("Alles hat ein Ende...");
							}
						}
					});
		}
	}

	/**
	 * 
	 * @param row
	 * @return
	 */
	protected KniffelZeile findKniffelZeile(int row) {
		switch (row) {
		case 1:
			return KniffelZeile.ONE;
		case 2:
			return KniffelZeile.TWO;
		case 3:
			return KniffelZeile.THREE;
		case 4:
			return KniffelZeile.FOUR;
		case 5:
			return KniffelZeile.FIVE;
		case 6:
			return KniffelZeile.SIX;
		case 10:
			return KniffelZeile.THREE_OAK;
		case 11:
			return KniffelZeile.FOUR_OAK;
		case 12:
			return KniffelZeile.FULL_HOUSE;
		case 13:
			return KniffelZeile.STREET_1;
		case 14:
			return KniffelZeile.STREET_2;
		case 15:
			return KniffelZeile.YAHTZEE;
		case 16:
			return KniffelZeile.CHANCE;
		default:
			return null;
		}
	}

	/**
	 * 
	 * @param wuerfelView
	 */
	public void registerWuerfelView(WuerfelView wuerfelView) {
		this.wuerfelView = wuerfelView;
	}
}
