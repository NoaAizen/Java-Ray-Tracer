package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

public class DirectionalLight extends Light implements LightSource{

    /**
     * the direction of this light
     */
    private Vector direction;

    // ***************** constructor ********************** //

    /**
     * constructor
     * @param intensity
     */
    protected DirectionalLight(Color intensity, Vector _direction) {
        super(intensity);
        direction = _direction.normalize();
    }

    /**
     * @param point
     * @return Double.POSITIVE_INFINITY
     */
    public double getDistance(Point point){
        return Double.POSITIVE_INFINITY;
    }

    // ***************** Override ********************** //

    /**
     * Get light intensity at a point IL
     * @param p
     * @return Color
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /**
     * geter for parameter L
     * @param p
     * @return Vector
     */
    @Override
    public List<Vector> getL(Point p) {
        return this.direction;
    }
}
