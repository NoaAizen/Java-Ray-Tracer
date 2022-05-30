package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class check class Point
 */
class PointTest {
    Point p1 = new Point(1, 2, 3);

    @Test
    /**
     * check func add from point.
     *  Point (1-1, 2-2, 3-3).
     */
    void testAdd() {
        assertEquals(
                new Point(0, 0, 0),
                p1.add(new Vector(-1, -2, -3)),
                "ERROR: Point + Vector does not work correctly");
    }

    @Test
    /**
     * check func subtract from point.
     * Vector(2-1, 3-2, 4-3)
     */
    void testSubtract() {
        assertEquals(
                new Vector(1, 1, 1),
                new Point(2, 3, 4).subtract(p1),
                "ERROR: Point - testSubtract does not work correctly");
    }

    @Test
    /**
     * check func distanceSquared from point.
     * double ((1-1)*(1-1)+(2-1)*(2-1)+(3-1)*(3-1)=5)
     */
    void testDistanceSquared() {
        assertEquals(
                5,
                new Point(1, 1, 1).distanceSquared(p1),
                "ERROR: Point - testDistanceSquared does not work correctly");
    }

    @Test
    /**
     * check func distance from point.
     * use distanceSquared in this func  ((1-1)*(1-1)+(2-2)*(2-2)+(3-1)*(3-1)=4)
     * and after that use math sqrt on 4
     */
    void testDistance() {
        assertEquals(
                2.0,
                new Point(1, 2, 1).distance(p1),
                "ERROR: Point - testDistance does not work correctly");
    }
}