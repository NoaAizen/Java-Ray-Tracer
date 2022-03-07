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
     * constctor using super main using Polygon
     * @param p1
     * @param p2
     * @param p3
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
     * @param p
     * @return vector
     */
    public Vector getNormal(Point p){
        return null;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }
}
