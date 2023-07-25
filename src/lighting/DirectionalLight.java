package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;


/**
 * DirectionalLight class represents a directional light source in a scene.
 * A directional light has a constant intensity and is located infinitely far away,
 * so it illuminates all objects in the scene equally, regardless of distance.
 * It has a specified direction, but no specific position or location.
 *
 * Authors: Noga Jacobs and Noa
 */
public class DirectionalLight extends Light implements LightSource{

    /**
     * The direction of this directional light.
     */
    private Vector direction;

    // ***************** Constructor  ********************** //

    /**
     * Constructor for creating a directional light with the specified intensity and direction.
     *
     * @param intensity  The intensity of the directional light.
     * @param _direction  The direction vector of the directional light.
     */
    protected DirectionalLight(Color intensity, Vector _direction) {
        super(intensity);
        direction = _direction.normalize();
    }



    // ***************** LightSource Interface Implementation ********************** //

    /**
     * Get the distance from the light source to a given point.
     * Since a directional light is located infinitely far away, the distance is positive infinity.
     *
     * @param point The point in the scene.
     * @return Double.POSITIVE_INFINITY.
     */
    public double getDistance(Point point){
        return Double.POSITIVE_INFINITY;
    }
    /**
     * Get the intensity of the light at a given point.
     * Since a directional light has constant intensity, it returns the same intensity for all points.
     *
     * @param p The point in the scene.
     * @return The intensity of the directional light.
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /**
     * Get the list of direction vectors from the light to a given point, based on the light source type.
     * For a directional light, the direction vector is the same for all points in the scene,
     * so it returns a list with a single element - the direction vector of the light.
     *
     * @param p The point in the scene.
     * @param n The normal vector at the point (not used for directional lights).
     * @return A list containing the direction vector of the directional light.
     */
    @Override
    public List<Vector> listGetL(Point p,Vector n) {
      return List.of (this.direction);
    }
    /**
     * Get the direction vector from the light to a given point.
     * Since a directional light has a constant direction, it returns the same direction vector for all points.
     *
     * @param p The point in the scene.
     * @return The direction vector of the directional light.
     */
    public Vector getL(Point p) {
        return this.direction;
    }
}
