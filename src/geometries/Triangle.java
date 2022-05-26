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
public class Triangle extends Polygon {

    // ***************** constructor ********************** //

    /**
     * constructor using super main using Polygon
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }

    // ***************** Override ********************** //

    /**
     * with parameter of triangle
     * @return string
     */
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
     * find the cross triangle
     * @param ray
     * @param maxDistance
     * @return list of GeoPoint - triangle cross and point cross
     */
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance){

        var intersection=plane.findGeoIntersectionsHelper(ray, maxDistance);
        if(intersection==null)
            return null;
        Point p0=ray.getP0();
        Vector v=ray.getDirection();
        Vector v1=vertices.get(0).subtract(p0);
        Vector v2=vertices.get(1).subtract(p0);
        Vector v3=vertices.get(2).subtract(p0);
        double s1=alignZero(v.dotProduct(v1.crossProduct(v2)));
        if(s1==0) return null;
        double s2=alignZero(v.dotProduct(v2.crossProduct(v3)));
        if(s1*s2<=0) return null;
        double s3=alignZero(v.dotProduct(v3.crossProduct(v1)));
        if(s1*s3<=0) return null;
        return  List.of(new GeoPoint(this,intersection.get(0).point));//check
    }
}
