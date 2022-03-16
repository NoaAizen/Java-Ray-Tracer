package geometries;
import primitives.*;

import java.util.List;

public interface Intersectable {
    /**
     *to do-להשלי
     * @param ray
     * @return
     */
    public List<Point> findIntersections(Ray ray);
}
