package lighting;

import primitives.*;

public class DirectionalLight extends Light implements LightSource{

    private Vector direction;
    /**
     * constructor
     * @param intensity
     */
    protected DirectionalLight(Color intensity, Vector _direction) {
        super(intensity);
        direction = _direction.normalize();
    }
    public double getDistance(Point point){
        return Double.POSITIVE_INFINITY;
    }
    /**
     * Get light intensity at a point IL
     *
     * @param p
     * @return Color
     */
    @Override
    public Color getIntensity(Point p) {
        return getIntensity();
    }

    /** geter for prmter L
     * @param p
     * @return Vector
     */
    @Override
    public Vector getL(Point p) {
        return this.direction;
    }
}
