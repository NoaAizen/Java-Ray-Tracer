package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 *     Function of normal test of Tube
 *
 */
class TubeTest {

    @Test
    void testGetNormal() {
        Point P0 = new Point(0,0,3);
        Point P1 = new Point(1,0,0);
        double r=1;
        Vector vector = new Vector(0,0,-1);
        Tube tube = new Tube(new Ray(P0,vector),r);
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        assertEquals(new Vector(1,0,0),tube.getNormal(P1),"Tube-getNormal=> Simple test failure  ");
        // =============== Boundary Values Tests ==================
        // TC02: The point is in front of the head of the foundation
        Point P2 = new Point(1,0,2);
        assertEquals(new Vector(1,0,0),tube.getNormal(P2),"Tube-getNormal=> Fails when the point is in front of the fund head   ");

    }
}