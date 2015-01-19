package it.seat.milf;

import javax.microedition.midlet.MIDlet;

public abstract class MILFMIDlet extends MIDlet {
	public MILFMIDlet() {
		MILFController.getInstance().setMIDlet(this);
	}

	protected void startApp() {

	}

	protected void pauseApp() {

	}

	public void destroyApp(boolean unconditional) {

	}
}
