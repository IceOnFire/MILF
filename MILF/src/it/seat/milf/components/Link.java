package it.seat.milf.components;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Link extends Component {
	private final static int DEFAULT_OFFSET = 2;

	private Image image;
	private String text;
	private Font font;

	public Link(int width) {
		super();
		this.width = width;
		font = Font.getDefaultFont();
	}

	public Link(Image image, int width, int height) {
		this(width);
		this.image = image;
		this.height = height;
		offscreenBuffer = Image.createImage(width, height);
	}

	public Link(Image image, String text, int width) {
		super();
		font = Font.getDefaultFont();
		this.width = width;
		height = Math.max(image.getHeight(), font.getHeight());
		offscreenBuffer = Image.createImage(width, height);

		this.image = image;
		this.text = text;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	protected void paintOffscreenBuffer(Graphics g) {
		if (text != null) {
			g.drawImage(image, 0, 0, Graphics.TOP | Graphics.LEFT);
			g.setColor(foregroundColor);
			g.translate(image.getWidth() + DEFAULT_OFFSET, font.getHeight() >> 1);
			g.drawString(text, 0, 0, Graphics.TOP | Graphics.LEFT);
			g.translate(-(image.getWidth() + DEFAULT_OFFSET), -(font.getHeight() >> 1));
		} else {
			g.drawImage(image, getWidth() >> 1, 0, Graphics.TOP | Graphics.HCENTER);
		}

		paintFocus(g);
	}

	public boolean canGiveFocus() {
		return true;
	}
}
