package it.seat.roma09.frontend;

import it.seat.milf.MILFController;
import it.seat.milf.components.Button;
import it.seat.milf.components.ComboBox;
import it.seat.milf.components.ComboBoxListener;
import it.seat.milf.components.Component;
import it.seat.milf.components.Container;
import it.seat.milf.components.FocusContainer;
import it.seat.milf.components.KeyCodeSelector;
import it.seat.milf.components.Label;
import it.seat.milf.components.ComboBox.Element;
import it.seat.roma09.model.Roma09;

import java.util.Vector;

public class CercaDataPage extends Container implements ComboBoxListener {// ,
	// EventListener
	// {
	private ComboBox giorno, mese;
	private Component button;

	public CercaDataPage(int width, int height) {
		super(width);

		Label titolo = new Label("Inserisci mese e giorno",
				Label.ALIGNMENT_LEFT, width);
		titolo.setForegroundColor(Roma09Constants.BLUE);
		titolo.setFocusable(false);
		add(titolo);

		FocusContainer dataPanel = new FocusContainer(width);

		Vector mesi = new Vector();
		Element luglio = new Element(new Integer(7), "Luglio");
		Element agosto = new Element(new Integer(8), "Agosto");
		mesi.addElement(luglio);
		mesi.addElement(agosto);

		mese = new ComboBox(getWidth() >> 1, mesi);
		mese.addComboBoxListener(this);
		dataPanel.add(mese);

		Label label = new Label(" ", Label.ALIGNMENT_CENTER, getWidth());
		label.setFocusable(false);
		dataPanel.add(label);

		Vector giorni = new Vector();
		Element giornoFasullo = new Element(null, " ");
		giorni.addElement(giornoFasullo);
		giorno = new ComboBox(getWidth() >> 1, giorni, 2);
		dataPanel.add(giorno);
		mese.selectElement();

		label = new Label(" ", Label.ALIGNMENT_CENTER, getWidth());
		label.setFocusable(false);
		dataPanel.add(label);

		button = new Button("Cerca");
		// button.addEventListener(this);
		dataPanel.add(button);

		add(dataPanel, height - titolo.getHeight());
	}

	public void elementSelected(Element element) {
		int mese = ((Integer) element.getKey()).intValue();
		if (mese == 7) {
			Vector giorni = new Vector();
			for (int i = 18; i <= 31; i++) {
				Element giorno = new Element(new Integer(i), "" + i);
				giorni.addElement(giorno);
			}
			giorno.setElements(giorni);
		} else if (mese == 8) {
			Vector giorni = new Vector();
			for (int i = 1; i <= 2; i++) {
				Element giorno = new Element(new Integer(i), "" + i);
				giorni.addElement(giorno);
			}
			giorno.setElements(giorni);
		}
	}

	public void handleEvent(int event) {
		if (event == KeyCodeSelector.KEY_RETURN && button.hasFocus()) {
			Integer g = (Integer) giorno.getSelectedElement().getKey();
			Roma09.getInstance().setGiornoCorrente(g.intValue());
			Integer m = (Integer) mese.getSelectedElement().getKey();
			Roma09.getInstance().setMeseCorrente(m.intValue());
			MILFController.getInstance().switchPage(
					Roma09PageConstants.DATA_PAGE, false);
		} else {
			super.handleEvent(event);
		}
	}
}
