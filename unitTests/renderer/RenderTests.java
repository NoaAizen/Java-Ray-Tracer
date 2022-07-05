package renderer;

import Scene.Scene;
import org.junit.jupiter.api.Test;

import lighting.AmbientLight;
import geometries.*;
import primitives.*;
import renderer.*;


import java.io.File;

import static java.awt.Color.white;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class RenderTests {

	/**
	 * Produce a scene with basic 3D model and render it into a png image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {
		Scene scene = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
				new Double3(1,1,1))) //
				.setBackground(new Color(75, 127, 90)).build();

		scene.getGeometries().add(new Sphere( new Point(0, 0, -100),50),
				new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
																													// left
				new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
																														// left
				new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
																													// right
		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500) //
				.setImageWriter(new ImageWriter("base render test", 1000, 1000))				
				.setRayTracer(new RayTracerBasic(scene));

		camera.renderImage();
		camera.printGrid(100, new Color(java.awt.Color.YELLOW));
		camera.writeToImage();
	}

	/**
	 * Test for XML based scene - for bonus
	 */
	@Test
	public void basicRenderXml() {

		Scene scene = new Scene.SceneBuilder("XML Test scene").loadSceneFromFile(new File("C:/Users/orog1/IdeaProjects/ISE5782_8965_0519/basicRenderTestTwoColors.xml")).build();
		//("C:/Users/orog1/IdeaProjects/ISE5782_8965_0519/basicRenderTestTwoColors.xml")
		// enter XML file name and parse from XML file into scene object


		Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPDistance(100) //
				.setVPSize(500, 500)
				.setImageWriter(new ImageWriter("xml render test", 1000, 1000))
				.setRayTracer(new RayTracerBasic(scene)).setThreadsCount(3).setPrintInterval(0.1);
		camera.renderImage();
		camera.printGrid(100, new Color(java.awt.Color.YELLOW));
		camera.writeToImage();
	}
}
