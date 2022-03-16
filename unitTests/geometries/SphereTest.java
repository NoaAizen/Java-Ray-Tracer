package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

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

        Sphere sphere = new Sphere(new Point (1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p21 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p22= new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result2 = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result2.size(), "Wrong number of points");
        if (result2.get(0).getX() > result2.get(1).getX())
            result2 = List.of(result2.get(1), result2.get(0));
        assertEquals(List.of(p21, p22), result2, "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        Point p31 = new Point(0.72650,0.58975,0.58975);
        Point p32 = new Point(-0.8175,-0.5675,-0.5675);
        List<Point> result3 = sphere.findIntersections(new Ray(new Point(0.5, 0.25, 0.25),
                new Vector(0.5, 0.75, 0.75)));

        assertEquals(1, result3.size(), "Wrong number of points");
        assertEquals(List.of(p31), result3, "Ray crosses sphere in the right direction");
        assertEquals(List.of(p32), result3, "ERROR: Ray crosses sphere in the opposite direction");
        // TC04: Ray starts after the sphere (0 points)


        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        // TC12: Ray starts at sphere and goes outside (0 points)

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        // TC14: Ray starts at sphere and goes inside (1 points)
        // TC15: Ray starts inside (1 points)
        // TC16: Ray starts at the center (1 points)
        // TC17: Ray starts at sphere and goes outside (0 points)
        // TC18: Ray starts after sphere (0 points)

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        // TC20: Ray starts at the tangent point
        // TC21: Ray starts after the tangent point

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line

    }

}