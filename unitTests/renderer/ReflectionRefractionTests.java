/**
 *
 */
package renderer;

import geometries.*;
import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import lighting.*;
import geometries.*;
import Scene.Scene;
import primitives.*;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 *
 * @author dzilb
 */
class ReflectionRefractionTests {
	private Scene scene = new Scene.SceneBuilder("Test scene").build();

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(150, 150).setVPDistance(1000);

		scene.getGeometries().add( //
				new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(new Double3(0.3))),
				new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100)));
		scene.getLights().add( //
				new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setkL(0.0004).setkQ(0.0000006));

		camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
		camera.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		Scene scene = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1))).build();

		scene.getGeometries().add( //
				new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20).setkT(new Double3(0.5))),
				new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(new Double3(1))),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(new Double3(0.5))));

		scene.getLights().add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setkL(0.00001).setkQ(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
		camera.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);

		Scene scene = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).build();

		scene.getGeometries().add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)), //
				new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(new Double3(0.6))));

		scene.getLights().add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setkL(4E-5).setkQ(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() ;//
		camera.writeToImage();
	}
	@Test
	public void test() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);
		Scene scene = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).build();

		scene.getGeometries().add( //
				new Triangle(new Point(-40,50, 0), new Point(-30,30,15), new Point(90,40,40))
						.setEmission(new Color(YELLOW)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60).setkT(new Double3(100))),
				new Triangle(new Point(-40,50, 00), new Point(10,70,55), new Point(90,40,40))
						.setEmission(new Color(RED))//
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)),
				new Triangle(new Point(40,-50, 00), new Point(-10,-70,-55), new Point(90,40,40))
						.setEmission(new Color(GREEN))//
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)),
				new Triangle(new Point(40,-50, 00), new Point(-10,-70,-55), new Point(-90,-40,-40))
						.setEmission(new Color(BLUE))//
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)),
				new Triangle(new Point(-40,50, 00), new Point(0,-45,-30), new Point(-85,-35,-35))
						.setEmission(new Color(255,0,255))//
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(60)));


		ImageWriter imageWriter = new ImageWriter("222", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage() ;//
		camera.writeToImage();
	}
	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void test1() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		Scene scene = new Scene.SceneBuilder("Test scene").setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1))).build();

		scene.getGeometries().add( //
				new Triangle(new Point(600, -600, 0),new Point(1000,-600,400),new Point(550,-970,-200)).setEmission(new Color(0, 0, 128)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Triangle(new Point(-950, -900, -1000), new Point(1000,900,1000),new Point(-1000,900,1000)).setEmission(new Color(35,67,55)) //
						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
				new Sphere(new Point(0, 0, 0), 200) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(new Double3(1))),
				new Sphere(new Point(-700, -600, -500), 100) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(new Double3(0.5))),
				new Sphere(new Point(700, 700, 500), 300) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(new Double3(0.5))),
				new Sphere(new Point(950, -700, -200), 50) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(new Double3(1.5))),
				new Sphere(new Point(790, -800, -200), 50) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(new Double3(0.90))),
				new Sphere(new Point(630, -900, -200), 50) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setkR(new Double3(0.5))),
				new Sphere(new Point(1100, -1100, -200), 250) //
						.setEmission(new Color(255, 242, 0)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)),
				new Sphere(new Point(600, 500, -500), 300).setEmission(new Color(white)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(new Double3(0.6))));

		scene.getLights().add(new SpotLight(new Color(white), new Point(600, 500, 0), new Vector(0, 0, -1)) //
				.setkL(4E-5).setkQ(2E-7));


		ImageWriter imageWriter = new ImageWriter("test1", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
		camera.writeToImage();
	}

	@Test
	public void test4() {
		Camera camera = new Camera(new Point(200, 100, 25000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
						.setVPSize(2500, 2500).setVPDistance(10000);
		// ????? ????? ??????
		//Camera camera = new Camera(new Point(0, -15000, 9500), new Vector(0, 4200, 0), new Vector(0, 0, 1)) //
				//		.setVPSize(2500, 2500).setVPDistance(10000);
		// ????? ?????? ?????
		//Camera camera = new Camera(new Point(200, 5000, 9500), new Vector(0, -4200, 0), new Vector(0, 0, 1)) //
		//		.setVPSize(2500, 2500).setVPDistance(10000);
		Scene scene = new Scene.SceneBuilder("Test scene").setBackground(new Color (0,162,232)).build();
		scene.getGeometries().add(
				//right
				new Triangle(new Point(0,-1500,8500), new Point(1500,1000,10000), new Point(0,1000,8500)).setEmission(new Color(white)),
				new Triangle(new Point(0,-1500,8500), new Point(1500,1000,10000), new Point(1500,-1500,10000)).setEmission(new Color(white)),

				//left
				new Triangle(new Point(0,-1500,8500), new Point(-1500,1000,10000), new Point(0,1000,8500)).setEmission(new Color(white)),
				new Triangle(new Point(0,-1500,8500), new Point(-1500,1000,10000), new Point(-1500,-1500,10000)).setEmission(new Color(white)),

				//up
				new Triangle(new Point(0,1000,8500), new Point(-1500,1000,10000), new Point(0,1000,11500)).setEmission(new Color(82,0,0)),
				new Triangle(new Point(0,1000,8500), new Point(1500,1000,10000), new Point(0,1000,11500)).setEmission(new Color(82,0,0)),

				//down
				new Triangle(new Point(0,-1500,8500), new Point(-1500,-1500,10000), new Point(0,-1500,11500)).setEmission(new Color(green)),
				new Triangle(new Point(0,-1500,8500), new Point(1500,-1500,10000), new Point(0,-1500,11500)).setEmission(new Color(green)),

				//lamp middle
				new Triangle(new Point(-500,1000,9450), new Point(-550,1000,9400), new Point(-500,700,9450)).setEmission(new Color(black)),
				new Triangle(new Point(-550,1000,9450), new Point(-550,700,9400), new Point(-500,700,9450)).setEmission(new Color(black)),

				new Sphere(new Point(-525,700,9425),150).setEmission(new Color(yellow)),

				//new Triangle(new Point(-550,580,-150), new Point(-550,280,50), new Point(-250,280,-300)).setEmission(new Color(red)),
				//new Triangle(new Point(-850,280,-50), new Point(-550,280,50), new Point(-550,580,-150)).setEmission(new Color(red)),

				//lamp first
				new Triangle(new Point(-910,700,9800), new Point(-960,700,9850), new Point(-960,1000,9850)).setEmission(new Color(black)),
				new Triangle(new Point(-910,700,9800), new Point(-910,1000,9850), new Point(-960,1000,9850)).setEmission(new Color(black)),

				new Sphere(new Point(-935,700,9825),150).setEmission(new Color(yellow)),

				//lamp last
				new Triangle(new Point(-85,700,8975), new Point(-135,1000,9025), new Point(-85,1000,8975)).setEmission(new Color(black)),
				new Triangle(new Point(-85,700,8975), new Point(-135,1000,9025), new Point(-135,700,9000)).setEmission(new Color(black)),

				new Sphere(new Point(-110,700,9000),150).setEmission(new Color(yellow)),

				//monkey
				new Sphere(new Point(750,-200,9250),200).setEmission(new Color(156,103,73)),
				new Sphere(new Point(900,-200,9400),120).setEmission(new Color(185,122,87)),
				new Sphere(new Point(600,-200,9100),120).setEmission(new Color(185,122,87)),

				//monkey eyes
				new Sphere(new Point(675,-150,9400),40).setEmission(new Color(white)),
				new Sphere(new Point(578,-150,9300),35).setEmission(new Color(white)),

				new Sphere(new Point(665,-150,9435),15).setEmission(new Color(black)),
				new Sphere(new Point(558,-150,9330),13).setEmission(new Color(black))

		);

		scene.getLights().add(new SpotLight(new Color(YELLOW), new Point(-1575, 700, 11275), new Vector(1575,0,-2775)) //
				.setkL(1E-5).setkQ(1.5E-7));


		ImageWriter imageWriter = new ImageWriter("333", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)) //
				.renderImage(); //
		camera.writeToImage();
	}
}


