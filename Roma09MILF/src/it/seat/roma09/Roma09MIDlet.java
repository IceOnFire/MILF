package it.seat.roma09;

import it.seat.milf.MILFMIDlet;
import it.seat.milf.PageConstants;

public class Roma09MIDlet extends MILFMIDlet {
	protected void startApp() {
		Roma09Controller.getInstance().switchPage(PageConstants.MAIN_PAGE);
	}
}
