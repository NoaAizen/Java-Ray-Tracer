package geometries;
import primitives.*;

import java.util.List;

public interface Intersectable {
    /**
     *to do-להשלי
     * @param ray
     * @return list of point of cross
     */
    public List<Point> findIntersections(Ray ray);
}
