package it.seat.roma09.frontend;

import it.seat.milf.MILFCanvas;
import it.seat.milf.MILFController;
import it.seat.milf.PageConstants;
import it.seat.milf.components.Component;
import it.seat.milf.components.FocusContainer;
import it.seat.milf.components.KeyCodeSelector;
import it.seat.milf.components.Link;
import it.seat.milf.components.SimpleContainer;
import it.seat.milf.utils.ImageUtils;

import java.io.IOException;

import javax.microedition.lcdui.Image;

public class Roma09Canvas extends MILFCanvas {
	private Link header, footer;
	private SimpleContainer body;

	public Roma09Canvas() {
		super();
		page = new FocusContainer(getWidth(), true);
		page.setSize(getWidth(), getHeight());

		try {
			header = new Link(getWidth());
			Image image = Image.createImage("/img/logo_roma09.gif");
			Image headerImg = ImageUtils.getInstance().rescaleImageH(image, 32);
			header.setImage(headerImg);
			header.setSize(getWidth(), headerImg.getHeight());

			footer = new Link(getWidth());
			image = Image.createImage("/img/logopg.png");
			Image logoImg = ImageUtils.getInstance().rescaleImageH(image, 20);
			footer.setImage(logoImg);
			footer.setSize(getWidth(), logoImg.getHeight());
			footer.setBackgroundColor(Roma09Constants.WHITE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.add(header);
		page.add(footer);
	}

	public SimpleContainer getPage() {
		return body;
	}

	public void setPage(SimpleContainer container) {
		FocusContainer page = (FocusContainer) this.page;
		Component focusedChild = page.getFocusedChild();
		if (focusedChild != body) {
			focusedChild.setFocus(false);
		}

		SimpleContainer oldBody = body;
		body = container;
		if (oldBody == null) {
			page.insert(body, 1);
		} else {
			page.set(body, 1);
		}
		page.setFocusedChild(body);
	}

	public int getContentsHeight() {
		return getHeight() - header.getHeight() - footer.getHeight();
	}

	public void keyPressed(int keyCode) {
		if (keyCode == KeyCodeSelector.KEY_RETURN
				&& (header.hasFocus() || footer.hasFocus())) {
			handleEvent(keyCode);
		} else {
			super.keyPressed(keyCode);
		}
	}

	public void handleEvent(int event) {
		if (header.hasFocus()) {
			MILFController.getInstance().switchPage(PageConstants.MAIN_PAGE);
		} else if (footer.hasFocus()) {
			MILFController.getInstance().openBrowser("http://pda.paginegialle.it");
		}
	}
}
