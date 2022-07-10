package geometries;
import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;


/**
 * @author noga and noa
 */
public abstract class Intersectable {

    /**
     * Function for finding intersection points
     * @param ray -The fund ray calculating the points is cut
     * @return list of point of cross
     */
    public  List<Point> findIntersections(Ray ray){
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Function for finding intersection points
     * @param ray- The fund ray calculating the points is cut
     * @return List of the GeoPoint Cut with the ray
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
     *  func abstract-That all departments that inherit must be exercised
     * @param ray-   -The fund ray calculating the points is cut
     * @param maxDistance- max Distance
     * @return List of the GeoPoint Cut with the ray
     * */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * class for to put geometry that cross with the point cross.
     */
    public static class GeoPoint{
        /**
         * the geometry that cross
         */
        public Geometry geometry;
        /**
         * the point cross
         */
        public Point point;

        // ***************** constructor ********************** //

        /**
         * constructor
         * @param geometry - Geometry
         * @param point - Point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        // ***************** Override ********************** //

        /**
         * equals
         * @param o-The second variable for equals
         * @return boolean-If this is true then they are equal, otherwise false
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
        }

        /**
         * cFunction for comparison function
         * @return int
         */
        @Override
        public int hashCode() {
            return Objects.hash(geometry, point);
        }

        /**
         * to string
         * @return string
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