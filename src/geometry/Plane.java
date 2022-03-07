package geometry;

import primitives.Point;
import primitives.Vector;
/**
 * Plane class a point in the vector anth space
 * @author noga and noa
 */

public class Plane implements Geometry{
    final Point _q0;
    final Vector _normal;

    /**
     * TODO explanations here
     * @param q0
     * @param normal vector for the normal (will be normalized automatically)
     */public Plane(Point q0, Vector normal) {
        _q0 = q0;
        _normal = normal;
    }

    /**
     * from three point found the vector normal (now put null in the next level we do it)
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        _q0 =p1;
//        //TODO check direction of vectors
//        Vector U = p1.subtract(p2);
//        Vector V = p3.subtract(p2);

        Vector U = p2.subtract(p1);
        Vector V = p3.subtract(p1);
        Vector N = U.crossProduct(V);

        //right hand rule
        _normal = null;
        //N.normalize();
    }

    public Point getQ0() {
        return _q0;
    }

    /**
     * getter for _normal field
     * @return
     */
    public Vector getNormal() {
        return _normal;
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
                "_q0=" + _q0 +
                ", _normal=" + _normal +
                '}';
    }
}