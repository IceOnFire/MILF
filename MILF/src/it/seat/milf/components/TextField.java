package it.seat.milf.components;

import it.seat.milf.MILFConstants;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class TextField extends Component {
	private final static int KEY_INTERVAL_MS = 5000;
	private final static int CARET_INTERVAL_MS = 1000;

	private Font font;
	private String text;
	private String hint;

	private int candidateChar;
	private char renderedChar;
	private int keyTime;

	private int caretIndex;
	private boolean caretVisible;
	private int caretTime;

	private int offset;
	private Mode currentMode;

	public TextField(int columns) {
		super();
		font = Font.getDefaultFont();
		offset = 6;
		width = font.charWidth('0') * columns + offset;
		height = font.getHeight() + offset;
		offscreenBuffer = Image.createImage(width, height);

		hint = "";
		reset();
	}

	public TextField(String hint, int columns) {
		this(columns);
		this.hint = hint;
	}

	public TextField(String hint) {
		this(hint, hint.length());
	}

	public void reset() {
		text = "";
		candidateChar = 0;
		renderedChar = 0;
		keyTime = 0;

		caretIndex = 0;
		caretVisible = false;
		caretTime = 0;
		setMode(new EmptyMode());
	}

	public String getText() {
		return text;
	}

	private boolean sameCharacter(int keyCode) {
		return candidateChar == keyCode;
	}

	private void newCharacter(int keyCode) {
		candidateChar = keyCode;
		KeyCodeSelector kcs = KeyCodeSelector.getInstance();
		kcs.reset();
		renderedChar = (char) kcs.getKey(keyCode, RIGHT);
		keyTime = 0;
		setMode(new InsertMode());
	}

	private void nextCharacter(int direction) {
		renderedChar = (char) KeyCodeSelector.getInstance().getKey(
				candidateChar, direction);
		keyTime = 0;
	}

	private void confirmCharacter() {
		String before = "";
		if (caretIndex > 0) {
			before = text.substring(0, caretIndex);
		}
		String after = text.substring(caretIndex);
		text = before + renderedChar + after;
		candidateChar = 0;
		renderedChar = 0;
		caretIndex++;
		setMode(new GenericMode());
	}

	private void deleteCharacter() {
		caretIndex--;
		if (caretIndex < 0) {
			caretIndex = 0;
		}

		String before = "";
		if (caretIndex > 0) {
			before = text.substring(0, caretIndex);
		}
		String after = "";
		if (caretIndex + 1 < text.length()) {
			after = text.substring(caretIndex + 1, text.length());
		}
		text = before + after;
		if (text.length() == 0) {
			setMode(new EmptyMode());
		}
	}

	private void deleteTempCharacter() {
		String before = "";
		if (caretIndex > 0) {
			before = text.substring(0, caretIndex);
		}
		String after = text.substring(caretIndex, text.length());
		text = before + after;
		candidateChar = 0;
		renderedChar = 0;
		if (text.length() == 0) {
			setMode(new EmptyMode());
		} else {
			setMode(new GenericMode());
		}
	}

	private void moveCaret(int direction) {
		KeyCodeSelector.getInstance().reset();
		caretIndex += direction;
		if (caretIndex < 0) {
			caretIndex = 0;
		} else if (caretIndex > text.length()) {
			caretIndex -= direction;
			newCharacter(Canvas.KEY_NUM0);
		}
	}

	protected void paintOffscreenBuffer(Graphics g) {
		int arc = 4;
		/* disegna il contorno */
		g.setColor(64, 64, 64);
		g.fillRoundRect(1, 1, width - 2, height - 2, arc, arc);
		g.setColor(192, 192, 192);
		g.fillRoundRect(2, 2, width - 3, height - 3, arc, arc);
		/* disegna lo sfondo */
		g.setColor(255, 255, 255);
		g.fillRoundRect(2, 2, width - 4, height - 4, arc, arc);

		paintFocus(g);

		g.setClip(offset, offset >> 1, width - offset, height - offset);

		int caretOffset = 0;
		if (caretIndex > 0 && caretIndex <= text.length()) {
			caretOffset = font.substringWidth(text, 0, caretIndex);
		}
		int maxWidth = width - 4 * offset;
		int strPos = 0;
		if (caretOffset > maxWidth) {
			strPos = caretOffset - maxWidth;
		}
		g.translate(offset - strPos, offset >> 1);

		keyTime += MILFConstants.MILLISECONDS_PER_FRAME;
		currentMode.paint(g, this);

		if (hasFocus()) {
			if (caretVisible) {
				/* disegna il caret */
				g.setColor(foregroundColor);
				g.drawLine(caretOffset, 0, caretOffset, height - offset);
			}

			if (caretTime >= CARET_INTERVAL_MS) {
				caretVisible = !caretVisible;
				caretTime = 0;
			}
			caretTime += MILFConstants.MILLISECONDS_PER_FRAME;
		}
	}

	private void paintTempText(Graphics g) {
		if (keyTime >= KEY_INTERVAL_MS) {
			confirmCharacter();
		}
		int caretOffset = 0;
		if (caretIndex > 0 && caretIndex <= text.length()) {
			caretOffset = font.substringWidth(text, 0, caretIndex);
		}
		int charWidth = font.charWidth(renderedChar);

		g.setColor(foregroundColor);
		g.drawString(text.substring(0, caretIndex), 0, 0, Graphics.TOP
				| Graphics.LEFT);
		g.setColor(128, 128, 255);
		g.fillRect(caretOffset, 0, charWidth, font.getHeight());
		g.setColor(255, 255, 255);
		g.drawString("" + renderedChar, caretOffset, 0, Graphics.TOP
				| Graphics.LEFT);
		g.setColor(foregroundColor);
		g.drawString(text.substring(caretIndex), caretOffset + charWidth, 0,
				Graphics.TOP | Graphics.LEFT);
	}

	private void paintText(Graphics g) {
		g.setColor(foregroundColor);
		g.drawString(text, 0, 0, Graphics.TOP | Graphics.LEFT);
	}

	private void paintHint(Graphics g) {
		g.setColor(128, 128, 128);
		g.drawString(hint, 0, 0, Graphics.TOP | Graphics.LEFT);
	}

	public void handleEvent(int event) {
		currentMode.handleEvent(event, this);
	}

	void setMode(Mode mode) {
		currentMode = mode;
	}

	private static interface Mode {
		void handleEvent(int keyCode, TextField t);

		void paint(Graphics g, TextField t);
	}

	private static abstract class IdleMode implements Mode {
		public void handleEvent(int event, TextField t) {
			switch (event) {
			case KeyCodeSelector.KEY_BACKSPACE:
				t.deleteCharacter();
				break;
			case KeyCodeSelector.KEY_RETURN:
				break;
			case LEFT:
			case RIGHT:
				t.moveCaret(event);
				break;
			case UP:
			case DOWN:
				t.getParent().handleEvent(event);
				break;
			default:
				t.newCharacter(event);
			}
		}
	}

	private static class EmptyMode extends IdleMode {
		public void paint(Graphics g, TextField t) {
			t.paintHint(g);
		}
	}

	private static class GenericMode extends IdleMode {
		public void paint(Graphics g, TextField t) {
			t.paintText(g);
		}
	}

	private static class InsertMode implements Mode {
		public void handleEvent(int event, TextField t) {
			switch (event) {
			case LEFT:
			case KeyCodeSelector.KEY_BACKSPACE:
				t.deleteTempCharacter();
				break;
			case RIGHT:
			case KeyCodeSelector.KEY_RETURN:
				t.confirmCharacter();
				break;
			case UP:
				t.nextCharacter(LEFT);
				break;
			case DOWN:
				t.nextCharacter(RIGHT);
				break;
			default:
				if (t.sameCharacter(event)) {
					t.nextCharacter(RIGHT);
				} else {
					t.confirmCharacter();
					t.newCharacter(event);
				}
			}
		}

		public void paint(Graphics g, TextField t) {
			t.paintTempText(g);
		}
	}
}
