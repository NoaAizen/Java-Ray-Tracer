package renderer;

import primitives.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.*;

/**
 * Image writer class combines accumulation of pixel color matrix and finally
 * producing a non-optimized jpeg image from this matrix. The class although is
 * responsible for holding image related parameters of View Plane - pixel matrix
 * size and resolution
 *
 * Authors: Noga Jacobs and Noa
 */
public class ImageWriter {


	private int nX;
	private int nY;

	private static final String FOLDER_PATH = System.getProperty("user.dir") + "/images";

	private BufferedImage image;
	private String imageName;
	private Logger logger = Logger.getLogger("ImageWriter");

	// ***************** Constructors ********************** //

	/**
	 * Image Writer constructor accepting image name and View Plane parameters
	 *
	 * @param imageName the name of the PNG file
	 * @param nX        amount of pixels by Width
	 * @param nY        amount of pixels by height
	 */
	public ImageWriter(String imageName, int nX, int nY) {
		this.imageName = imageName;
		this.nX = nX;
		this.nY = nY;

		image = new BufferedImage(nX, nY, BufferedImage.TYPE_INT_RGB);
	}

	// ***************** Getters ********************** //

	/**
	 * Get the number of vertical pixels (resolution along the Y-axis)
	 *
	 * @return The amount of vertical pixels
	 */
	public int getNy() {
		return nY;
	}

	/**
	 * Get the number of horizontal pixels (resolution along the X-axis)
	 *
	 * @return The amount of horizontal pixels
	 */
	public int getNx() {
		return nX;
	}

	// ***************** Operations ******************** //

	/**
	 * Write the accumulated pixel color matrix to a PNG image file
	 */
	public void writeToImage() {
		try {
			File file = new File(FOLDER_PATH + '/' + imageName + ".png");
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "I/O error", e);
			throw new IllegalStateException("I/O error - may be missing directory " + FOLDER_PATH, e);
		}
	}

	/**
	 * Write the color of a specific pixel into the pixel color matrix
	 *
	 * @param xIndex The X-axis index of the pixel
	 * @param yIndex The Y-axis index of the pixel
	 * @param color  The final color of the pixel
	 */
	public void writePixel(int xIndex, int yIndex, Color color) {
		image.setRGB(xIndex, yIndex, color.getColor().getRGB());
	}

	/**
	 * Print a grid on the image by changing the color of pixels at regular intervals
	 *
	 * @param gap           The gap between grid lines
	 * @param intervalColor The color of the grid lines
	 */
	public void printGrid(int gap, Color intervalColor) {
		for (int i = 0; i < nX; i++) {
			for (int j = 0; j < nY; j++) {
				if( i  % gap == 0 || j  % gap ==0){
					writePixel(i,j,intervalColor );
				}
			}
		}
	}
}
