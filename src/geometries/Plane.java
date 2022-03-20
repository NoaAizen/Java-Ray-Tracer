package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane class a point in the vector anth space
 * @author noga and noa
 */

public class Plane implements Geometry{
    final Point p0;
    final Vector normal;

    /**
     * constructor
     * @param q0
     * @param n vector for the normal (will be normalized automatically)
     */public Plane(Point q0, Vector n) {
        p0 = q0;
        normal = n;
    }

    /**
     * from three point found the vector normal (now put null in the next level we do it)
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        p0 =p1;
//        //TODO check direction of vectors
//        Vector U = p1.subtract(p2);
//        Vector V = p3.subtract(p2);

        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        Vector N = U.crossProduct(V);


         normal =  N.normalize();

    }

    public Point getQ0() {
        return p0;
    }

    /**
     * getter for _normal field
     * @return
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * implementation of getNormal from Geometry
     * @param point
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Plane{" +
                "p0=" + p0 +
                ", normal=" + normal +
                '}';
    }

    /**
     * The data:
     * n = normal of the plane
     * Q = the point in the plane
     * the P is the point in the plane we search that ray cross plane on it.
     * (this we get from the plane)
     * @parm ray
     * @return
     */
    @Override
    public List<Point> findIntersections(Ray ray) {

        //the data from the ray
        Point p0 = ray.getP0();
        Vector v = ray.getDirection();

        //the data from the plane
        Vector n = getNormal();
        Point Q = getQ0();

        //check if the vector between Q and p0 is zero (it mean that the ray start on plane)
        if(Q.equals(p0))
            return null;

        //numerator of the calculation t
        double numerator = alignZero(n.dotProduct(Q.subtract(p0)));

        //denominator of the calculation t
        double nv = alignZero(n.dotProduct(v));

        //check if the vector of plane(n) and the vector of ray is Orthogonal
        if(isZero(nv))
            return null;

        //the calculation t
        double t = alignZero(numerator/nv);

        //The opposite direction
        if (t<=0)
            return null;

        //Ray points P = P0 + tV
        Point P = p0.add(v.Scale(t));

        return List.of(P);
    }
}