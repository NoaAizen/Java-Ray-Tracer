package geometry;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void testGetNormal() {
        Sphere sphere= new Sphere(new Point(0,0,0),1);
        Point p1 = new Point(1,1,1);
        double root = 1/Math.sqrt(3.0);
        assertEquals(new Vector(root,root,root),sphere.getNormal(p1),"ERROR ");
    }
}