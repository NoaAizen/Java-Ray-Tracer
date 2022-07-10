package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.*;
/**
 * Triangle class Vertex polygon
 * system
 *
 * @author noga and noa
 */
public class Tube extends Geometry {

    /**
     *  the ray that Passes inside the tube and determines its direction
     */
    final Ray axisRay;

    /**
     *  radius of tube determines his size
     */
    final double radius;

    // ***************** constructor ********************** //

    /**
     *  constructor with prmetrim
     * @param axisRay - Tube of ray
     * @param radius- Tube of radius
     */

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    // ***************** getter ********************** //

    /**
     * func get return axisRay of Tube
     * @return Ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * func get return radius of Tube
     * @return double
     */
    public double getRadius() {
        return radius;
    }
    // ***************** fuc ********************** //

    /**
     * return vector normal
     * @param p- point for the normal
     * @return vector normal
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
     * with Ray (axisRay) and radius (double)
     * @return string
     */
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }

    /**
     * find the cross Tube (bunus)
     * @param ray - Ray
     * @param maxDistance - double
     * @return list of GeoPoint - tube cross and point cross
     */
   @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
       return null;
   }
}
