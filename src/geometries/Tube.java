package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.*;
/**
 * Tube class represents a tube in 3D Cartesian coordinate system.
 * A tube is defined by its axis, which is a ray, and its radius.
 * The tube is an infinite cylinder with a finite radius.
 * The class inherits from the Geometry class.
 *
 * Authors: Noga Jacobs and Noa
 */
public class Tube extends Geometry {

    /**
     * The ray that passes inside the tube and determines its direction.
     */
    final Ray axisRay;

    /**
     * The radius of the tube, determines its size.
     */
    final double radius;

    // ***************** Constructor ********************** //

    /**
     * Constructor for creating a tube with the specified axis ray and radius.
     *
     * @param axisRay The ray that passes inside the tube and determines its direction.
     * @param radius  The radius of the tube.
     */

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    // ***************** Getters  ********************** //

    /**
     * Get the axis ray of the tube.
     *
     * @return The axis ray of the tube.
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * Get the radius of the tube.
     *
     * @return The radius of the tube.
     */
    public double getRadius() {
        return radius;
    }
    // ***************** Methods ********************** //

    /**
     * Returns the normal vector to the tube at the specified point.
     *
     * @param p The point for which to calculate the normal vector.
     * @return The normalized normal vector to the tube at the specified point.
     */
    public Vector getNormal(Point p){
        Point P0 = axisRay.getP0();
        Vector v = axisRay.getDir();

        Vector P0_P = p.subtract(P0);

        double t = alignZero(v.dotProduct(P0_P));

        if (isZero(t)) {
            return P0_P.normalize();
        }

        Point P1 = P0.add(v.scale(t));

        if (p.equals(P1)) {
            throw new IllegalArgumentException("point cannot be on the tube axis");
        }

        Vector n = p.subtract(P1).normalize();

        return n;
    }

    // ***************** Override ********************** //

    /**
     * Returns a string representation of the tube.
     *
     * @return A string representation of the tube.
     */
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * Finds the intersection points of a ray with the tube.
     * (Note: This method returns null because the intersection calculation is not implemented for the tube).
     *
     * @param ray         The ray for calculating the intersection points.
     * @param maxDistance The maximum distance for valid intersections.
     * @return A list containing GeoPoint objects representing the intersection points of the ray with the tube.
     * If there are no intersections, the list is null.
     */
   @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
       return null;
   }
}
