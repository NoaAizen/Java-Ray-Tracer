package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    Point p1 = new Point(1, 2, 3);
    @Test
    void testAdd() {
        assertEquals(
                new Point(0, 0, 0),
                p1.add(new Vector(-1, -2, -3)),
                "ERROR: Point + Vector does not work correctly");
    }

    @Test
    void testSubtract() {
//       assertEquals();
//        Point p1 = new Point(1, 2, 3);
//        if (!new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)))
//            out.println("ERROR: Point - Point does not work correctly");
    }

    @Test
    void testDistanceSquared() {
    }

    @Test
    void testDistance() {
    }
//
//    @Test
//    void add() {
//        Point p1 = new Point(1, 2, 3);
//        if (!(p1.add(new Vector(-1, -2, -3)).equals(new Point(0, 0, 0))))
//            out.println("ERROR: Point + Vector does not work correctly");
//    }
//
//    @Test
//    void subtract() {
//        Point p1 = new Point(1, 2, 3);
//        if (!new Vector(1, 1, 1).equals(new Point(2, 3, 4).subtract(p1)))
//            out.println("ERROR: Point - Point does not work correctly");
//    }
//
//    @Test
//    void distanceSquared() {
//        Point point0 = new Point(1, 1, -100);
//        Point point1 = new Point(-1, 1, -99);
//        Point point2 = new Point(0, 0, -100);
//        Point point3 = new Point(0.5, 0, -100);
//        double resultSquared;
//        double result;
//
//        if(0.25 == point3.distanceSquared(new Point(0,0,-100)))
//            out.println("ERROR: Point - func distanceSquared of Point not work correctly");
//        result = point3.distance(new Point(0,0,-100));
//        System.out.println(result);
//    }
//
//    @Test
//    void distance() {
//
//    }
}