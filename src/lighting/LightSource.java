package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;


/**
 * @author noga and noa
 */
public interface LightSource {

    // ***************** getter ********************** //

    /**
     * Get light intensity at a point IL
     * @param p -point of the Light
     * @return Color
     */
    Color getIntensity(Point p);

    /** get Distance
     * every class that implements this class Override this func
     * @param point - Point
     * @return double Distance
     */
    double getDistance(Point point);

    /**
     * get for l

     * @param p=point of the light
     * @param n=the Vector ray
     * @return Returns a list of vectors
     */
    public List<Vector> listGetL(Point p,Vector n);

    /**
     *    * geter for parameter L
     *      * @param p=point of the light
     *      * @return Returns a   vector of the

     */
    /**
     *  geter for parameter L
     * @param  p = point of the light
     * @return vector
     */
    public Vector getL(Point p);

}
