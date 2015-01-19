package it.seat.milf.components;

import it.seat.milf.utils.StringUtils;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Label extends Component {
	public final static int ALIGNMENT_LEFT = Graphics.LEFT;
	public final static int ALIGNMENT_CENTER = Graphics.HCENTER;
	private final static int DEFAULT_OFFSET = 6;

	private Image image;
	private Font font;
	private String text;
	private Alignment alignment;
	private int offset;

	public Label(String text, int alignment, int width) {
		super();
		font = Font.getDefaultFont();
		if (alignment == ALIGNMENT_LEFT) {
			this.alignment = new LeftAlignment();
		} else if (alignment == ALIGNMENT_CENTER) {
			this.alignment = new CenterAlignment();
		}
		offset = DEFAULT_OFFSET;
		this.text = text;
		setSize(width, 0);
	}

	public Label(Image image) {
		this(image, image.getWidth(), image.getHeight());
	}

	public Label(Image image, int width, int height) {
		super();
		this.alignment = new LeftAlignment();
		this.image = image;
		setSize(width, height);
	}

	int getOffset() {
		return offset;
	}

	boolean needsScrolling() {
		return Font.getDefaultFont().stringWidth(text) >= width - 2 * offset
				- ScrollPane.DEFAULT_WIDTH;
	}

	public void setSize(int width, int height) {
		if (text != null) {
			height = font.getHeight() * splitText();
		}
		super.setSize(width, height);
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
		if (image != null) {
			alignment.drawImage(g, this);
		}
		if (text != null) {
			g.setColor(foregroundColor);

			String[] rows = StringUtils.getInstance().split(text, "\n");
			for (int i = 0; i < rows.length; i++) {
				alignment.drawString(g, rows[i], this);
				g.translate(0, font.getHeight());
			}
			g.translate(0, -rows.length * font.getHeight());
		}

		paintFocus(g);
	}

	public boolean canGiveFocus() {
		return true;
	}

	private int splitText() {
		int rows = 1;
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '\n') {
				rows++;
			}
		}
		return rows;
	}

	// private int splitText() {
	// String splittedText = "";
	//
	// String tempText = "";
	// int rows = 1;
	// for (int i = 0; i < text.length(); i++) {
	// char nextChar = text.charAt(i);
	// tempText += nextChar;
	// if (nextChar == '\n') {
	// splittedText += tempText;
	// tempText = "";
	// rows++;
	// } else {
	// int currentWidth = font.stringWidth(tempText);
	// if (currentWidth > width - 2 * offset
	// - Font.getDefaultFont().getHeight()) {
	// int trunkHere = findSpace(tempText);
	// splittedText += tempText.substring(0, trunkHere) + "\n";
	// tempText = tempText.substring(trunkHere + 1);
	// rows++;
	// }
	// }
	// }
	// splittedText += tempText;
	// text = splittedText;
	//
	// return rows;
	// }
	//
	// private int findSpace(String tempText) {
	// int index = tempText.length();
	// boolean spaceFound = false;
	//
	// for (int i = tempText.length() - 1; i > 0 && !spaceFound; i--) {
	// if (tempText.charAt(i) == ' ') {
	// index = i;
	// spaceFound = true;
	// }
	// }
	// return index;
	// }

	private static interface Alignment {
		public void drawImage(Graphics g, Label l);

		public void drawString(Graphics g, String string, Label l);
	}

	private static class LeftAlignment implements Alignment {
		public void drawImage(Graphics g, Label l) {
			g.translate(l.offset, 0);
			g.drawImage(l.image, 0, 0, Graphics.TOP | Graphics.LEFT);
			g.translate(-l.offset, 0);
		}

		public void drawString(Graphics g, String string, Label l) {
			g.translate(l.offset, 0);
			g.drawString(string, 0, 0, Graphics.TOP | Graphics.LEFT);
			g.translate(-l.offset, 0);
		}
	}

	private static class CenterAlignment implements Alignment {
		public void drawImage(Graphics g, Label l) {
			int center = l.width >> 1;
			g.translate(center, 0);
			g.drawImage(l.image, 0, 0, Graphics.TOP | Graphics.HCENTER);
			g.translate(-center, 0);
		}

		public void drawString(Graphics g, String string, Label l) {
			int center = l.width >> 1;
			g.translate(center, 0);
			g.drawString(string, 0, 0, Graphics.TOP | Graphics.HCENTER);
			g.translate(-center, 0);
		}
	}
}