//Monkey background
//new Triangle(new Point(200,650,-400), new Point(1000,750,0), new Point(1000,0,-0)).setEmission(new Color(126,126,126)),
//new Triangle(new Point(230,650,-400), new Point(200,0,0), new Point(1000,-0,0)).setEmission(new Color(126,126,126)),

//Table
//new Triangle(new Point(1000,350,70), new Point(188,0,0), new Point(1000,0,50)).setEmission(new Color(pink)),
//new Triangle(new Point(980,340,300), new Point(188,0,0), new Point(188,300,100)).setEmission(new Color(pink)),

//new Sphere(new Point(0,-1000,0),1000).setEmission(new Color(156,103,73)),

//new Sphere(new Point(-1000,-900,50),550).setEmission(new Color(185,122,87)),

//new Sphere(new Point(1000,-900,50),550).setEmission(new Color(185,122,87)),

//HOME
				 /* *
				 *

				 new Triangle(new Point(300, -50, 500),new Point(-300,-50,500),new Point(300,400,0)).setEmission(new Color(255, 255, 128))
						.setMaterial(new Material().setkR(new Double3(0.90))),
				new Triangle(new Point(-300, -50, 500),new Point(-300,400,0),new Point(300,400,0)).setEmission(new Color(255, 255, 128))
						.setMaterial(new Material().setkR(new Double3(0.90))),S
				new Triangle(new Point(300, -50, 500),new Point(300,400,0),new Point(350,420,0)).setEmission(new Color(255, 255, 128))
						.setMaterial(new Material().setkR(new Double3(0.90))),
				new Triangle(new Point(300, -50, 500),new Point(350,420,0),new Point(350,0,0)).setEmission(new Color(255, 255, 128))
						.setMaterial(new Material().setkR(new Double3(0.90))),
				new Triangle(new Point(0, 600, 420),new Point(-300,400,0),new Point(300,400,0)).setEmission(new Color(RED))
						,
				new Triangle(new Point(0, 600, 420),new Point(350,420,0),new Point(300,400,0)).setEmission(new Color(RED)),
*/
//WATER after
//new Triangle(new Point(-1500, -800, 1500),new Point(1500,-800,2000),new Point(-1500,-1400,1500)).setEmission(new Color(1,191,53)),
//new Triangle(new Point(1500, -1400, 2000),new Point(1500,-800,2000),new Point(-1500,-1400,1500)).setEmission(new Color(1,191,53)),

//WATER BEFUR

//new Triangle(new Point(-1500, -800, -1500),new Point(1500,-800,-2000),new Point(-1500,-1400,-1500)).setEmission(new Color(62,109,234)),
//new Triangle(new Point(1500, -1400, -2000),new Point(1500,-800,-2000),new Point(-1500,-1400,-1500)).setEmission(new Color(62,109,234)),

//for shadow
//new Sphere(new Point(100, 350, 4500), 100).setEmission(new Color(yellow)) //
//		.setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setkT(new Double3(0.3)))
