package geometry;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void testGetNormal() {
        Point P0 = new Point(0,0,3);
        Point P = new Point(1,0,0);
        double r=1;
        Vector vector = new Vector(0,0,-1);
        Tube tube = new Tube(new Ray(P0,vector),r);
        assertEquals(new Vector(1,0,0),tube.getNormal(P),"ERROR  ");




        Point P0 = new Point(0,0,3);
        Point P1 = new Point(1,0,0);
        double r=1;
        Vector vector = new Vector(0,0,-1);
        Tube tube = new Tube(new Ray(P0,vector),r);
        assertEquals(new Vector(1,0,0),tube.getNormal(P1),"ERROR  ");


        Point P2 = new Point(1,0,2);
        assertEquals(new Vector(1,0,0),tube.getNormal(P2),"ERROR   ");

    }
}