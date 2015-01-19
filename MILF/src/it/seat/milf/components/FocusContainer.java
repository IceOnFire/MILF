package it.seat.milf.components;

import it.seat.milf.MILFController;

import javax.microedition.lcdui.Graphics;

public class FocusContainer extends SimpleContainer {
	protected Component focusedChild;

	public FocusContainer(int width, boolean cyclic) {
		super(width);
		children = new ComponentVector(cyclic);
	}

	public FocusContainer(int width) {
		this(width, false);
	}

	private boolean isCyclic() {
		return children.isCyclic();
	}

	public Component getFocusedChild() {
		return focusedChild;
	}

	public void setFocusedChild(Component component) {
		focusedChild = component;
	}

	public void setFocus(boolean focus) {
		setFocus(focus, DOWN);
	}

	public void setFocus(boolean focus, int direction) {
		super.setFocus(focus, direction);
		if (focus) {
			reset(direction);
		} else {
			for (int i = 0; i < children.size(); i++) {
				children.componentAt(i).setFocus(false, direction);
			}
		}
	}

	protected void reset(int direction) {
		for (int i = 0; i < children.size(); i++) {
			Component component = (Component) children.elementAt(i);
			component.reset(direction);
		}

		if (children.isEmpty() || !hasFocus()) {
			return;
		}

		Component focusableComponent = null;
		if (direction == DOWN) {
			focusableComponent = (Component) children.firstFocusableComponent();
		} else if (direction == UP) {
			focusableComponent = (Component) children.lastFocusableComponent();
		}
		giveFocusTo(focusableComponent, direction);
		focusedChild = focusableComponent;
	}

	protected void paintOffscreenBuffer(Graphics g) {
		super.paintOffscreenBuffer(g);

		/* disegna il layer secondario per gli oggetti che lo prevedono */
		for (int i = 0; i < children.size(); i++) {
			Component component = (Component) children.elementAt(i);
			g.translate(component.getX() - g.getTranslateX(), component.getY()
					- g.getTranslateY());
			component.paintSecondaryLayer(g);
		}
	}

	protected void giveFocusTo(Component component, int direction) {
		if (focusedChild != null) {
			focusedChild.setFocus(false, direction);
		}
		focusedChild = component;
		if (focusedChild != null) {
			focusedChild.setFocus(true, direction);
		}
	}

	protected void focusPreviousWidget() {
		Component previous = children.previousFocusableComponent(focusedChild);
		giveFocusTo(previous, UP);
	}

	protected void focusNextWidget() {
		Component next = children.nextFocusableComponent(focusedChild);
		giveFocusTo(next, DOWN);
	}

	public void keyPressed(int keyCode) {
		if (focusedChild != null) {
			focusedChild.keyPressed(keyCode);
		} else {
			super.keyPressed(keyCode);
		}
	}

	public void handleEvent(int event) {
		if (event == UP
				&& (focusedChild != children.firstFocusableComponent() || isCyclic())) {
			focusPreviousWidget();
		} else if (event == DOWN
				&& (focusedChild != children.lastFocusableComponent() || isCyclic())) {
			focusNextWidget();
		} else if (event == KeyCodeSelector.KEY_BACKSPACE && parent == null) {
			MILFController.getInstance().previousPage();
		} else {
			super.handleEvent(event);
		}
	}

	public void destroy() {
		super.destroy();
		focusedChild = null;
	}
}
