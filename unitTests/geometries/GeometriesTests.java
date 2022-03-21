package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTests {


    @Test
    void findIntersections() {
        Geometries geometries=new Geometries();

        //___________BVA______________________________
        // TC01: empty collection
        Ray ray=new Ray(new Point(0,0,0),new Vector(1,1,1));
        assertNull(geometries.findIntersections(ray)," empty collection");
        // TC02: zero intersections
        Triangle triangle=new Triangle(new Point(2,4,1), new Point(5,4,3), new Point(5,5,3));
        Sphere sphere = new Sphere(new Point(3,3,3), 1d);
        Plane plane=new Plane(new Point(2,4,1),new Vector(0,0,1));
        geometries.add(triangle,sphere,plane);
        Ray ray1=new Ray(new Point(0,0,0),new Vector(3.55,3.88,0));
        assertNull(geometries.findIntersections(ray1),"the ray dont cross one of  geometries");
        // TC03: one intersection
        Plane p=new Plane(new Point(2,4,1),new Vector(1,0,0));
        geometries.add(p);
        assertEquals(geometries.findIntersections(ray1).size(),1,"Only one shape is cross  of  geometries");
        //___________EP______________________________

        // TC05: partly intersection
        Triangle t=new Triangle(new Point(3,1,-3), new Point(5,4,3), new Point(5,5,3));
        geometries.add(t,p);
        assertEquals(geometries.findIntersections(ray1).size(),2,"Some shapes are  cross  of  geometries");


        //___________BVA______________________________

        // TC04: all shapenews intersection
        Sphere s = new Sphere(new Point(3,3,3), 3.5d);
        geometries.add(s);
        assertEquals(geometries.findIntersections(ray1).size(),4,"All shapes are cross  of  geometries");


    }
}