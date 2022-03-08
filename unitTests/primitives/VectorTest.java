package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class VectorTest {

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

    @Test
    void testTestDotProduct() {
    }

    @Test
    void testTestCrossProduct() {
    }

    @Test
    void testTestNormalize() {
    }

    @Test
    void testTestLength() {
    }

    @Test
    void testTestLengthSquared() {
    }
}