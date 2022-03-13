package geometry;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class PlaneTest {
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
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point p1=new Point(1,1,1);
        Point p2=new Point(1,2,3);
        Point p3=new Point(2,0,0);
        Plane pl = new Plane(p1,p2,p3);
        Vector temp = new Vector(1/Math.sqrt(6),2/Math.sqrt(6),-1/Math.sqrt(6) );//ויקטור מנורמל
        assertEquals(temp,pl.getNormal(new Point(1, 1, 1)), "getNormal=> Simple test failure");
    }




}