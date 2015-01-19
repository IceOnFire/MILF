package it.seat.milf.components;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Button extends Component {
	private String text;
	private Font font;

	private int offset;

	public Button(String text) {
		super();
		font = Font.getDefaultFont();
		offset = 6;
		width = font.stringWidth(text) + offset;
		height = font.getHeight() + offset;
		offscreenBuffer = Image.createImage(width, height);

		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	protected void paintOffscreenBuffer(Graphics g) {
		int arc = 4;
		g.setColor(255, 255, 255);
		g.fillRoundRect(1, 1, width - 2, height - 2, arc, arc);
		g.setColor(64, 64, 64);
		g.fillRoundRect(2, 2, width - 3, height - 3, arc, arc);
		g.setColor(192, 192, 192);
		g.fillRoundRect(2, 2, width - 4, height - 4, arc, arc);

		paintFocus(g);

		g.setColor(foregroundColor);
		g.translate(offset + font.substringWidth(text, 0, text.length() >> 1),
				offset >> 1);
		g.drawString(text, 0, 0, Graphics.TOP | Graphics.HCENTER);
	}

	public boolean canGiveFocus() {
		return true;
	}
}
