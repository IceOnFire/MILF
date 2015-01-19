package it.seat.milf.components;

import javax.microedition.lcdui.Graphics;

public class SimpleContainer extends Component {
	protected ComponentVector children;
	private int childrenHeight;

	public SimpleContainer(int width) {
		this.width = width;
		children = new ComponentVector(false);
		childrenHeight = 0;
	}

	public void add(Component component) {
		int currentHeight = 0;
		if (!children.isEmpty()) {
			Component last = children.lastComponent();
			currentHeight = last.getY() + last.getHeight();
		}
		component.place(0, currentHeight);
		children.addElement(component);

		childrenHeight += component.getHeight();
		if (childrenHeight > height) {
			setSize(width, childrenHeight);
		}

		component.setParent(this);
	}

	public void insert(Component component, int index) {
		int y = 0;
		if (index < children.size()) {
			Component previous = children.componentAt(index);
			y = previous.getY();
		}
		component.place(0, y);
		children.insertElementAt(component, index);
		for (int i = index + 1; i < children.size(); i++) {
			Component next = children.componentAt(i);
			next.move(0, component.getHeight());
		}

		childrenHeight += component.getHeight();
		if (childrenHeight > height) {
			setSize(width, childrenHeight);
		}

		component.setParent(this);
	}

	public void set(Component component, int index) {
		int oldY = 0;
		int oldHeight = 0;
		if (index < children.size()) {
			Component previous = children.componentAt(index);
			oldY = previous.getY();
			oldHeight = previous.getHeight();
		}
		component.place(0, oldY);
		children.setElementAt(component, index);
		int delta = component.getHeight() - oldHeight;
		for (int i = index + 1; i < children.size(); i++) {
			Component next = children.componentAt(i);
			next.move(0, delta);
		}

		childrenHeight += delta;
		if (childrenHeight > oldHeight) {
			setSize(width, childrenHeight);
		}

		component.setParent(this);
	}

	public void remove(Component component) {
		int index = children.indexOf(component);
		children.removeElement(component);
		for (int i = index; i < children.size(); i++) {
			Component next = children.componentAt(i);
			next.move(0, -component.getHeight());
		}

		childrenHeight -= component.getHeight();
		if (childrenHeight > height) {
			setSize(width, childrenHeight);
		}

		component.destroy();
	}

	public boolean isEmpty() {
		return children.size() == 0;
	}

	protected void resizeChildren(int newWidth) {
		for (int i = 0; i < children.size(); i++) {
			Component next = (Component) children.componentAt(i);
			if (next.getWidth() > newWidth) {
				next.setSize(newWidth, next.getHeight());
			}
		}
	}

	protected void paintOffscreenBuffer(Graphics g) {
		for (int i = 0; i < children.size(); i++) {
			Component component = (Component) children.elementAt(i);
			component.paint(g);
		}
	}

	protected void destroy() {
		for (int i = 0; i < children.size(); i++) {
			Component component = (Component) children.elementAt(i);
			component.destroy();
		}
		children.removeAllElements();
	}
}
