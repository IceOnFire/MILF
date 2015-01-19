package it.seat.roma09;

import it.seat.milf.MILFCanvas;
import it.seat.milf.MILFController;
import it.seat.milf.PageConstants;
import it.seat.milf.components.FocusContainer;
import it.seat.roma09.frontend.CercaDataPage;
import it.seat.roma09.frontend.DataPage;
import it.seat.roma09.frontend.MainPage;
import it.seat.roma09.frontend.MenuSpecialitaPage;
import it.seat.roma09.frontend.Roma09Canvas;
import it.seat.roma09.frontend.Roma09PageConstants;
import it.seat.roma09.frontend.SpecialitaPage;

public class Roma09Controller extends MILFController {
	protected MILFCanvas createCanvas() {
		return new Roma09Canvas();
	}

	protected FocusContainer createPage(String pageId, MILFCanvas canvas) {
		int width = canvas.getWidth();
		int height = canvas.getContentsHeight();

		FocusContainer page = null;
		if (pageId == PageConstants.MAIN_PAGE) {
			page = new MainPage(width, height);
		} else if (pageId == Roma09PageConstants.MENU_SPECIALITA_PAGE) {
			page = new MenuSpecialitaPage(width, height);
		} else if (pageId == Roma09PageConstants.CERCA_DATA_PAGE) {
			page = new CercaDataPage(width, height);
		} else if (pageId == Roma09PageConstants.SPECIALITA_PAGE) {
			page = new SpecialitaPage(width, height);
		} else if (pageId == Roma09PageConstants.DATA_PAGE) {
			page = new DataPage(width, height);
		}

		return page;
	}
}
