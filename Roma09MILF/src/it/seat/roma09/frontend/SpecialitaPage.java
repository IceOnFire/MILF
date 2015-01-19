package it.seat.roma09.frontend;

import it.seat.milf.components.ComponentWrapper;
import it.seat.milf.components.Container;
import it.seat.milf.components.FocusContainer;
import it.seat.milf.components.Label;
import it.seat.milf.components.MultiPage;
import it.seat.milf.components.TextScroller;
import it.seat.roma09.frontend.common.SpecialitaLink;
import it.seat.roma09.model.Evento;
import it.seat.roma09.model.Roma09;

import java.io.IOException;
import java.util.Vector;

public class SpecialitaPage extends FocusContainer {
	public SpecialitaPage(int width, int height) {
		super(width);

		try {
			String specialita = Roma09.getInstance().getSpecialitaCorrente();

			SpecialitaLink specialitaLabel = new SpecialitaLink(specialita,
					width);
			specialitaLabel.setFocusable(false);
			add(specialitaLabel);

			Vector eventi = Roma09.getInstance().getEventiBySpecialita(
					specialita);
			MultiPage specialitaPanel = new SpecialitaMultiPage(eventi, width,
					height - specialitaLabel.getHeight());
			add(specialitaPanel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class SpecialitaMultiPage extends MultiPage {
		public SpecialitaMultiPage(Vector data, int width, int height) {
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
					Label data = new Label(evento.formattaData(),
							Label.ALIGNMENT_CENTER, width);
					data.setForegroundColor(Roma09Constants.RED);
					Label descrizione = new Label(evento.getDescrizione(),
							Label.ALIGNMENT_LEFT, width);
					TextScroller descScroller = new TextScroller(descrizione);
					Label orario = new Label(evento.formattaOra(),
							Label.ALIGNMENT_LEFT, width);
					orario.setForegroundColor(Roma09Constants.BLUE);
//					ComponentWrapper wrapper = new ComponentWrapper(width);
//					wrapper.add(data);
//					wrapper.add(descScroller);
//					wrapper.add(orario);
//					container.add(wrapper);
					container.add(data);
					container.add(descScroller);
					container.add(orario);
				} catch (Throwable e) {
					Label errorLabel = new Label(e.getMessage(),
							Label.ALIGNMENT_CENTER, width);
					container.add(errorLabel);
					break;
				}
			}
			page.add(container, height - arrowsLabel.getHeight());
			return page;
		}
	}
}
