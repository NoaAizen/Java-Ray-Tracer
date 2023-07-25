package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;


/**
 * The Intersectable abstract class represents a 3D geometric shape that can be intersected with a ray.
 * Subclasses of Intersectable must implement the findGeoIntersectionsHelper method to find intersections with a given ray.
 * The findIntersections method provides a list of intersection points as regular Point objects.
 * The GeoPoint inner class holds a Geometry object and the corresponding intersection point.
 *
 * Authors: Noga Jacobs and Noa
 */
public abstract class Intersectable {

    /**
     * Finds intersection points of the geometry with the given ray.
     * Converts the GeoPoint objects into regular Point objects for ease of use.
     *
     * @param ray The ray for calculating the intersection points.
     * @return A list of the intersection points, or null if there are no intersections.
     */
    public  List<Point> findIntersections(Ray ray){
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Finds the intersections of the geometry with the given ray, up to a maximum distance.
     * Converts the GeoPoint objects into regular Point objects for ease of use.
     *
     * @param ray         The ray for calculating the intersection points.
     * @return A list of the intersection points, or null if there are no intersections.
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * call to func findGeoIntersectionsHelper
     * @param ray-   -The fund ray calculating the points is cut
     * @param maxDistance- max Distance
     * @return List of the GeoPoint Cut with the ray
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * Finds the intersections of the geometry with the given ray, up to a maximum distance.
     *
     * @param ray         The ray for calculating the intersection points.
     * @param maxDistance The maximum distance for valid intersections.
     * @return A list of the intersection GeoPoints, or null if there are no intersections.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * The GeoPoint inner class holds a Geometry object and the corresponding intersection point.
     */
    public static class GeoPoint{
        /**
         * The geometry that intersects with the ray.
         */
        public Geometry geometry;
        /**
         * The point of intersection with the geometry.
         */
        public Point point;

        // ***************** Constructor ********************** //

        /**
         * Constructs a GeoPoint with the given geometry and intersection point.
         *
         * @param geometry The geometry that intersects with the ray.
         * @param point    The point of intersection with the geometry.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        // ***************** Override ********************** //

        /**
         * Checks if this GeoPoint is equal to another object.
         *
         * @param o The other object to compare to.
         * @return True if they are equal, otherwise false.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        /**
         * Generates a hash code for this GeoPoint.
         *
         * @return The hash code.
         */
        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }

        /**
         * Converts this GeoPoint to a string representation.
         *
         * @return The string representation of the GeoPoint.
         */
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "_geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
}