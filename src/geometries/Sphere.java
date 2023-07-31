package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Sphere class represents a three-dimensional sphere in 3D Cartesian coordinate system.
 * The sphere is defined by its center point and its radius.
 * The class inherits from the Geometry class.
 *
 * Authors: Noga Jacobs and Noa
 */
public class Sphere extends Geometry {

    /**
     * The center point of the sphere.
     */
    final Point center;

    /**
     * The radius of the sphere.
     */
    final double radius;

    // ***************** Constructor ********************** //

    /**
     * Constructor for creating a sphere with a specified center point and radius.
     *
     * @param center The center point of the sphere.
     * @param radius The radius of the sphere.
     */
    public Sphere(final Point center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    // ***************** Getter ********************** //

    /**
     * Returns the center point of the sphere.
     *
     * @return The center point of the sphere.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the radius of the sphere.
     *
     * @return The radius of the sphere.
     */
    public double getRadius() {
        return radius;
    }


    /**
     * Calculates the normal vector to the sphere at any given point on its surface.
     *
     * @param p1 The point for which to calculate the normal vector.
     * @return The normalized normal vector to the sphere at the specified point.
     */
    public Vector getNormal(Point p1) {
        return p1.subtract(center).normalize();
    }

    // ***************** Override ********************** //

    /**
     * Returns a string representation of the sphere, including its center point and radius.
     *
     * @return A string representation of the sphere.
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }


    /**
     * Finds the intersection points of a ray with the sphere.
     *
     * @param ray The ray for calculating the intersection points.
     * @param maxDistance The maximum distance for valid intersections.
     * @return A list containing GeoPoint objects representing the intersection points of the ray with the sphere.
     * If there are no intersections, the list is null.
     */
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {

        //the data from the ray
        Point p0 = ray.getP0();
        Vector v = ray.getDir().normalize();
        if (center.equals(p0)) {
            return List.of(new GeoPoint(this,center.add(v.scale(radius))));
        }
        Vector u = center.subtract(p0);
        double tm = (v.dotProduct(u));
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        // there are no intersections
        if (alignZero(d - radius) >= 0) {
            return null;
        }
        double th = Math.sqrt((radius * radius) - (d * d));
        double t1 = tm + th;
        double t2 = tm - th;
        // there are no intersections
        if (isZero(th)) {
            return null;
        }
        if (t1 > 0 && t2 > 0  && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0) {

            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);

            return List.of(new GeoPoint(this, p1),new GeoPoint(this, p2));
        }
        if (t1 > 0 && alignZero(t1 - maxDistance)<=0) {

            Point p1 = p0.add(v.scale(t1));
            return List.of(new GeoPoint(this,p1));
        }
        if (t2 > 0 && alignZero(t2 - maxDistance) <=0) {

            Point p2 = p0.add(v.scale(t2));
            return List.of(new GeoPoint(this,p2));
        }
        return null;
    }

}




