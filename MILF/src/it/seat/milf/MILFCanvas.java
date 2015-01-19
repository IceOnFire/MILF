package it.seat.milf;

import it.seat.milf.components.Component;
import it.seat.milf.components.FocusContainer;
import it.seat.milf.components.SimpleContainer;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public class MILFCanvas extends Canvas implements Runnable {
	protected SimpleContainer page;
	private long lastFrameTime = 0;
	private boolean running;

	protected MILFCanvas() {
		super();
		// setFullScreenMode(true);
		setCommandListener(MILFController.getInstance());
	}

	protected SimpleContainer getPage() {
		return page;
	}

	protected void setPage(SimpleContainer page) {
		this.page = page;
	}

	public void run() {
		while (running) {
			// manageEvents();
			repaint();
			synchronizeFrameRate();
		}
	}

	/* i 3 metodi che seguono servono pubblici per WidgetMIDlet */
	public void reset() {
		page.setFocus(true);
	}

	public void wakeUp() {
		running = true;
		new Thread(this).start();
	}

	public void sleep() {
		running = false;
	}

	public int getContentsHeight() {
		return getHeight();
	}

	protected void add(Component component) {
		if (page == null) {
			page = new FocusContainer(getWidth());
		}
		page.add(component);
	}

	protected void insert(Component component, int index) {
		page.insert(component, index);
	}

	protected void remove(Component component) {
		page.remove(component);
	}

	protected void paint(Graphics g) {
		g.setColor(MILFConstants.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		if (page != null) {
			page.paint(g);
		}
	}

	protected void keyPressed(int keyCode) {
		int key = translateKey(keyCode);
		page.keyPressed(key);
	}

	protected int translateKey(int keyCode) {
		int key = keyCode;
		if (keyCode == getKeyCode(UP)) {
			key = Component.UP;
		} else if (keyCode == getKeyCode(DOWN)) {
			key = Component.DOWN;
		} else if (keyCode == getKeyCode(LEFT)) {
			key = Component.LEFT;
		} else if (keyCode == getKeyCode(RIGHT)) {
			key = Component.RIGHT;
		}
		return key;
	}

	protected void keyRepeated(int keyCode) {
		keyPressed(keyCode);
	}

	private void synchronizeFrameRate() {
		long currentTime = System.currentTimeMillis();
		long elapsedTime = currentTime - lastFrameTime;
		lastFrameTime = currentTime;
		if (elapsedTime < MILFConstants.MILLISECONDS_PER_FRAME) {
			try {
				Thread
						.sleep(MILFConstants.MILLISECONDS_PER_FRAME
								- elapsedTime);
			} catch (InterruptedException e) {
			}
		} else {
			Thread.yield();
		}
	}
}
