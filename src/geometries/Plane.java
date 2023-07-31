package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The Plane class represents a plane in 3D Cartesian coordinate system.
 * A plane is defined by a point on the plane (p0) and a normal vector (normal).
 * The normal vector should be normalized automatically upon construction.
 * The class inherits from the Geometry class.
 *
 * Authors: Noga Jacobs and Noa
 */

public class Plane extends Geometry{
    /**
     * The point on the plane (origin).
     */
    final Point p0;
    /**
     * The normalized normal vector to the plane.
     */
    final Vector normal;

    // ***************** Constructor ********************** //

    /**
     * Constructs a plane using a point on the plane and the normal vector.
     *
     * @param q0 The point on the plane.
     * @param n  The normal vector to the plane (will be normalized automatically).
     */
    public Plane(Point q0, Vector n) {
        p0 = q0;
        normal = n;
    }

    /**
     * Constructs a plane using three points to calculate the normal vector.
     * The normal vector will be normalized automatically.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @param p3 The third point.
     */
    public Plane(Point p1, Point p2, Point p3) {
        p0 =p1;
        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        Vector N = U.crossProduct(V);


         normal =  N.normalize();

    }

    // ***************** Getter ********************** //

    /**
     * Returns the origin point of the plane.
     *
     * @return The origin point.
     */
    public Point getQ0()
    {
        return p0;
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return The normal vector.
     */
    public Vector getNormal() {
        return normal;
    }

    // ***************** Override ********************** //

    /**
     * Calculates the normal vector to the plane at a given point.
     * Since a plane has a constant normal, the same normal vector is returned regardless of the point.
     *
     * @param point The point for which to calculate the normal.
     * @return The normal vector.
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    /**
     * Returns a string representation of the plane in the format
     * p0 is point
     * nurmal is Vector
     * @return The string representation of the plane.
     */
    @Override
    public String toString() {
        return "Plane{" +
                "p0=" + p0.toString() +
                ", normal=" + normal.toString() +
                '}';
    }


    /**
     * Finds the intersection points of a ray with the plane.
     *
     * @param ray         The ray for calculating the intersection points.
     * @param maxDistance The maximum distance for valid intersections.
     * @return A list of GeoPoint objects representing the intersections of the ray with the plane.
     * If there are no intersections, the list is null.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
        Vector n=getNormal();
        double nv=n.dotProduct(ray.getDirection());
        if(isZero(nv))
        {
            return  null;
        }
        Vector p0Q;
        try {
            p0Q =(p0.subtract(ray.getP0()));
        }
        catch (Exception e)
        {
            return  null;
        }
        double t=alignZero((n.dotProduct(p0Q))/nv);
        if(t<=0 || alignZero(t - maxDistance) >= 0)
            return null;
        return List.of(new GeoPoint(this,ray.getPoint(t)));
    }
}