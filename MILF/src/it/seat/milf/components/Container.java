package it.seat.milf.components;

public class Container extends FocusContainer {
	public Container(int width) {
		super(width);
	}

	public void add(FocusContainer container, int height) {
		int fillerHeight = height - container.getHeight();
		if (fillerHeight > 0) {
			SimpleContainer filler = new SimpleContainer(width);
			filler.setSize(width, fillerHeight);
			filler.setBackgroundColor(container.getBackgroundColor());
			filler.setFocusable(false);
			container.add(filler);
			add(container);
		} else {
			ScrollPane scroll = new ScrollPane(container, height);
			addFocusListener(scroll);
			add(scroll);
		}
	}
}
