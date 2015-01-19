package it.seat.milf.components;

import java.util.Vector;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class Component {
	public final static int LEFT = -1;
	public final static int RIGHT = 1;
	public final static int UP = 0;
	public final static int DOWN = 2;

	protected int x, y;
	protected int width, height;
	protected Image offscreenBuffer;
	protected int backgroundColor;
	protected int foregroundColor;

	protected Component parent;

	private Vector focusListeners;
	private boolean focusable;
	private boolean focus;

	// private Vector eventListeners;

	protected Component() {
		focusable = true;
		backgroundColor = 0xFFFFFF;
		foregroundColor = 0x000000;
		focusListeners = new Vector();
		// eventListeners = new Vector();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
		offscreenBuffer = Image.createImage(width, height);
	}

	public void place(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	public boolean isFocusable() {
		return focusable;
	}

	public void setFocusable(boolean focusable) {
		this.focusable = focusable;
	}

	public boolean hasFocus() {
		return focus;
	}

	public void setFocus(boolean focus) {
		this.focus = focus;
	}

	protected void setFocus(boolean focus, int direction) {
		this.focus = focus;
		if (focus) {
			notifyFocusGained(direction);
		} else {
			notifyFocusLost(direction);
		}
	}

	public void addFocusListener(FocusListener listener) {
		focusListeners.addElement(listener);
	}

	protected void notifyFocusGained(int direction) {
		for (int i = 0; i < focusListeners.size(); i++) {
			FocusListener listener = (FocusListener) focusListeners
					.elementAt(i);
			listener.focusGained(direction);
		}
	}

	protected void notifyFocusLost(int direction) {
		for (int i = 0; i < focusListeners.size(); i++) {
			FocusListener listener = (FocusListener) focusListeners
					.elementAt(i);
			listener.focusLost(direction);
		}
	}

	protected void paintFocus(Graphics g) {
		if (hasFocus()) {
			g.setColor(0, 0, 0);
			g.drawRect(1, 1, width - 3, height - 3);
		}
	}

	// public void addEventListener(EventListener listener) {
	// eventListeners.addElement(listener);
	// }

	// private void notifyEventPerformed(int event) {
	// for (int i=0; i<eventListeners.size(); i++) {
	// EventListener listener = (EventListener) eventListeners.elementAt(i);
	// listener.eventPerformed(event);
	// }
	// }

	protected void paintSecondaryLayer(Graphics g) {

	}

	protected void reset() {

	}

	protected void reset(int direction) {

	}

	protected void destroy() {
		parent = null;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(int color) {
		backgroundColor = color;
	}

	public int getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(int color) {
		foregroundColor = color;
	}

	public void paint(Graphics g) {
		Graphics g1 = offscreenBuffer.getGraphics();
		g1.setColor(backgroundColor);
		g1.fillRect(0, 0, width, height);
		paintOffscreenBuffer(g1);
		g.drawImage(offscreenBuffer, x, y, Graphics.TOP | Graphics.LEFT);
	}

	public void setParent(Component component) {
		parent = component;
	}

	public Component getParent() {
		return parent;
	}

	protected abstract void paintOffscreenBuffer(Graphics g);

	public void keyPressed(int keyCode) {
		// if (!eventListeners.isEmpty()) {
		// notifyEventPerformed(keyCode);
		// } else {
		handleEvent(keyCode);
		// }
	}

	protected void handleEvent(int event) {
		if (parent != null) {
			parent.handleEvent(event);
		}
	}
}
