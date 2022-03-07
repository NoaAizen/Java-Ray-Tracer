package geometry;
import java.util.List;
import primitives.*;
import static primitives.Util.*;
import primitives.Point;
/**
 * Triangle class Vertex polygon
 * system
 *
 * @author noga and noa
 */
public class Triangle extends Polygon {

    /**
     * constctor using super main using Polygon
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }

    @Override
    public  String toString() {
        return "Triangle{}";
    }
}
