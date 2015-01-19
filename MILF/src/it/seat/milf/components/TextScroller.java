package it.seat.milf.components;

import it.seat.milf.MILFConstants;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class TextScroller extends Component {
	private final static int DEFAULT_DELAY_MILLIS = 500;

	private Label label;
	private Mode mode;

	public TextScroller(Label label) {
		super();
		this.label = label;
		setMode(new IdleMode());
		setSize(label.getWidth(), label.getHeight());
	}

	private Label getLabel() {
		return label;
	}

	private void setMode(Mode mode) {
		this.mode = mode;
	}

	protected void paintOffscreenBuffer(Graphics g) {
		mode.update(this);
		paintFocus(g);
		g.setClip(label.getOffset(), label.getOffset() >> 1, width
				- ScrollPane.DEFAULT_WIDTH, height - label.getOffset());
		mode.paint(g, label);
	}

	private static interface Mode {
		public void update(TextScroller ts);

		public void paint(Graphics g, Label l);
	}

	private static class IdleMode implements Mode {
		private int time;

		IdleMode() {
			time = 0;
		}

		public void update(TextScroller ts) {
			if (ts.hasFocus()) {
				time += MILFConstants.MILLISECONDS_PER_FRAME;
				if (ts.getLabel().needsScrolling()
						&& time >= DEFAULT_DELAY_MILLIS && time < MILFConstants.MILLISECONDS_PER_FRAME + DEFAULT_DELAY_MILLIS) {
					ts.setMode(new ScrollMode());
				}
			} else {
				time = 0;
			}
		}

		public void paint(Graphics g, Label l) {
			l.paintOffscreenBuffer(g);
		}
	}

	private static class ScrollMode implements Mode {
		private int amount;

		ScrollMode() {
			amount = 0;
		}

		public void update(TextScroller ts) {
			if (!ts.hasFocus()) {
				ts.setMode(new IdleMode());
			}
		}

		public void paint(Graphics g, Label l) {
			amount++;
			if (amount > Font.getDefaultFont().stringWidth(l.getText())) {
				amount = -Font.getDefaultFont().stringWidth(l.getText());
			}
			g.translate(-amount, 0);
			l.paintOffscreenBuffer(g);
			g.translate(amount, 0);
		}
	}
}
