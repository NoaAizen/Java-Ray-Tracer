package primitives;

import java.util.List;
import java.util.Objects;

import static primitives.Util.*;

import static geometries.Intersectable.GeoPoint;


/**
 * Ray class represents a ray in 3D space, consisting of a starting point (p0) and a direction vector (dir).
 *
 * Authors: Noga Jacobs and Noa
 */
public class Ray {
    /**
     * A small delta value used for constructing a new ray slightly above or below a geometry's surface.
     */
    private static final double DELTA = 0.1 ;//
    /**
     * The starting point of the ray.
     */
    final Point p0;
    /**
     * The direction vector of the ray.
     */
    final Vector dir;

    // ***************** Constructor ********************** //

    /**
     * Constructor for a ray given a starting point and a direction vector.
     *
     * @param p0  The starting point of the ray.
     * @param dir The direction vector of the ray.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }
    /**
     * Constructor for a ray given a starting point, a direction vector, and a normal vector for the geometry.
     * Used for calculating a slightly offset starting point to avoid self-intersections.
     *
     * @param p0  The starting point of the ray.
     * @param v   The direction vector of the ray.
     * @param n   The normal vector of the geometry.
     */
    public Ray(Point p0, Vector v, Vector n) {
        this.dir = v.normalize();
        double vn = alignZero(v.dotProduct(n));
        Vector epsilon;
        if( vn < 0){
            epsilon = n.scale(-DELTA);
        }
        else{
            epsilon = n.scale(DELTA);
        }

        this.p0 = p0.add(epsilon);
    }
    // ***************** Getter ********************** //

    /**
     * start of the ray
     *
     * @return Point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * the direction of the ray
     *
     * @return Vector
     */
    public Vector getDir() {
        return dir;
    }

    // ***************** Override ********************** //

    /**
     * Returns a string representation of this ray.
     *
     * @return String A string containing the starting point and direction vector of the ray.
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * Checks if this ray is equal to the given object.
     *
     * @param o The second parameter to compare with this ray.
     * @return boolean Returns true if the object is of the same class and has the same value as this ray, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * Calculates the hash code for this ray.
     *
     * @return int The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    // ***************** Getter ********************** //

    /**
     * Returns the direction vector of this ray.
     *
     * @return Vector The direction vector of the ray.
     */
    public Vector getDirection() {
        return new Vector(dir.xyz);
    }

    /**
     * Calculates a point along the ray at a given distance from the starting point.
     *
     * @param t The distance parameter.
     * @return Point The point along the ray at the given distance.
     */
    public Point getPoint(double t) {
        if ((t)==0.0) {
            return p0;
        }
        else{
        return p0.add(dir.scale(t));
        }
    }

    // ***************** Methods ********************** //

    /**
     * Finds the closest geometry point to the ray's starting point from a list of geometry points.
     *
     * @param geoPoints List of geometry points to search from.
     * @return GeoPoint The geometry point closest to the starting point of the ray.
     */
    public GeoPoint findGeoClosestPoint(List<GeoPoint> geoPoints) {
        if (geoPoints == null) //In case of an empty list
            return null;
        GeoPoint closePoint = geoPoints.get(0);    //Save the first point in the list
        for (GeoPoint p : geoPoints) {
            if (closePoint.point.distance(p0) > p.point.distance(p0))    //In case the distance of closes point is bigger than the p point
                closePoint = p;
        }
        return closePoint;
    }
}
