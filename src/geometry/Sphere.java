package geometry;
import java.util.List;

import primitives.*;
import static primitives.Util.*;
/**
 * Triangle class Vertex polygon
 * system
 *
 * @author noga and noa
 */
public class Sphere implements Geometry {

    Point center;
    double radius;

    /**
     *
     * @param center
     * @param radius
     */
    public Sphere(final Point center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * get the point center
     * @return point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * get the radius center
     * @return double
     */
    public double getRadius() {
        return radius;
    }

    /**
     * return vector normal
     * @param p1
     * @return vector
     */
    public Vector getNormal(Point p1){
        return p1.subtract(center).normalize();
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
