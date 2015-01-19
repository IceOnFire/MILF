package it.seat.milf.components;

import java.util.Vector;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class ComboBox extends Component {
	private Vector comboBoxListeners;

	private Element selectedElement;
	private Label selectedElementLabel;

	private Vector elements;
	private ScrollPane elementsContainer;

	private int visibleElements;
	private Mode currentMode;

	public ComboBox(int width, Vector elements) {
		this(width, elements, elements.size());
	}

	public ComboBox(int width, Vector elements, int visibleElements) {
		comboBoxListeners = new Vector();
		this.visibleElements = visibleElements;
		selectedElementLabel = new Label(" ", Label.ALIGNMENT_LEFT, width);
		height = selectedElementLabel.getHeight();
		setSize(width, height);
		offscreenBuffer = Image.createImage(width, height);
		setElements(elements);
	}

	public void addComboBoxListener(ComboBoxListener listener) {
		comboBoxListeners.addElement(listener);
	}

	private void notifyElementSelected(Element element) {
		for (int i = 0; i < comboBoxListeners.size(); i++) {
			ComboBoxListener listener = (ComboBoxListener) comboBoxListeners
					.elementAt(i);
			listener.elementSelected(element);
		}
	}

	public void setElements(Vector elements) {
		this.elements = elements;
		elementsContainer = new ScrollPane(new FocusContainer(width, true),
				visibleElements * height);
		for (int i = 0; i < elements.size(); i++) {
			Element element = (Element) elements.elementAt(i);
			Label elementLabel = new Label(element.getValue(),
					Label.ALIGNMENT_LEFT, width);
			elementsContainer.add(elementLabel);
		}
		reset();
	}

	public void reset() {
		elementsContainer.setFocus(true);
		selectedElement = (Element) elements.firstElement();
		selectedElementLabel.setText(selectedElement.getValue());
		currentMode = new IdleMode();
	}

	public Element getSelectedElement() {
		return selectedElement;
	}

	protected void paintOffscreenBuffer(Graphics g) {
		selectedElementLabel.paint(g);

		g.setColor(64, 64, 64);
		g.drawRect(0, 0, width - 1, height - 1);

		int boxWidth = 10;
		int boxHeight = selectedElementLabel.getHeight() - 2;
		g.translate(width - 1 - boxWidth, 1);
		paintBox(g, boxWidth, boxHeight);
		g.translate(boxWidth - width + 1, -1);

		paintFocus(g);
	}

	private void paintBox(Graphics g, int boxWidth, int boxHeight) {
		g.setColor(255, 255, 255);
		g.fillRect(0, 0, boxWidth, boxHeight);
		g.setColor(128, 128, 128);
		g.fillRect(1, 1, boxWidth - 1, boxHeight - 1);
		g.setColor(192, 192, 192);
		g.fillRect(1, 1, boxWidth - 2, boxHeight - 2);

		int offset = boxHeight / 4;
		g.translate(2, offset);
		g.setColor(64, 64, 64);
		g.fillTriangle(0, 0, boxWidth - 4, 0, (boxWidth - 4) >> 1, boxHeight
				- 2 * offset);
		g.translate(-2, -offset);
	}

	public void paintSecondaryLayer(Graphics g) {
		currentMode.paintSecondaryLayer(g, this);
	}

	public void handleEvent(int event) {
		currentMode.handleEvent(event, this);
	}

	private Element getElement(String value) {
		for (int i = 0; i < elements.size(); i++) {
			Element e = (Element) elements.elementAt(i);
			if (e.getValue().equals(value)) {
				return e;
			}
		}
		return null;
	}

	public void selectElement() {
		currentMode = new IdleMode();
		notifyElementSelected(selectedElement);
	}

	public static class Element {
		private Object key;
		private String value;

		public Element(Object key, String value) {
			this.key = key;
			this.value = value;
		}

		public Object getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}
	}

	private static interface Mode {
		void paintSecondaryLayer(Graphics g, ComboBox c);

		void handleEvent(int event, ComboBox c);
	}

	private static class IdleMode implements Mode {
		public void paintSecondaryLayer(Graphics g, ComboBox c) {
			// do nothing
		}

		public void handleEvent(int event, ComboBox c) {
			if (event == KeyCodeSelector.KEY_RETURN) {
				c.currentMode = new SelectMode();
			} else {
				c.parent.handleEvent(event);
			}
		}
	}

	private static class SelectMode implements Mode {
		public void paintSecondaryLayer(Graphics g, ComboBox c) {
			g.translate(0, c.height);
			c.elementsContainer.paint(g);
			g.setColor(c.foregroundColor);
			g.drawRect(0, 0, c.elementsContainer.getWidth() - 1,
					c.elementsContainer.getHeight() - 1);
			g.translate(0, -c.height);
		}

		public void handleEvent(int event, ComboBox c) {
			if (event == DOWN || event == UP) {
				c.elementsContainer.keyPressed(event);
				Label focusedChild = (Label) c.elementsContainer
						.getFocusedChild();
				c.selectedElement = c.getElement(focusedChild.getText());
				c.selectedElementLabel.setText(c.selectedElement.getValue());
			} else if (event == KeyCodeSelector.KEY_RETURN) {
				c.selectElement();
			}
		}
	}
}
