package it.seat.milf;

import it.seat.milf.components.FocusContainer;
import it.seat.milf.components.SimpleContainer;

import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

public class MILFController implements CommandListener {
	private static MILFController singleton;

	private MILFMIDlet midlet;
	private MILFCanvas canvas;
	protected Hashtable pages;
	private Vector visitedPages;

	protected Command exitCommand;
	protected Command backCommand;

	public static MILFController getInstance() {
		if (singleton == null) {
			singleton = new MILFController();
		}
		return singleton;
	}

	protected MILFController() {
		pages = new Hashtable();
		visitedPages = new Vector();
		backCommand = new Command("Indietro", Command.BACK, 1);
		exitCommand = new Command("Esci", Command.EXIT, 1);
	}

	protected void setMIDlet(MILFMIDlet midlet) {
		this.midlet = midlet;

		canvas = MILFPageAbstractFactory.getFactory().createCanvas();

		canvas.addCommand(backCommand);
		canvas.addCommand(exitCommand);
		Display display = Display.getDisplay(midlet);
		display.setCurrent(canvas);
		canvas.wakeUp();
	}

	public void switchPage(String pageId) {
		switchPage(pageId, true);
	}

	public void switchPage(String pageId, boolean persistent) {
		FocusContainer newPage = getPage(pageId, persistent);
		switchPage(newPage);
		if (pageId.equals(PageConstants.MAIN_PAGE)) {
			visitedPages.removeAllElements();
		}
		canvas.reset();
	}

	private void switchPage(SimpleContainer newPage) {
		SimpleContainer oldPage = canvas.getPage();
		if (oldPage != null) {
			visitedPages.addElement(oldPage);
		}
		canvas.setPage(newPage);
		if (visitedPages.size() > 5) {
			visitedPages.removeElementAt(0);
		}
	}

	public void previousPage() {
		if (!visitedPages.isEmpty()) {
			FocusContainer previousPage = (FocusContainer) visitedPages.lastElement();
			switchPage(previousPage);
			visitedPages.removeElement(previousPage);
			FocusContainer currentPage = (FocusContainer) visitedPages.lastElement();
			visitedPages.removeElement(currentPage);
			/* distrugge la pagina, se non persistente */
			if (!pages.contains(currentPage)) {
				currentPage.destroy();
				currentPage = null;
			}
		}
	}

	/**
	 * Metodo factory che crea le pagine con inizializzazione pigra.
	 * 
	 * @param pageId
	 *            l'id della pagina da recuperare
	 * @return la pagina creata o in cache
	 */
	private FocusContainer getPage(String pageId, boolean persistent) {
		Object object = pages.get(pageId);
		if (object != null) {
			return (FocusContainer) object;
		}

		FocusContainer page = MILFPageAbstractFactory.getFactory().createPage(pageId, canvas);
		if (persistent) {
			pages.put(pageId, page);
		}

		return page;
	}

	 public String getAppProperty(String key) {
		return midlet.getAppProperty(key);
	}

	 public void openBrowser(String url) {
		 try {
			midlet.platformRequest(url);
//			midlet.destroyApp(true);
//			midlet.notifyDestroyed();
		} catch (ConnectionNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	/**
	 * CommandListener
	 */
	public void commandAction(Command c, Displayable d) {
		if (c == exitCommand) {
			MILFCanvas canvas = (MILFCanvas) Display.getDisplay(midlet)
					.getCurrent();
			canvas.sleep();
			midlet.destroyApp(true);
			midlet.notifyDestroyed();
		} else if (c == backCommand) {
			previousPage();
		}
	}
}
