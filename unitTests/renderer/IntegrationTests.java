package renderer;

import static org.junit.jupiter.api.Assertions.*;
import static renderer.CameraTests.ZERO_POINT;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;

import primitives.*;

import java.util.List;

class IntegrationTests {
    /**
     * help function for calculating points of intersection.
     * @param camera
     * @param g
     * @param nx
     * @param ny
     * @return  int
     */
    private int SumPointCross(Camera camera, Intersectable g, int nx, int ny) {
        int counter = 0;
        for (int i = 0; i < ny; i++) {
            for (int j = 0; j < nx; j++) {
                Ray ray = camera.constructRay(nx, ny, j, i);//find ray
                var crossPoint = g.findIntersections(ray);//find Intersections
                if (crossPoint != null)
                    counter += crossPoint.size();
                else
                    counter += 0;//When G is empty
            }
        }
        return counter;
    }

    /**
     *       Tests for Intersection Sphere with camera (tests given in class)
     */
    @Test
    void testIntegrationSphere() {
        //__________________2 Intersection_________
        Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3, 3);
        Sphere sphere = new Sphere(new Point(0, 0, -3), 1);
        assertEquals(SumPointCross(camera, sphere, 3, 3), 2);
        //__________________18 Intersection_________
        Camera camera1 = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3, 3);
        Sphere sphere1 = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(SumPointCross(camera1, sphere1, 3, 3), 18);

        //__________________10 Intersection_________
        Sphere sphere3 = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(SumPointCross(camera1, sphere3, 3, 3), 10);

        //__________________9 Intersection_________
        Sphere sphere4 = new Sphere(new Point(0, 0, -1), 4);
        assertEquals(SumPointCross(camera, sphere4, 3, 3), 9);

        //__________________0 Intersection_________
        Sphere sphere5 = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(SumPointCross(camera, sphere5, 3, 3), 0);

    }

    /**
     * Tests for Intersection plane with camera (tests given in class)
     */
    @Test
    void testIntegrationPalne() {
        //__________________9 Intersection
        Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3, 3);
        Plane plane = new Plane(new Point(0, 0, -9), new Vector(0, 0, 1));
        assertEquals(SumPointCross(camera, plane, 3, 3), 9);
        //__________________6 Intersection_________
        Plane plane2 = new Plane(new Point(0, 1.5, -1), new Vector(0,-6,4.5));
        assertEquals(SumPointCross(camera, plane2, 3, 3), 6);
        //__________________9 Intersection_________
        Plane plane3 = new Plane(new Point(0, 0, -7), new Vector(0,1 ,2 ));
       assertEquals(SumPointCross(camera, plane3, 3, 3), 9);

    }

    /**
     *      *       Tests for Intersection triangle with camera (tests given in class)
     */
    @Test
    void testIntegrationTruangle() {
        //___________________1 intersection________
        Camera camera = new Camera(ZERO_POINT, new Vector(0, 0, -1), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3, 3);
        Triangle triangle = new Triangle(new Point(0, 1, -2),new Point(1, -1, -2),new Point(-1, -1, -2) );
        assertEquals(SumPointCross(camera, triangle, 3, 3), 1);
        //__________________2 Intersection_________
        Triangle triangle1 =new Triangle(new Point(0, 20, -2),new Point(1, -1, -2),new Point(-1, -1, -2));
        assertEquals(SumPointCross(camera, triangle1, 3, 3), 2);

    }
}
