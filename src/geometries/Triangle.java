package geometries;
import primitives.*;
import primitives.Point;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Triangle class Vertex polygon
 * system
 *
 * @author noga and noa
 */
public class Triangle extends Polygon  implements Geometry {

    /**
     * constctor using super main using Polygon
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }

    @Override
    public  String toString() {
        return "Triangle{}";
    }

    /**
     * use func getNormal of class plane
     * @param p
     * @return the normal of plane
     */
    public Vector getNormal(Point p){
        return this.plane.getNormal(p);
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
    public List<Point> findIntersections(Ray ray){

        //the data from the ray
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        var intersections=super.plane.findIntersections(ray);
        if(intersections==null){
            return null;}
        Vector v0=vertices.get(0).subtract(p0);
        Vector v1=vertices.get(1).subtract(p0);
        Vector v2=vertices.get(2).subtract(p0);
        double temp = alignZero(v.dotProduct(v0.crossProduct(v1)));
        double temp1 = alignZero(v.dotProduct(v1.crossProduct(v2)));
        double temp2 = alignZero(v.dotProduct(v2.crossProduct(v0)));

        if (temp==0){
        return null;}
        if (temp1*temp<=0){
            return null;}
        if (temp2*temp<=0){
            return null;}
    return  intersections;
    }
}
