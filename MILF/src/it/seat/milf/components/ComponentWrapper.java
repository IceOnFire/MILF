package it.seat.milf.components;

import javax.microedition.lcdui.Graphics;

public class ComponentWrapper extends SimpleContainer {
	public ComponentWrapper(int width) {
		super(width);
	}

	protected void paintOffscreenBuffer(Graphics g) {
		super.paintOffscreenBuffer(g);
		paintFocus(g);
	}
}
