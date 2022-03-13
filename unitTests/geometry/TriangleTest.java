package geometry;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point p1=new Point(1,1,1);
        Point p2=new Point(1,2,3);
        Point p3=new Point(2,0,0);
        Triangle tr = new Triangle(p1,p2,p3);
        Vector temp = new Vector(1/Math.sqrt(6),2/Math.sqrt(6),-1/Math.sqrt(6) );
        assertEquals(temp,tr.getNormal(new Point(1, 1, 1)), "getNormal=> Simple test failure");
         }
}