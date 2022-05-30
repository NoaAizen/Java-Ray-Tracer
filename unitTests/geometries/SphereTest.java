package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    /**
     *     *Function of normal test of Sphere
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere sphere= new Sphere(new Point(0,0,0),1);
        Point p1 = new Point(1,1,1);
        double root = 1/Math.sqrt(3.0);
        assertEquals(new Vector(root,root,root),sphere.getNormal(p1),"Sphere-getNormal=> Simple test failure\" ");
    }
    @Test
   void testFindIntsersections() {
        Sphere sphere = new Sphere(new Point(0, 0, -3), 2d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0, -1.0, -1.2679491924311228);
        Point p2 = new Point(0.0, -1.0, -4.732050807568877);
        List<Point> result = sphere.findIntersections(new Ray(new Point(0, -1, 0), new Vector(0, 0, -1)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(0), result.get(1));
        else
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Point p3 = new Point(0.0, 0.0, -5.0);
        Sphere sphere3 = new Sphere(new Point(0, 0, -2.5), 2.5d);
        List<Point> result3 = sphere3.findIntersections(new Ray(new Point(0, 0, -0.5), new Vector(0, 0, -1)));
        assertEquals(1, result3.size(), "Wrong number of points");
        assertEquals(List.of(p3), result3, "Ray from inside sphere");

        // TC04: Ray starts after the sphere (0 points)
        Sphere sphere4 = new Sphere(new Point(0, 0, 1), 0.5);
        assertNull(
                sphere4.findIntersections(
                        new Ray(new Point(0, 0, -1), new Vector(0, 0, -1))),
                "Sphere behind Ray");
        // =============== Boundary Values Tests ==================
        // ** Group: Ray's line crosses the sphere (but not the center)

        // TC11: Ray starts at sphere and goes inside (1 points) look at TC03
        assertEquals(List.of(p3),
                sphere3.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, 0, -1))),
                "Ray from sphere inside");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(
                sphere.findIntersections(new Ray(new Point(0, 3, -3), new Vector(0, 0, -1))),
                "Ray from sphere outside");

        // ** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points) look at TC02
        List<Point> result2 = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, 0, -1)));
        assertEquals(2, result2.size(), "Wrong number of points");
        if (result2.get(0).getY() > result2.get(1).getY())
            result2 = List.of(result2.get(0), result2.get(1));
        else
            result2 = List.of(result2.get(1), result2.get(0));
        assertEquals(List.of(new Point(0, 0, -1), new Point(0, 0, -5)),
                result2,
                "Line through O, ray crosses sphere");

        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals(List.of(new Point(0, 0, -5)),
                sphere.findIntersections(new Ray(new Point(0, 0, -1), new Vector(0, 0, -1))),
                "Line through O, ray from and crosses sphere");

        // TC15: Ray starts inside (1 points)
        assertEquals(List.of(new Point(0, 0, -5)),
                sphere.findIntersections(new Ray(new Point(0, 0, -4), new Vector(0, 0, -1))),
                "Line through O, ray from inside sphere");

        // TC16: Ray starts at the center (1 points)
        assertEquals(List.of(new Point(0, 0, -5)),
                sphere.findIntersections(new Ray(new Point(0, 0, -3), new Vector(0, 0, -1))),
                "Line through O, ray from O");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(0, 0, -5), new Vector(0, 0, -1))),
                "Line through O, ray from sphere outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(0, 0, -6), new Vector(0, 0, -1))),
                "Line through O, ray outside sphere");


        // ** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(0, 2, 0), new Vector(0, 0, -1))),
                "Tangent line, ray before sphere");

        // TC20: Ray starts at the tangent point-6
        assertNull(sphere.findIntersections(new Ray(new Point(0, 2, -3), new Vector(0, 0, -1))),
                "Tangent line, ray at sphere");

        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(0, 2, -4), new Vector(0, 0, -1))),
                "Tangent line, ray after sphere");

        // ** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's
        // center line
        assertNull(sphere.findIntersections(new Ray(new Point(0, 0, -7), new Vector(0, -1, 0))),
                "Ray orthogonal to ray head -> O line");


    }
}