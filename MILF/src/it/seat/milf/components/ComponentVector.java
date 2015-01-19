package it.seat.milf.components;

import java.util.Vector;

public class ComponentVector extends Vector {
	private boolean cyclic;

	public ComponentVector(boolean cyclic) {
		this.cyclic = cyclic;
	}

	boolean isCyclic() {
		return cyclic;
	}

	void setCyclic(boolean cyclic) {
		this.cyclic = cyclic;
	}

	Component componentAt(int index) {
		return (Component) elementAt(index);
	}

	Component lastComponent() {
		return (Component) lastElement();
	}

	Component firstFocusableComponent() {
		for (int i = 0; i < size(); i++) {
			Component first = (Component) elementAt(i);
			if (first.isFocusable()) {
				return first;
			}
		}
		return null;
	}

	Component lastFocusableComponent() {
		for (int i = size() - 1; i >= 0; i--) {
			Component last = (Component) elementAt(i);
			if (last.isFocusable()) {
				return last;
			}
		}
		return null;
	}

	Component previousFocusableComponent(Component component) {
		int index = indexOf(component);
		for (int i = index - 1; i >= 0; i--) {
			Component previousChild = (Component) elementAt(i);
			if (previousChild.isFocusable()) {
				return previousChild;
			}
		}

		if (cyclic) {
			for (int i = size() - 1; i >= index - 1; i--) {
				Component previousChild = (Component) elementAt(i);
				if (previousChild.isFocusable()) {
					return previousChild;
				}
			}
		}

		return null;
	}

	Component nextFocusableComponent(Component component) {
		int index = indexOf(component);
		for (int i = index + 1; i < size(); i++) {
			Component nextChild = (Component) elementAt(i);
			if (nextChild.isFocusable()) {
				return nextChild;
			}
		}

		if (cyclic) {
			for (int i = 0; i <= index + 1; i++) {
				Component nextChild = (Component) elementAt(i);
				if (nextChild.isFocusable()) {
					return nextChild;
				}
			}
		}

		return null;
	}
}
