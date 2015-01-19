package it.seat.roma09.frontend;

import it.seat.milf.components.ComponentWrapper;
import it.seat.milf.components.Container;
import it.seat.milf.components.FocusContainer;
import it.seat.milf.components.Label;
import it.seat.milf.components.MultiPage;
import it.seat.milf.components.TextScroller;
import it.seat.roma09.model.Evento;
import it.seat.roma09.model.Roma09;

import java.util.Vector;

public class DataPage extends FocusContainer {
	public DataPage(int width, int height) {
		super(width);
		int giorno = Roma09.getInstance().getGiornoCorrente();
		int mese = Roma09.getInstance().getMeseCorrente();
		int anno = 2009;

		String datatot = giorno + "/" + mese + "/" + anno;

		Label dataLabel = new Label(datatot, Label.ALIGNMENT_CENTER, width);
		dataLabel.setForegroundColor(Roma09Constants.RED);
		dataLabel.setFocusable(false);
		add(dataLabel);

		Vector eventi = Roma09.getInstance()
				.getEventiByData(giorno, mese, anno);
		MultiPage dataPanel = new DataMultiPage(eventi, width,
				height - dataLabel.getHeight());
		add(dataPanel);
	}

	private static class DataMultiPage extends MultiPage {
		public DataMultiPage(Vector data, int width, int height) {
			super(data, width, height);
			setBackgroundColor(Roma09Constants.BLUE);
			setForegroundColor(Roma09Constants.WHITE);
			switchPage(0);
		}

		protected FocusContainer createPage(int from, int length) {
			Container page = new Container(width);
			FocusContainer container = new FocusContainer(width);

			for (int i = from; i < Math.min(from + length, data.size()); i++) {
				try {
					Evento evento = (Evento) data.elementAt(i);
					Label specialita = new Label(evento.getSpecialita(),
							Label.ALIGNMENT_LEFT, width);
					specialita.setForegroundColor(Roma09Constants.BLUE);
					Label descrizione = new Label(evento.getDescrizione(),
							Label.ALIGNMENT_LEFT, width);
					TextScroller descScroller = new TextScroller(descrizione);
					Label orario = new Label(evento.formattaOra(),
							Label.ALIGNMENT_LEFT, width);
					orario.setForegroundColor(Roma09Constants.BLUE);
//					ComponentWrapper wrapper = new ComponentWrapper(width);
//					wrapper.add(specialita);
//					wrapper.add(descrizione);
//					wrapper.add(orario);
//					container.add(wrapper);
					container.add(specialita);
					container.add(descScroller);
					container.add(orario);
				} catch (Throwable e) {
					Label errorLabel = new Label(e.getMessage(),
							Label.ALIGNMENT_CENTER, width);
					container.add(errorLabel);
					break;
				}
			}
			page.add(container, height - 12);
			return page;
		}
	}
}
