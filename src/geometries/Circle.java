package geometries;

import static primitives.Util.alignZero;

import java.util.List;

import primitives.*;

/**
 * Circle class represents Euclidean 2D circle in 3D Cartesian coordinate system
 * represented by its center, radius and orthogonal unit vector
 *
 * @author Dan
 */
public class Circle extends Geometry {
    private Point o;
    private double radius;
    private Plane plane;

    /**
     * Circle constructor given its center, radius and orthogonal unit vector
     * (normal)
     *
     * @param o      center point
     * @param radius circle radius
     * @param n      orthogonal vector (will be normalized)
     */
    public Circle(Point o, double radius, Vector n) {
        this.o = o;
        this.radius = radius;
        plane = new Plane(o, n);
    }

    /**
     * Radius getter for the circle
     *
     * @return the radius
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Center getter for the circle
     *
     * @return the center point
     */
    public Point getO() {
        return o;
    }

    @Override
    public Vector getNormal(Point p) {
        return plane.getNormal();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = plane.findGeoIntersections(ray, maxDistance);
        if (intersections == null)
            return null;

        double pToEdge = alignZero(radius - o.distance(intersections.get(0).point));
        if (pToEdge <= 0)
            return null;
        return intersections;
    }
}