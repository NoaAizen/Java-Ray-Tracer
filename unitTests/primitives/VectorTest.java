package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);


    @Test
    void testConstuctorZero() {
        assertThrows(
                IllegalArgumentException.class,
                ()->{new Vector(0,0,0);},
                "ERROR: zero vector does not throw an exception"
        );
    }

    @Test
    void testTestAdd() {
    }

    @Test
    void testScale() {
    }

    /**
     * מקרה קצה של 0
     */
    @Test
    void testTestDotProductBV() {

        assertEquals(0,v1.dotProduct(v3),"ERROR: dotProduct() for orthogonal vectors is not zero");

    }

    /**
     * מקרה פשוט
     */
    @Test
    void testTestCrossProductDP() {
        assertEquals(-28,v1.dotProduct(v2),"ERROR: dotProduct() wrong value");

    }

    @Test
    void testTestNormalize() {
    }

    @Test
    void testTestLength() {
        double root = Math.sqrt(14);
        assertEquals(root,v1.length(),"ERROR: length() wrong value");
    }

    @Test
    void testTestLengthSquared() {
        assertEquals(14,v1.lengthSquared(),"ERROR: lengthSquared() wrong value");
    }
}