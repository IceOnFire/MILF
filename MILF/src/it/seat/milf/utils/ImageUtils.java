package it.seat.milf.utils;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class ImageUtils {
	private static ImageUtils singleton;

	public static ImageUtils getInstance() {
		if (singleton == null) {
			singleton = new ImageUtils();
		}
		return singleton;
	}

	public Image rescaleImageW(Image image, int width) {
		float ratio = 1f * width / image.getWidth();
		return rescaleImage(image, width, (int) (image.getHeight() * ratio));
	}

	public Image rescaleImageH(Image image, int height) {
		float ratio = 1f * height / image.getHeight();
		return rescaleImage(image, (int) (image.getWidth() * ratio), height);
	}

	public Image rescaleImage(Image image, int width, int height) {
		int sourceWidth = image.getWidth();
		int sourceHeight = image.getHeight();

		Image newImage = Image.createImage(width, height);
		Graphics g = newImage.getGraphics();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				g.setClip(x, y, 1, 1);
				int dx = x * sourceWidth / width;
				int dy = y * sourceHeight / height;
				g
						.drawImage(image, x - dx, y - dy, Graphics.LEFT
								| Graphics.TOP);
			}
		}
		return Image.createImage(newImage);
	}
}
