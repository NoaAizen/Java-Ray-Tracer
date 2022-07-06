package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;
// * @author noga and noa

public class DirectionalLight extends Light implements LightSource{

    /**
     * the direction of this light
     */
    private Vector direction;

    // ***************** constructor ********************** //

    /**
     * constructor
     * @param intensity
     * @param _direction- Vector for the direction of Light
     */
    protected DirectionalLight(Color intensity, Vector _direction) {
        super(intensity);
        direction = _direction.normalize();
    }



    // ***************** Override ********************** //
    /**  get  Distance
     * @param point -The point where the light passes
     * @return Double.POSITIVE_INFINITY
     */
    public double getDistance(Point point){
        return Double.POSITIVE_INFINITY;
    }
    /**
     * Get light intensity at a point IL
     * @param p-point
     * @return Color
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /**
     * geter for parameter L
     * @param p=point of the light
     * @param n=the Vector ray
     * @return Returns a list of vectors of the direction from the  light
     */
    @Override
    public List<Vector> listGetL(Point p,Vector n) {
      return List.of (this.direction);
    }
    /**
     * geter for parameter L
     * @param p=point of the light
     * @return Returns a list of vector of the direction from the  light
     */
    public Vector getL(Point p) {
        return this.direction;
    }
}
