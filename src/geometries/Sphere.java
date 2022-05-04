package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Triangle class Vertex polygon
 * system
 *
 * @author noga and noa
 */
public class Sphere extends Geometry {

    final Point center;
    final double radius;

    /**
     * constructor
     * @param center
     * @param radius
     */
    public Sphere(final Point center, final double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * get the point center
     * @return point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * get the radius center
     * @return double
     */
    public double getRadius() {
        return radius;
    }

    /**
     * return vector normal
     * @param p1
     * @return vector
     */
    public Vector getNormal(Point p1) {
        return p1.subtract(center).normalize();
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    /**
     *      *Function for finding intersection points
     * @param ray
     * @return
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {

        //the data from the ray
        Point p0 = ray.getP0();
        Vector v = ray.getDir().normalize();
        if (center.equals(p0)) {// לשנות לGeoPoint
            return List.of(new GeoPoint(this,center.add(v.Scale(radius))));
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
        if (t1 > 0 && t2 > 0) {

            //  Point p1 = p0.add(v.Scale(t1));
            //Point p2 = p0.add(v.Scale(t2));
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);

            return List.of(new GeoPoint(this, p2));
        }
        if (t1 > 0) {

            Point p1 = p0.add(v.Scale(t1));
            return List.of(new GeoPoint(this,p1));
        }
        if (t2 > 0) {

            Point p2 = p0.add(v.Scale(t2));
            return List.of(new GeoPoint(this,p2));
        }
        return null;
    }
}



