package geometries;
import primitives.*;

import java.util.List;
import java.util.Objects;

public abstract class Intersectable {
    /**
     *Function for finding intersection points
     * @param ray
     * @return list of point of cross
     */
    public  List<Point> findIntersections(Ray ray){
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     *     *Function for finding intersection points
     * @param ray
     * @return List
     */
    public List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);



    public static class GeoPoint{
        public Geometry geometry;
        public Point point;

        /**
         * constructor
          * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**\
         * equals
         * @param o
         * @return boolean
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
