package geometries;

import static primitives.Util.alignZero;

import java.util.List;

import primitives.*;

/**
 * The Circle class represents a two-dimensional circle in 3D Cartesian coordinate system.
 * It is defined by its center, radius, and an orthogonal unit vector (normal).
 * The Circle class is a type of Geometry.
 * Circle objects are immutable.
 *
 * @author Noga Jacobs and Noa
 */
public class Circle extends Geometry {

    // Fields
    private Point center;   // The center point of the circle
    private double radius;  // The radius of the circle
    private Plane plane;    // The plane containing the circle

    // ***************** Constructor ********************** //

    /**
     * Constructs a Circle given its center, radius, and an orthogonal unit vector (normal).
     * The plane containing the circle is also constructed using these parameters.
     *
     * @param center The center point of the circle
     * @param radius The radius of the circle
     * @param normal The orthogonal vector (will be normalized) that determines the plane of the circle
     */
    public Circle(Point center, double radius, Vector normal) {
        this.center = center;
        this.radius = radius;
        this.plane = new Plane(center, normal);
    }

    // ***************** Getters ********************** //

    /**
     * Returns the radius of the circle.
     *
     * @return The radius of the circle
     */
    public double getRadius() {
        return radius;
    }


    /**
     * Returns the center point of the circle.
     *
     * @return The center point of the circle
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Calculates and returns the normal vector at a given point on the circle.
     * The normal vector is the same as the normal vector of the plane containing the circle.
     *
     * @param point The point on the circle
     * @return The normal vector at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal();
    }

    /**
     * Finds and returns the intersection points between the circle and a given ray,
     * up to a maximum distance from the ray's starting point.
     *
     * @param ray The ray to find intersections with
     * @param maxDistance The maximum distance from the ray's starting point
     * @return A list of intersection points (GeoPoint) with the circle, or null if no intersections are found
     */
    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = plane.findGeoIntersections(ray, maxDistance);
        if (intersections == null)
            return null;

        double pToEdge = alignZero(radius - center.distance(intersections.get(0).point));
        if (pToEdge <= 0)
            return null;

        return intersections;
    }
}