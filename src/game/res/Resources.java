package game.res;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resources {	//
	
	public static BufferedImage[] letters ;	// BufferedImage is the way that Java store images
	
	static {	// use static as initializer
		
		letters = new BufferedImage[2] ;	// initialize the array
		letters[0] = loadImage("res/x.png") ;	// load image o 
		letters[1] = loadImage("res/o.png") ;	// load image x
	
	}

	private static BufferedImage loadImage (String path) {
		try {
			return ImageIO.read(new FileInputStream(path)) ;	// access the two images that we need
		
		} catch (IOException e) {
			e.printStackTrace() ;
			System.exit(-1) ;	// exit the game if an error occurs
			
		}
		return null;
	}
}
