package renderer;
import geometries.*;
import org.junit.jupiter.api.Test;
import static java.awt.Color.*;
import lighting.*;
import geometries.*;
import Scene.Scene;
import primitives.*;

public class ProjectTest {
    @Test
    public void ProjectTestS() {
        Camera camera = new Camera(new Point(0,-250, 22400), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000).setThreadsCount(3).setPrintInterval(0.1);;
        // look from down to up
        //Camera camera = new Camera(new Point(-300, -15000, 9500), new Vector(0, 4200, 0), new Vector(0, 0, 1)) //
        //	.setVPSize(2500, 2500).setVPDistance(10000);
        // look from up to down
        //Camera camera = new Camera(new Point(200, 20000, 9500), new Vector(0, -4200, 0), new Vector(0, 0, 1)) //
        //	.setVPSize(2500, 2500).setVPDistance(10000).setThreadsCount(3).setPrintInterval(0.1);;
        Scene scene = new Scene.SceneBuilder("Test scene").setBackground(new Color(0,162,232)).setAmbientLight(new AmbientLight(new Color(white),new Double3(0.01))).build();
        scene.getGeometries().add(

                //right
                new Triangle(new Point(0,-1500,8500), new Point(1500,1000,10000), new Point(0,1000,8500)).setEmission(new Color(red)).setMaterial(new Material().setkT(1).setkD(0.8).setkS(0.6).setnShininess(150)),
                new Triangle(new Point(0,-1500,8500), new Point(1500,1000,10000), new Point(1500,-1500,10000)).setEmission(new Color(orange)).setMaterial(new Material().setkT(1).setkD(0.8).setkS(0.6).setnShininess(150)),

                //left
                new Triangle(new Point(0,-1500,8500), new Point(-1500,1000,10000), new Point(0,1000,8500)).setEmission(new Color(gray)).setMaterial(new Material().setkS(0.4).setkT(0.4).setkD(0.2).setnShininess(150)),
                new Triangle(new Point(0,-1500,8500), new Point(-1500,1000,10000), new Point(-1500,-1500,10000)).setEmission(new Color(gray)).setMaterial(new Material().setkS(0.4).setkT(0.4).setkD(0.2).setnShininess(150)),

                //meror
                new Triangle(new Point(0,100,8500), new Point(-1200,-700,9750), new Point(0,-700,8500)).setEmission(new Color(blue)).setMaterial(new Material().setkR(0.2).setkD(0.3).setkS(0.4).setkT(0.8).setnShininess(150)),
                new Triangle(new Point(0,100,8500), new Point(-1200,-700,9750), new Point(-1200,100,9750)).setEmission(new Color(blue)).setMaterial(new Material().setkR(0.2).setkD(0.3).setkS(0.4).setkT(0.8).setnShininess(150)),

                //perfume - busam
                new Sphere(new Point(-500,-642,9950),50).setEmission(new Color(white)),
                new Sphere(new Point(-500,-582,9950),30).setEmission(new Color(white)),
                new Sphere(new Point(-500,-582,9980),7).setEmission(new Color(black)),

                new Sphere(new Point(-600,-652,9950),30).setEmission(new Color(white)),
                new Sphere(new Point(-600,-625,9950),30).setEmission(new Color(white)),
                new Sphere(new Point(-600,-595,9950),30).setEmission(new Color(white)),
                new Sphere(new Point(-600,-565,9950),30).setEmission(new Color(yellow)),

                //perfume - busam
                new Sphere(new Point(-100,-642,8900),50).setEmission(new Color(white)),
                new Sphere(new Point(-100,-582,8900),30).setEmission(new Color(white)),
                new Sphere(new Point(-100,-582,8900),7).setEmission(new Color(black)),

                new Sphere(new Point(35,-652,8800),30).setEmission(new Color(white)),
                new Sphere(new Point(35,-625,8800),30).setEmission(new Color(white)),
                new Sphere(new Point(35,-595,8800),30).setEmission(new Color(white)),
                new Sphere(new Point(35,-565,8800),30).setEmission(new Color(green)),

                //bad - up
                new Triangle(new Point(0,-700,8500), new Point(-1200,-700,9700), new Point(500,-700,9000)).setEmission(new Color(black)),
                new Triangle(new Point(-667,-700,10200), new Point(-1200,-700,9700), new Point(500,-700,9000)).setEmission(new Color(black)),

                //boll
                new Sphere(new Point(-980,-1300,9950),200).setEmission(new Color(red)),
                //boll
                new Sphere(new Point(-900,-1350,10300),150).setEmission(new Color(white)),

                //chair - up
                new Triangle(new Point(-150,-1000,9150), new Point(-560,-1000,9575), new Point(350,-1000,9650)).setEmission(new Color(white)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)),
                new Triangle(new Point(-50,-1000,10075), new Point(-560,-1000,9575), new Point(350,-1000,9650)).setEmission(new Color(white)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)),

