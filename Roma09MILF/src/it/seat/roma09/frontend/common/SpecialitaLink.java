package it.seat.roma09.frontend.common;

import it.seat.milf.components.Link;
import it.seat.roma09.frontend.Roma09Constants;
import it.seat.roma09.model.Roma09;

import java.io.IOException;

import javax.microedition.lcdui.Image;

public class SpecialitaLink extends Link {

	public SpecialitaLink(String specialita, int width) throws IOException {
		super(width);

		String path = null;
		if (specialita.equals(Roma09.NUOTO)) {
			path = "/img/divanuotopic.png";
		} else if (specialita.equals(Roma09.NUOTO_IN_ACQUE_LIBERE)) {
			path = "/img/divanlpic.png";
		} else if (specialita.equals(Roma09.TUFFI)) {
			path = "/img/divatuffipic.png";
		} else if (specialita.equals(Roma09.PALLANUOTO_MASCHILE)) {
			path = "/img/divapnpic.png";
		} else if (specialita.equals(Roma09.PALLANUOTO_FEMMINILE)) {
			path = "/img/divapnpic.png";
		} else if (specialita.equals(Roma09.NUOTO_SINCRONIZZATO)) {
			path = "/img/divasnpic.png";
		}
		try {
			Image specialitaImage = Image.createImage(path);
			setImage(specialitaImage);
			setText(specialita);
			setSize(width, specialitaImage.getHeight());
			setBackgroundColor(Roma09Constants.BLUE);
			setForegroundColor(Roma09Constants.WHITE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
