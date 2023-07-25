package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;


/**
 * LightSource is an interface representing a light source in a scene.
 * It provides methods to get the intensity of the light at a specific point and to calculate the distance from the light source to a given point.
 *
 * Authors: Noga Jacobs and Noa
 */
public interface LightSource {

    // ***************** Getter ********************** //

    /**
     * Get the light intensity at a specified point in the scene.
     *
     * @param p The point at which the intensity of the light is calculated.
     * @return The color intensity of the light at the specified point.
     */
    Color getIntensity(Point p);

    /**
     * Get the distance from the light source to a given point.
     * This method should be overridden by any class that implements this interface.
     *
     * @param point The point for which the distance to the light source is calculated.
     * @return The distance from the light source to the specified point.
     */
    double getDistance(Point point);

    /**
     * Get the direction vectors from the light source to a given point.
     *
     * @param p The point for which the direction vectors are calculated.
     * @param n The normal vector at the specified point.
     * @return A list of direction vectors from the light source to the specified point.
     */
    public List<Vector> listGetL(Point p,Vector n);

    /**
     * Get the direction vector from the light source to a given point.
     *
     * @param p The point for which the direction vector is calculated.
     * @return The direction vector from the light source to the specified point.
     */
    public Vector getL(Point p);

}
