package pe.nasqa.values.control;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageDraw {

	public static BufferedImage resizeImageCargo(BufferedImage originalImage, int width){
		int IMG_WIDTH=width;
		int IMG_HEIGHT=0;
		
		double porcent = originalImage.getHeight() * 100 /originalImage.getWidth();
		IMG_HEIGHT=(int)(IMG_WIDTH * porcent / 100.0);
		
		BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB/*originalImage.getType()*/);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		g.dispose();
	 
		return resizedImage;
    }
}
