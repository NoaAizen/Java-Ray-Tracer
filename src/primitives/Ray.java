package primitives;

import primitives.*;

import java.util.List;
import java.util.Objects;

import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;

public class Ray {
    final Point p0;
    final Vector dir;

    // ***************** constructor ********************** //
    /**
     * constructor
     *
     * @param p0
     * @param dir
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }
    // ***************** getter ********************** //
    /**
     * start of the ray
     * @return Point
     */
    public Point getP0() {
        return p0;
    }

    /**
     * the direction of the ray
     * @return Vector
     */
    public Vector getDir() {
        return dir;
    }

    // ***************** Override ********************** //
    /**
     * toString
     *
     * @return
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     * equals
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    /**
     * hash Code
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    // ***************** getter ********************** //
    /**
     * func of get
     *
     * @return vector
     */
    public Vector getDirection() {
        return new Vector(dir.xyz);
    }

    /**
     * Multiply by double the point
     *
     * @param t
     * @return point
     */
    public Point getPoint(double t) {
        if (isZero(t)) {
            return p0;
        }
        return p0.add(dir.Scale(t));
    }

    // ***************** func ********************** //
    /**
     * @param points
     * @return the point closest to the beginning of the foundation.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * @param geoPoints
     * @return The closest point to the began of the ray
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {

        if (geoPoints == null) //In case of an empty list
            return null;
        GeoPoint closePoint = geoPoints.get(0);    //Save the first point in the list
        for (GeoPoint p : geoPoints) {
            if (closePoint.point.distance(p0) > p.point.distance(p0))    //In case the distance of closes point is bigger than the p point
                closePoint = p;
        }
        return closePoint;
    }

    /**
     * find Geo point Closest Point,  geo point it is the point from list of cross geometries
     * @param intersections
     * @return GeoPoint
     */
    public GeoPoint findGeoClosestPoint(List<GeoPoint> intersections) {
        GeoPoint closestPoint = null;
        if (intersections == null)
            return null;
        double distance = Double.MAX_VALUE;
        double d;
        if (!intersections.isEmpty()) {
            for (var pt : intersections) {
                d = p0.distance(pt.point);

                if (d < distance) {
                    distance = d;
                    closestPoint = pt;
                }
            }
        }
        return closestPoint;
    }
}