                //chair - leg
                new Triangle(new Point(-150,-1000,9150), new Point(-150,-1500,9150), new Point(-170,-1500,9200)).setEmission(new Color(white)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)),
                new Triangle(new Point(-150,-1000,9150), new Point(-170,-1500,9200), new Point(-170,-1000,9200)).setEmission(new Color(white)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)),

                //chair - leg
                new Triangle(new Point(-50,-1000,10075), new Point(-50,-1500,10075), new Point(-30,-1500,10055)).setEmission(new Color(white)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)),
                new Triangle(new Point(-50,-1000,10075), new Point(-30,-1000,10055), new Point(-30,-1500,10055)).setEmission(new Color(white)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)),

                //chair - leg
                new Triangle(new Point(-560,-1000,9575), new Point(-560,-1500,9575), new Point(-530,-1000,9575)).setEmission(new Color(white)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)),
                new Triangle(new Point(-560,-1500,9575), new Point(-530,-1500,9575), new Point(-530,-1000,9575)).setEmission(new Color(white)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)),

                //chair - leg
                new Triangle(new Point(300,-1000,9650), new Point(300,-1500,9650), new Point(330,-1500,9650)).setEmission(new Color(white)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)),
                new Triangle(new Point(300,-1000,9650), new Point(330,-1500,9650), new Point(330,-1000,9650)).setEmission(new Color(white)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30)),

                //bad - leg
                new Triangle(new Point(-647,-1500,10150), new Point(-677,-1500,10200), new Point(-677,-700,10200)).setEmission(new Color(blue)),
                new Triangle(new Point(-647,-1500,10150), new Point(-647,-700,10150), new Point(-677,-700,10200)).setEmission(new Color(blue)),

                //bad - leg
                new Triangle(new Point(-1220,-1500,9700), new Point(-1220,-700,9700), new Point(-1170,-700,9700)).setEmission(new Color(blue)),
                new Triangle(new Point(-1220,-1500,9700), new Point(-1170,-1500,9700), new Point(-1170,-700,9700)).setEmission(new Color(blue)),

                //bad - leg
                new Triangle(new Point(20,-1500,8510), new Point(20,-700,8510), new Point(-20,-700,8510)).setEmission(new Color(blue)),
                new Triangle(new Point(20,-1500,8510), new Point(-20,-1500,8510), new Point(-20,-700,8510)).setEmission(new Color(blue)),

                //bad - leg
                new Triangle(new Point(500,-1500,9000), new Point(500,-700,9000), new Point(470,-700,9000)).setEmission(new Color(blue)),
                new Triangle(new Point(470,-1500,9000), new Point(500,-1500,9000), new Point(470,-700,9000)).setEmission(new Color(blue)),

                //up
                new Triangle(new Point(0,1000,8500), new Point(-1500,1000,10000), new Point(0,1000,11500)).setEmission(new Color(30,30,30)).setMaterial(new Material().setkT(1).setnShininess(50)),
                new Triangle(new Point(0,1000,8500), new Point(1500,1000,10000), new Point(0,1000,11500)).setEmission(new Color(30,30,30)).setMaterial(new Material().setkT(1).setnShininess(50)),

                //down
                new Triangle(new Point(0,-1500,8500), new Point(-1500,-1500,10000), new Point(0,-1500,11500)).setEmission(new Color(235,235,235)).setMaterial(new Material().setkT(1).setnShininess(50)),
                new Triangle(new Point(0,-1500,8500), new Point(1500,-1500,10000), new Point(0,-1500,11500)).setEmission(new Color(235,235,235)).setMaterial(new Material().setkT(1).setnShininess(50)),

                //lamp middle
                new Triangle(new Point(-500,1000,9450), new Point(-550,1000,9400), new Point(-500,880,9450)).setEmission(new Color(yellow)),
                new Triangle(new Point(-550,1000,9450), new Point(-550,880,9400), new Point(-500,880,9450)).setEmission(new Color(yellow)),

                new Sphere(new Point(-525,730,9425),150).setEmission(new Color(yellow)).setMaterial(new Material().setkT(1).setnShininess(50)),

                //lamp first
                new Triangle(new Point(-910,880,9800), new Point(-960,880,9850), new Point(-960,1000,9850)).setEmission(new Color(yellow)),
                new Triangle(new Point(-910,880,9800), new Point(-910,1000,9850), new Point(-960,1000,9850)).setEmission(new Color(yellow)),

                new Sphere(new Point(-935,730,9825),150).setEmission(new Color(yellow)).setMaterial(new Material().setkT(1).setnShininess(50)),

                //lamp last
                new Triangle(new Point(-85,880,8975), new Point(-135,1000,9025), new Point(-85,1000,8975)).setEmission(new Color(yellow)),
                new Triangle(new Point(-85,880,8975), new Point(-135,1000,9025), new Point(-135,880,9000)).setEmission(new Color(yellow)),

                new Sphere(new Point(-110,730,9000),150).setEmission(new Color(yellow)).setMaterial(new Material().setkT(1).setnShininess(50)),
                //monkey
                new Sphere(new Point(910,-600,9700),200).setEmission(new Color(156,103,73)),
                new Sphere(new Point(1060,-600,9800),120).setEmission(new Color(185,122,87)),
                new Sphere(new Point(760,-600,9600),120).setEmission(new Color(185,122,87)),

                new Sphere(new Point(870,-1050,9720),285).setEmission(new Color(156,103,73)),
                new Sphere(new Point(870,-1150,9720),285).setEmission(new Color(156,103,73)),
                new Sphere(new Point(650,-1380,9750),120).setEmission(new Color(black)),
                new Sphere(new Point(950,-1380,9950),120).setEmission(new Color(black)),
                //right
                new Sphere(new Point(670,-980,9550),120).setEmission(new Color(black)),
                new Sphere(new Point(620,-930,9530),120).setEmission(new Color(black)),
                new Sphere(new Point(570,-900,9510),120).setEmission(new Color(black)),
                //left
                new Sphere(new Point(1100,-980,9870),120).setEmission(new Color(black)),
                new Sphere(new Point(1140,-1020,9950),120).setEmission(new Color(black)),
                new Sphere(new Point(1190,-1050,10010),120).setEmission(new Color(black)),

                //monkey eyes
                new Sphere(new Point(860,-550,9865),40).setEmission(new Color(white)),
                new Sphere(new Point(750,-550,9780),35).setEmission(new Color(white)),

                new Sphere(new Point(860,-550,9900),17).setEmission(new Color(black)),
                new Sphere(new Point(735,-550,9810),15).setEmission(new Color(black)),

                new Sphere(new Point(805,-650,9830),35).setEmission(new Color(red)),
                new Sphere(new Point(795,-652,9850),15).setEmission(new Color(white))

                //new Sphere(new Point(-150,-1000,12150),100).setEmission(new Color(BLUE)).setEmission(new Color(BLUE)).setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(30))


        );
        //(new Point(870,-1050,9720),
        scene.getLights().add(
                new SpotLight(new Color(400, 240, 0), new Point(350,-1200,12050),new Vector(-130,700,-2100)).setkL(1E-5).setkQ(1.5E-7) );
        scene.getLights().add(
                new PointLight(new Color(255,197,143),new Point(-525,700,9425)).setkL(0.0004).setkQ(0.000006));
        scene.getLights().add(
                new PointLight(new Color(255,197,143),new Point(-935,700,9825)).setkL(0.0004).setkQ(0.000006));
        scene.getLights().add(
                new PointLight(new Color(255,197,143),new Point(-110,700,9000)).setkL(0.0004).setkQ(0.000006));

        ImageWriter imageWriter = new ImageWriter("shadown 20", 800,800);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage(); //
        camera.writeToImage();
    }
}
