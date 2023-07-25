package geometries;
import primitives.Ray;
import primitives.Vector;
import primitives.Point;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Triangle class represents a three-dimensional triangle in 3D Cartesian coordinate system.
 * The triangle is defined by its three vertices.
 * The class inherits from the Polygon class.
 *
 * Authors: Noga Jacobs and Noa
 */
public class Triangle extends Polygon {

    // ***************** Constructor ********************** //

    /**
     * Constructor for creating a triangle with three specified vertices.
     *
     * @param p1 The first vertex of the triangle.
     * @param p2 The second vertex of the triangle.
     * @param p3 The third vertex of the triangle.
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1,p2,p3);
    }

    // ***************** Override ********************** //

    /**
     * Returns a string representation of the triangle.
     *
     * @return A string representation of the triangle.
     */
    @Override
    public  String toString() {
        return "Triangle{}";
    }

    /**
     * Calculates the normal vector to the triangle at any given point on its surface.
     * This method uses the getNormal method from the Plane class, which is inherited from the Polygon class.
     *
     * @param p The point for which to calculate the normal vector.
     * @return The normalized normal vector to the triangle at the specified point.
     */
    public Vector getNormal(Point p){
        return this.plane.getNormal(p);
    }

    /**
     * Finds the intersection points of a ray with the triangle.
     *
     * @param ray The ray for calculating the intersection points.
     * @param maxDistance The maximum distance for valid intersections.
     * @return A list containing GeoPoint objects representing the intersection points of the ray with the triangle.
     * If there are no intersections, the list is null.
     */
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance){

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
