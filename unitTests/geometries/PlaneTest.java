package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    /**
     * A method that tests the constructor
     */
    @Test
    public void testConstructor()
    {
        // TC01: The points are on the same plane
        Point p1= new Point(1,1,1);
        Point p2= new Point(2,2,2);
        assertThrows(IllegalArgumentException.class,()-> new Plane(p1,p2,p1),"Plane- The first and second points merge");

        // TC02: The points are on the same plane
        Point p3= new Point(3,3,3);
        assertThrows(IllegalArgumentException.class,()-> new Plane(p1,p2,p3),"Plane- The points are on the same plane");


    }

    /**
     *Function of normal test of Plane
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point p1=new Point(1,1,1);
        Point p2=new Point(1,2,3);
        Point p3=new Point(2,0,0);
        Plane pl = new Plane(p1,p2,p3);
        Vector temp = new Vector(1/Math.sqrt(6),2/Math.sqrt(6),-1/Math.sqrt(6) );
        ;//nornal vector
        assertEquals(temp,pl.getNormal(new Point(1, 1, 1)), "getNormal=> Simple test failure");
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(Ray)}.MM
     */
    @Test
    public void testfindIntersectionsRay() {
        Plane pl = new Plane(new Point(0, 0, 1), new Vector(1, 1, 1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray into plane
        assertEquals(List.of(new Point(0, 1, 0)),
                pl.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, 2, 0))),
                "Bad plane intersection");

        // TC02: Ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0, 2), new Vector(0, 0, 1))),
                "Must not be plane intersection");

        // =============== Boundary Values Tests ==================
        // TC11: Ray parallel to plane
        assertNull(pl.findIntersections(new Ray(new Point(2, 1, 1), new Vector(0, 1, -1))),
                "Must not be plane intersection");

        // TC12: Ray in plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 2.5, -1.5), new Vector(0, -2, 2))),
                "Must not be plane intersection");

        // TC13: Orthogonal ray into plane
        assertEquals(List.of(new Point(0, 0, 1)),
                pl.findIntersections(new Ray(new Point(1, 1, 2), new Vector(-1, -1, -1))),
                "Bad plane intersection");

        // TC14: Orthogonal ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(2, 2, 2), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC15: Orthogonal ray out of plane
        assertNull(pl.findIntersections(new Ray(new Point(2, 2, 2), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC16: Orthogonal ray from plane0
        assertNull(pl.findIntersections(new Ray(new Point(-0.33, 0.15, 1.18), new Vector(1, 1, 1))),
                "Must not be plane intersection");

        // TC17: Ray from plane
        assertNull(pl.findIntersections(new Ray(new Point(0, 0.5, 0.5), new Vector(1, 1, 0))),
                "Must not be plane intersection");

        // TC18: Ray from plane's Q point
        assertNull(pl.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 0, 1))),
                "Must not be plane intersection");

    }




}