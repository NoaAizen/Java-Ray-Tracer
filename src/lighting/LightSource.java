package lighting;

import primitives.*;

public interface LightSource {

    // ***************** getter ********************** //

    /**
     * Get light intensity at a point IL
     * @param p
     * @return Color
     */
    Color getIntensity(Point p);

    /**
     * every class that implements this class Override this func
     * @param point
     * @return double
     */
    double getDistance(Point point);

    /**
     * get for l
     * @param p
     * @return Vector
     */
    public Vector getL(Point p);
}
