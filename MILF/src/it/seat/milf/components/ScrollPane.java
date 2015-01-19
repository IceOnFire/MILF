package it.seat.milf.components;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class ScrollPane extends Component implements FocusListener {
	public static final int DEFAULT_WIDTH = 10;

	private FocusContainer container;
	private int barWidth;
	private int offset;

	public ScrollPane(FocusContainer container, int height) {
		super();
		this.container = container;
		container.setParent(this);
		barWidth = DEFAULT_WIDTH;
		offset = 0;
		setSize(container.getWidth(), height);
	}

	public void setSize(int width, int height) {
		super.setSize(width, height);
		container.resizeChildren(width - barWidth);
	}

	/* Deleghe al FocusContainer */
	public void add(Component component) {
		container.add(component);
	}

	public void setFocus(boolean focus) {
		container.setFocus(focus);
	}

	public void setFocus(boolean focus, int direction) {
		container.setFocus(focus, direction);
	}

//	public void setParent(Component parent) {
//		container.setParent(parent);
//	}

	public Component getFocusedChild() {
		return container.getFocusedChild();
	}

	public void addFocusListener(FocusListener listener) {
		container.addFocusListener(listener);
	}

	protected void paintOffscreenBuffer(Graphics g) {
		g.translate(0, offset);
		container.paint(g);
		g.translate(0, -offset);

		g.setColor(64, 64, 64);
		g.translate(width - barWidth, 0);
		g.fillRect(0, 0, barWidth, height);

		float ratio = 1f * height / container.getHeight();
		int barHeight = (int) (height * ratio);
		int barOffset = (int) (-offset * ratio);

		g.translate(0, barOffset);
		g.setColor(255, 255, 255);
		g.fillRect(1, 1, barWidth - 2, barHeight - 2);
		g.setColor(128, 128, 128);
		g.fillRect(2, 2, barWidth - 3, barHeight - 3);
		g.setColor(192, 192, 192);
		g.fillRect(2, 2, barWidth - 4, barHeight - 4);
	}

	private void scroll(int amount) {
		offset -= amount;
	}

	private void scrollToChild() {
		Component focusedChild = container.getFocusedChild();
		int childTopLimit = offset + focusedChild.getY();
		int childBottomLimit = childTopLimit + focusedChild.getHeight();
		int containerTopLimit = 0;
		int containerBottomLimit = height;

		if (childTopLimit < containerTopLimit) {
			if (focusedChild.getHeight() <= height)
				scroll(childTopLimit - containerTopLimit);
			else {
				scroll(childBottomLimit - containerBottomLimit);
			}
		} else if (childBottomLimit > containerBottomLimit) {
			if (focusedChild.getHeight() <= height)
				scroll(childBottomLimit - containerBottomLimit);
			else {
				scroll(childTopLimit - containerTopLimit);
			}
		}
	}

	public void keyPressed(int keyCode) {
		int scrollAmount = Font.getDefaultFont().getHeight();
		Component child = container.getFocusedChild();
		if (keyCode == UP) {
			if (offset + child.getY() < 0) {
				if (offset + scrollAmount > 0) {
					scrollAmount = -offset;
				}
				scroll(-scrollAmount);
			} else {
				container.keyPressed(keyCode);
				scrollToChild();
			}
		} else if (keyCode == DOWN) {
			if (offset + child.getY() + child.getHeight() > height) {
				if (offset + container.getHeight() - scrollAmount < height) {
					scrollAmount = offset + container.getHeight() - height;
				}
				scroll(scrollAmount);
			} else {
				container.keyPressed(keyCode);
				scrollToChild();
			}
		} else {
			container.keyPressed(keyCode);
		}
	}

//	public void handleEvent(int event) {
//		int scrollAmount = Font.getDefaultFont().getHeight();
//		Component child = container.getFocusedChild();
//		if (event == UP) {
//			if (offset + child.getY() < 0) {
//				if (offset + scrollAmount > 0) {
//					scrollAmount = -offset;
//				}
//				scroll(-scrollAmount);
//			} else {
//				container.handleEvent(event);
//				scrollToChild();
//			}
//		} else if (event == DOWN) {
//			if (offset + child.getY() + child.getHeight() > height) {
//				if (offset + container.getHeight() - scrollAmount < height) {
//					scrollAmount = offset + container.getHeight() - height;
//				}
//				scroll(scrollAmount);
//			} else {
//				container.handleEvent(event);
//				scrollToChild();
//			}
//		} else {
//			container.handleEvent(event);
//		}
//	}

	/* Override da FocusListener */
	public void focusGained(int direction) {
		if (direction == UP && offset == 0) {
			offset = height - container.getHeight();
		} else if (direction == DOWN
				&& offset + container.getHeight() == height) {
			offset = 0;
		}
	}

	public void focusLost(int direction) {
		// TODO Auto-generated method stub

	}

	public void destroy() {
		super.destroy();
		container.destroy();
	}
}
