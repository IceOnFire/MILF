package it.seat.milf;

import it.seat.milf.components.FocusContainer;

public abstract class MILFPageAbstractFactory {
	private static MILFPageAbstractFactory singleton;

	public static MILFPageAbstractFactory getFactory() {
		if (singleton == null) {
			String className = MILFController.getInstance().getAppProperty(
					PageConstants.PAGE_FACTORY);
			try {
				singleton = (MILFPageAbstractFactory) Class.forName(className)
						.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return singleton;
	}

	public abstract MILFCanvas createCanvas();

	public abstract FocusContainer createPage(String pageId, MILFCanvas canvas);
}
