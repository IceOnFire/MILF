//package it.seat.milf.components;
//import javax.microedition.lcdui.Font;
//
//package it.seat.milf.components;
//
//import it.seat.milf.MILFController;
//
//import java.util.Vector;
//
//import javax.microedition.lcdui.Font;
//import javax.microedition.lcdui.Graphics;
//import javax.microedition.lcdui.Image;
//
///**
// * 
// * @author d6674
// */
//public class OldContainer extends Component {
//	private Vector widgets;
//	private Scrollbar scrollbar;
//
//	private Component focusedChild;
//	private boolean canGiveFocus;
//	private boolean cyclic;
//	private int widgetsHeight;
//
//	public OldContainer(int w, int h, boolean cyclic) {
//		this(w, h);
//		this.cyclic = cyclic;
//	}
//
//	public OldContainer(int w, int h) {
//		super();
//		width = w;
//		height = h;
//		widgets = new Vector();
//		scrollbar = new Scrollbar(this);
//		offscreenBuffer = Image.createImage(width, height);
//
//		canGiveFocus = false;
//		cyclic = false;
//		widgetsHeight = 0;
//	}
//
//	public void setCyclic(boolean cyclic) {
//		this.cyclic = cyclic;
//	}
//
//	/* Appends the specified component to the end of this container */
//	public void add(Component widget) {
//		widget.place(0, widgetsHeight);
//		widgetsHeight += widget.getHeight();
//		widget.setParent(this);
//		widgets.addElement(widget);
//	}
//
//	public void insert(Component widget, int index) {
//		int y = 0;
////		int height = 0;
//		if (index < widgets.size()) {
//			Component previous = (Component) widgets.elementAt(index);
//			y = previous.getY();
////			height = previous.getHeight();
//		}
//		widget.place(0, y);
//		widgetsHeight += widget.getHeight();
//		widget.setParent(this);
//		widgets.insertElementAt(widget, index);
//		for (int i = index + 1; i < widgets.size(); i++) {
//			Component next = (Component) widgets.elementAt(i);
//			next.move(0, widget.getHeight());
//		}
//	}
//
//	public void set(Component widget, int index) {
//		int y = 0;
//		int height = 0;
//		if (index < widgets.size()) {
//			Component previous = (Component) widgets.elementAt(index);
//			y = previous.getY();
//			height = previous.getHeight();
//		}
//		widget.place(0, y);
//		widgetsHeight += widget.getHeight() - height;
//		widget.setParent(this);
//		widgets.setElementAt(widget, index);
//		for (int i = index + 1; i < widgets.size(); i++) {
//			Component next = (Component) widgets.elementAt(i);
//			next.move(0, widget.getHeight() - height);
//		}
//	}
//
////	public void remove(Component widget) {
////		int index = widgets.indexOf(widget);
////		widgets.removeElement(widget);
////		widgetsHeight -= widget.getHeight();
////		for (int i = index; i < widgets.size(); i++) {
////			Component w = (Component) widgets.elementAt(i);
////			w.move(0, -widget.getHeight());
////		}
////		widget.destroy();
////	}
//
//	public Component getFocusedChild() {
//		return focusedChild;
//	}
//
//	public Component getChildAt(int index) {
//		return (Component)widgets.elementAt(index);
//	}
//
//	public int getChildrenNumber() {
//		return widgets.size();
//	}
//
//	int getWidgetsHeight() {
//		return widgetsHeight;
//	}
//
//	int getWidgetsOffset() {
//		Component first = (Component) widgets.firstElement();
//		return first.getY();
//	}
//
//	public void reset() {
//		reset(DOWN);
//		for (int i = 0; i < widgets.size(); i++) {
//			Component widget = (Component) widgets.elementAt(i);
//			widget.reset();
//		}
//	}
//
//	protected void reset(int direction) {
//		for (int i = 0; i < widgets.size(); i++) {
//			Component widget = (Component) widgets.elementAt(i);
//			widget.reset(direction);
//		}
//
//		if (widgets.isEmpty()) {
//			return;
//		}
//
//		if (direction == DOWN) {
//			Component firstWidget = (Component) widgets.firstElement();
//			// SCROLLA TUTTO SU
//			scroll(getWidgetsOffset());
//			scrollTo(firstWidget);
//		} else if (direction == UP) {
//			Component lastWidget = (Component) widgets.lastElement();
//			if (getWidgetsHeight() + getWidgetsOffset() > height) {
//				// SCROLLA TUTTO GIU
//				scroll(getWidgetsOffset() + getWidgetsHeight() - height);
//			}
//			scrollTo(lastWidget);
//		}
//	}
//
//	public void setFocus(boolean focus) {
//		super.setFocus(focus);
//
//		if (widgets.isEmpty()) {
//			return;
//		}
//
//		if (focus) {
//			if (focusedChild == null) {
//				focusedChild = (Component) widgets.firstElement();
//			}
//			focusedChild.setFocus(true);
//		} else {
//			for (int i = 0; i < widgets.size(); i++) {
//				((Component) widgets.elementAt(i)).setFocus(false);
//			}
//			focusedChild.setFocus(false);
//			// QUESTO E' IMPORTANTE
//			canGiveFocus = false;
//		}
//	}
//
//	private boolean isShowing(Component widget) {
//		// Un elemento è visibile se il margine in alto o il margine in basso
//		// sono all'interno del container
//		int topMargin = widget.getY();
//		int bottomMargin = topMargin + widget.getHeight();
//		return topMargin < height || bottomMargin > 0;
//	}
//
//	protected void paintOffscreenBuffer(Graphics g) {
//		// System.out.println("TRANSLATION "+g.getTranslateY());
//
//		g.setClip(0, 0, width, height);
//		for (int i = 0; i < widgets.size(); i++) {
//			Component widget = (Component) widgets.elementAt(i);
//			if (isShowing(widget)) {
//				widget.paint(g);
//			}
//		}
//
//		/* posiziona la scrollbar */
//		if (widgetsHeight > height) {
//			g.translate(width - scrollbar.getWidth(), 0);
//			scrollbar.paint(g);
//			g.translate(scrollbar.getWidth() - width, 0);
//		}
//
//		/* disegna il layer secondario per gli oggetti che lo prevedono */
//		for (int i = 0; i < widgets.size(); i++) {
//			Component widget = (Component) widgets.elementAt(i);
//			g.translate(widget.getX() - g.getTranslateX(), widget.getY()
//					- g.getTranslateY());
//			if (isShowing(widget)) {
//				widget.paintSecondaryLayer(g);
//			}
//		}
//	}
//
//	private void scrollTo(Component widget) {
//		if (focusedChild != null) {
//			focusedChild.setFocus(false);
//		}
//		focusedChild = widget;
//
//		int widgetTopLimit = focusedChild.getY();
//		int containerTopLimit = 0;
//		int widgetBottomLimit = focusedChild.getY() + focusedChild.getHeight();
//		int containerBottomLimit = height;
//
//		if (widgetTopLimit < containerTopLimit) {
//			// SCROLL UP
//			if (focusedChild.getHeight() <= height)
//				scroll(widgetTopLimit - containerTopLimit);
//			else {
//				scroll(widgetBottomLimit - containerBottomLimit);
//				// System.out.println("Scroll UP - PIU GRANDE");
//			}
//		} else if (widgetBottomLimit > containerBottomLimit) {
//			// SCROLL DOWN
//			if (focusedChild.getHeight() <= height)
//				scroll(widgetBottomLimit - containerBottomLimit);
//			else {
//				scroll(widgetTopLimit - containerTopLimit);
//				// System.out.println("Scroll DOWN - PIU GRANDE");
//			}
//		}
//	}
//
//	private void giveFocusTo(Component widget) {
//		scrollTo(widget);
//		widget.setFocus(true);
//	}
//
//	void focusNextWidget() {
//		int i = widgets.indexOf(focusedChild);
//		int limit = widgets.size();
//		if (!cyclic) {
//			limit -= i;
//		}
//		for (int j = 1; j < limit; j++) {
//			int index = i + j;
//			if (index >= widgets.size()) {
//				index -= widgets.size();
//			}
//			Component nextChild = (Component) widgets.elementAt(index);
//			if (nextChild.isFocusable()) {
//				nextChild.reset(DOWN);
//				giveFocusTo(nextChild);
//				return;
//			}
//		}
//	}
//
//	void focusPreviousWidget() {
//		int i = widgets.indexOf(focusedChild);
//		int limit = widgets.size();
//		if (!cyclic) {
//			limit = i;
//		}
//		for (int j = 1; j <= limit; j++) {
//			Component nextChild;
//			int index = i - j;
//			if (index < 0) {
//				index += widgets.size();
//			}
//			nextChild = (Component) widgets.elementAt(index);
//			if (nextChild.isFocusable()) {
//				nextChild.reset(UP);
//				giveFocusTo(nextChild);
//				return;
//			}
//		}
//	}
//
//	public void keyPressed(int keyCode) {
//		if (focusedChild != null) {
//			focusedChild.keyPressed(keyCode);
//		} else {
//			super.keyPressed(keyCode);
//		}
//	}
//
//	public void handleEvent(int event) {
//		// System.out.println("KEY PRESSED " + event);
//
//		updateCanGiveFocus(event);
//		int scrollAmount = Font.getDefaultFont().getHeight();
//		if (event == DOWN) {
//			if (!focusedChild.canShowBottom()) {
//				if (widgetsHeight + getWidgetsOffset() - scrollAmount < height) {
//					scrollAmount = widgetsHeight + getWidgetsOffset() - height;
//				}
//				scroll(scrollAmount);
//			} else if (!canGiveFocus) {
//				focusNextWidget();
//			}
//		} else if (event == UP) {
//			if (!focusedChild.canShowTop()) {
//				if (getWidgetsOffset() + scrollAmount > 0) {
//					scrollAmount = -getWidgetsOffset();
//				}
//				scroll(-scrollAmount);
//			} else if (!canGiveFocus) {
//				focusPreviousWidget();
//			}
//		} else if (event == KeyCodeSelector.KEY_BACKSPACE && parent == null) {
//			MILFController.getInstance().previousPage();
//		} else if (parent != null) {
//			parent.handleEvent(event);
//		}
//
//		if (event == DOWN) {
//			focusNextWidget();
//		} else if (event == UP) {
//			focusPreviousWidget();
//		} else if (event == KeyCodeSelector.KEY_BACKSPACE && parent == null) {
//			MILFController.getInstance().previousPage();
//		} else {
//			super.handleEvent(event);
//		}
//	}
//
////	protected void updateCanGiveFocus(int direction) {
////		focusedChild.updateCanGiveFocus(direction);
////		if (cyclic) {
////			canGiveFocus = false;
////		} else if (direction == UP && focusedChild == widgets.firstElement()
////				&& focusedChild.canShowTop()) {
////			canGiveFocus = focusedChild.canGiveFocus();
////		} else if (direction == DOWN && focusedChild == widgets.lastElement()
////				&& focusedChild.canShowBottom()) {
////			canGiveFocus = focusedChild.canGiveFocus();
////		} else {
////			canGiveFocus = false;
////		}
////	}
//
//	void scroll(int amount) {
//		for (int i = 0; i < widgets.size(); i++) {
//			((Component) widgets.elementAt(i)).move(0, -amount);
//		}
//	}
//
//	public boolean canGiveFocus() {
//		return canGiveFocus;
//	}
//
//	public void destroy() {
//		for (int i = 0; i < widgets.size(); i++) {
//			Component widget = (Component) widgets.elementAt(i);
//			widget.destroy();
//		}
//		widgets.removeAllElements();
//		focusedChild = null;
//	}
//}
//
//private void shift(int direction) {
//		int scrollAmount = Font.getDefaultFont().getHeight();
//		int widgetsHeight = parent.getWidgetsHeight();
//		int widgetsOffset = parent.getWidgetsOffset();
//		if (direction == DOWN) {
//			if (widgetsHeight + widgetsOffset - scrollAmount < parent.getHeight()) {
//				scrollAmount = widgetsHeight + widgetsOffset - parent.getHeight();
//			}
//			parent.scroll(scrollAmount);
//		} else if (direction == UP) {
//			if (widgetsOffset + scrollAmount > 0) {
//				scrollAmount = -widgetsOffset;
//			}
//			parent.scroll(-scrollAmount);
//		}
//	}
//private boolean isShowing(Component widget) {
//		// Un elemento è visibile se il margine in alto o il margine in basso
//		// sono all'interno del container
//		int topMargin = widget.getY();
//		int bottomMargin = topMargin + widget.getHeight();
//		return topMargin < height || bottomMargin > 0;
//	}