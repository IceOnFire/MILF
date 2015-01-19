package it.seat.roma09.frontend;

import it.seat.milf.MILFCanvas;
import it.seat.milf.MILFPageAbstractFactory;
import it.seat.milf.PageConstants;
import it.seat.milf.components.FocusContainer;

public class Roma09PageFactory extends MILFPageAbstractFactory {
	public MILFCanvas createCanvas() {
		return new Roma09Canvas();
	}

	public FocusContainer createPage(String pageId, MILFCanvas canvas) {
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
