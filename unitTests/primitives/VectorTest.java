package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);


    @Test
    /**
     *      * A method that tests the constructor
     */
    void testConstuctorZero() {
        // test zero vector
        assertThrows(
                IllegalArgumentException.class,
                ()->{new Vector(0,0,0);},
                "ERROR: zero vector does not throw an exception"
        );
    }

    /**
     *     *      * A method that add vektor   tests the vektor
     */
    @Test
    void testTestAdd() {

        assertEquals(new Vector(-1, -2, -3),
                v1.add(v2),
                "Wrong vector add");
    }
    /**
     *      * A method thatScale   tests the vektor
     */
    @Test
    void testScale() {
        assertEquals(new Vector(2, 4, 6),
                v1.scale(2.0),
                "Wrong vector scale");
    }

    /**
     *      *  **Function of cross Product  test of vector BVT
     */
    @Test
    void testTestDotProduct() {
        // =============== Boundary Values Tests ==================
        assertEquals(0,v1.dotProduct(v3),"ERROR: dotProduct() for orthogonal vectors is not zero");
        assertEquals(-28,v1.dotProduct(v2),"ERROR: dotProduct() wrong value");

    }

    /**
     *  **Function of cross Product  test of vector EP
     */
    @Test
    void testTestCrossProductDP() {
        assertThrows(//test zero vector
                IllegalArgumentException.class,
                ()->v1.crossProduct(v2),
                "ERROR: crossProduct() for parallel vectors does not throw an exception"
        );
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Vector vr = v1.crossProduct(v3);
        assertEquals(v1.length() * v3.length(),vr.length(),0.01,"ERROR: crossProduct() wrong result length");
        assertEquals(0,vr.dotProduct(v1),0.01,"ERROR: crossProduct() result is not orthogonal to its operands");
        assertEquals(0,vr.dotProduct(v3),0.01,"ERROR: crossProduct() result is not orthogonal to its operands");
    }


    /**
     *  **Function of Normalize  test of vector
     */
    @Test
    void testTestNormalize() {
        // test vector normalization vs vector length and cross-product

        Vector u = v1.normalize();
        double temp=v1.dotProduct(u);
        assertEquals(1,u.length() ,"ERROR: the normalized vector is not a unit vector");
        assertThrows(  //test zero vector
                IllegalArgumentException.class,
                ()->v1.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one"
        );
        assertTrue(temp>0,"ERROR: the normalized vector is opposite to the original one");

    }

    /**
     *     **Function of Length  test of vector
     */
    @Test
     void testTestLength() {
        // test length..
        double root = Math.sqrt(14);
        assertEquals(root,v1.length(),"ERROR: length() wrong value");
    }

    /**
     **Function of Length Squared test of vector
     */
    @Test
    void testTestLengthSquared() {
        // test length..
        assertEquals(14,v1.lengthSquared(),"ERROR: lengthSquared() wrong value");
    }
}