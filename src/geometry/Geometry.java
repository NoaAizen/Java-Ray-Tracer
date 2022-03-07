package geometry;

import primitives.Vector;
import primitives.Point;

/**
 * this is the interface for all geometries that need to get a normalized vector.its the most basic interface for all geometries.
 */
public interface Geometry
{
    /**
     *
     * @param point
     * @return Vector
     */
    public Vector getNormal (Point point);
}