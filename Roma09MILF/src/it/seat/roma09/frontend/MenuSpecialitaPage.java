package it.seat.roma09.frontend;

import it.seat.milf.MILFController;
import it.seat.milf.components.Container;
import it.seat.milf.components.FocusContainer;
import it.seat.milf.components.KeyCodeSelector;
import it.seat.roma09.frontend.common.SpecialitaLink;
import it.seat.roma09.model.Roma09;

import java.io.IOException;

public class MenuSpecialitaPage extends Container {
	private SpecialitaLink nuoto, tuffi, nuotolib, nuotosinc, pallanuotom,
			pallanuotof;

	public MenuSpecialitaPage(int width, int height) {
		super(width);

		try {
			FocusContainer specialitaPanel = new FocusContainer(width);
			specialitaPanel.setBackgroundColor(Roma09Constants.BLUE);

			nuoto = new SpecialitaLink(Roma09.NUOTO, width);
			specialitaPanel.add(nuoto);
			nuotolib = new SpecialitaLink(Roma09.NUOTO_IN_ACQUE_LIBERE, width);
			specialitaPanel.add(nuotolib);
			tuffi = new SpecialitaLink(Roma09.TUFFI, width);
			specialitaPanel.add(tuffi);
			pallanuotom = new SpecialitaLink(Roma09.PALLANUOTO_MASCHILE, width);
			specialitaPanel.add(pallanuotom);
			pallanuotof = new SpecialitaLink(Roma09.PALLANUOTO_FEMMINILE, width);
			specialitaPanel.add(pallanuotof);
			nuotosinc = new SpecialitaLink(Roma09.NUOTO_SINCRONIZZATO, width);
			specialitaPanel.add(nuotosinc);

			add(specialitaPanel, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleEvent(int event) {
		if (event == KeyCodeSelector.KEY_RETURN) {
			if (nuoto.hasFocus()) {
				Roma09.getInstance().setSpecialitaCorrente(Roma09.NUOTO);
			} else if (nuotolib.hasFocus()) {
				Roma09.getInstance().setSpecialitaCorrente(
						Roma09.NUOTO_IN_ACQUE_LIBERE);
			} else if (tuffi.hasFocus()) {
				Roma09.getInstance().setSpecialitaCorrente(Roma09.TUFFI);
			} else if (pallanuotom.hasFocus()) {
				Roma09.getInstance().setSpecialitaCorrente(
						Roma09.PALLANUOTO_MASCHILE);
			} else if (pallanuotof.hasFocus()) {
				Roma09.getInstance().setSpecialitaCorrente(
						Roma09.PALLANUOTO_FEMMINILE);
			} else if (nuotosinc.hasFocus()) {
				Roma09.getInstance().setSpecialitaCorrente(
						Roma09.NUOTO_SINCRONIZZATO);
			}
			MILFController.getInstance().switchPage(
					Roma09PageConstants.SPECIALITA_PAGE, false);
		} else {
			super.handleEvent(event);
		}
	}
}
