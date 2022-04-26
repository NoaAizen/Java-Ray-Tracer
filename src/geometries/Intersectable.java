package geometries;
import primitives.*;

import java.util.List;
import java.util.Objects;

public abstract class Intersectable {
    /**
     *to do-להשלי
     * @param ray
     * @return list of point of cross
     */
    public  List<Point> findIntersections(Ray ray){
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }

    public List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    public static class GeoPoint{
        public Geometry _geometry;
        public Point point;

        public GeoPoint(Geometry geometry, Point point) {
            _geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return _geometry.equals(geoPoint._geometry) && point.equals(geoPoint.point);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_geometry, point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "_geometry=" + _geometry +
                    ", point=" + point +
                    '}';
        }
    }
}
