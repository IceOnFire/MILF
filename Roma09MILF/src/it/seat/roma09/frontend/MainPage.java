package it.seat.roma09.frontend;

import it.seat.milf.MILFController;
import it.seat.milf.components.Container;
import it.seat.milf.components.FocusContainer;
import it.seat.milf.components.KeyCodeSelector;
import it.seat.milf.components.Label;
import it.seat.milf.components.Link;

import java.io.IOException;

import javax.microedition.lcdui.Image;

public class MainPage extends Container {
	private Link specialita, data;

	public MainPage(int width, int height) {
		super(width);

		try {
			Label benvenuti = new Label("Programma delle gare",
					Label.ALIGNMENT_CENTER, width);
			benvenuti.setForegroundColor(Roma09Constants.BLUE);
			benvenuti.setFocusable(false);
			add(benvenuti);

			FocusContainer mainPanel = new FocusContainer(width);
			mainPanel.setBackgroundColor(Roma09Constants.BLUE);

			Image specialitaImage = Image.createImage("/img/diva.png");
			specialita = new Link(specialitaImage, "Per specialità", width);
			specialita.setBackgroundColor(Roma09Constants.BLUE);
			specialita.setForegroundColor(Roma09Constants.WHITE);
			mainPanel.add(specialita);

			Image dataImage = Image.createImage("/img/diva.png");
			data = new Link(dataImage, "Per data", width);
			data.setBackgroundColor(Roma09Constants.BLUE);
			data.setForegroundColor(Roma09Constants.WHITE);
			mainPanel.add(data);

			add(mainPanel, height - benvenuti.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleEvent(int event) {
		if (event == KeyCodeSelector.KEY_RETURN) {
			if (specialita.hasFocus()) {
				MILFController.getInstance().switchPage(
						Roma09PageConstants.MENU_SPECIALITA_PAGE);
			} else if (data.hasFocus()) {
				MILFController.getInstance().switchPage(
						Roma09PageConstants.CERCA_DATA_PAGE);
			}
		} else {
			super.handleEvent(event);
		}
	}
}
