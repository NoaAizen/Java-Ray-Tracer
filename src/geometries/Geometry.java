package geometries;

import primitives.Ray;
import primitives.Vector;
import primitives.Point;

import java.util.List;

/**
 * this is the interface for all geometries that need to get a normalized vector.its the most basic interface for all geometries.
 */
public interface Geometry extends Intersectable
{
    /**
     *
     * @param point
     * @return Vector
     */
    public Vector getNormal (Point point);

    /**
     *
     * @param ray
     * @return list of point
     */
    public List<Point> findIntersections(Ray ray);


}