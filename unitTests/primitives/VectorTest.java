package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

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
        IllegalArgumentException.class,
                ()->{new Vector(0,0,0);},
                "ERROR: zero vector does not throw an exception"

        if (!isZero(v1.lengthSquared() - 14))
            out.println("ERROR: lengthSquared() wrong value");
    }

    @Test
    void testTestLengthSquared() {
    }
}