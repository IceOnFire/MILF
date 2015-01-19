package it.seat.milf.components;

import java.util.Vector;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class MultiPage extends Component {
	private final static int DEFAULT_INTERVAL = 8;

	protected Vector data;
	private FocusContainer currentPage;
	protected Label arrowsLabel;
	private int index;
	private int interval;

	public MultiPage(Vector data, int width, int height) {
		super();
		setSize(width, height);
		this.data = data;
		index = 0;
		interval = DEFAULT_INTERVAL;
	}

	public void setFocus(boolean focus) {
		currentPage.setFocus(focus);
	}

	public void setFocus(boolean focus, int direction) {
		currentPage.setFocus(focus, direction);
	}

	public Component getFocusedChild() {
		return currentPage.getFocusedChild();
	}

	public void addFocusListener(FocusListener listener) {
		currentPage.addFocusListener(listener);
	}

	protected void paintOffscreenBuffer(Graphics g) {
		currentPage.paint(g);
		if (arrowsLabel != null) {
			g.translate(0, currentPage.getHeight());
			arrowsLabel.paint(g);
		}
	}

	protected void switchPage(int direction) {
		int newIndex = index + interval * direction;
		if (newIndex >= 0 && newIndex < data.size()) {
			arrowsLabel = createLabel(newIndex);

			FocusContainer newPage = createPage(newIndex, interval);
			if (currentPage != null) {
				currentPage.destroy();
			}
			currentPage = newPage;
			currentPage.setParent(this);
			currentPage.setFocus(true);
			index = newIndex;
		}
	}

	private Label createLabel(int index) {
		int imageWidth = width;
		int imageHeight = Font.getDefaultFont().getHeight();
		Image arrows = Image.createImage(imageWidth, imageHeight);
		Graphics g = arrows.getGraphics();
		g.setColor(backgroundColor);
		g.fillRect(0, 0, imageWidth, imageHeight);
		g.translate(imageWidth >> 1, imageHeight >> 1);
		g.setColor(foregroundColor);
		if (index > 0) {
			g.fillTriangle(-(imageWidth >> 2) - 6, 0, -(imageWidth >> 2),
					-(imageHeight >> 1) + 3, -(imageWidth >> 2),
					(imageHeight >> 1) - 3);
		}
		if (index < data.size() - interval) {
			g.fillTriangle((imageWidth >> 2) + 6, 0, imageWidth >> 2,
					-(imageHeight >> 1) + 3, imageWidth >> 2,
					(imageHeight >> 1) - 3);
		}

		int pagesNr = data.size() / interval;
		if (data.size() % interval != 0) {
			pagesNr++;
		}
		String pag = "Pagina " + (index / interval + 1) + "/" + pagesNr;
		g.translate(0, -(imageHeight >> 1));
		g.drawString(pag, 0, 0, Graphics.TOP | Graphics.HCENTER);

		return new Label(arrows);
	}

	protected abstract FocusContainer createPage(int from, int length);

	public void keyPressed(int keyCode) {
		currentPage.keyPressed(keyCode);
	}

	public void handleEvent(int event) {
		if (event == LEFT || event == RIGHT) {
			switchPage(event);
		} else {
			super.handleEvent(event);
		}
	}

	public void destroy() {
		super.destroy();
		data.removeAllElements();
		currentPage = null;
	}
}
