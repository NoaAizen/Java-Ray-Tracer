package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;
// * @author noga and noa

/**
 * spot light - type of light
 */
public class SpotLight  extends PointLight {
    /**
     * get for Intensity
     * @param point - Point
     * @return Color
     */
    @Override
    public Color getIntensity(Point point) {
        Color Ic = super.getIntensity(point);
        double lv= getL(point).dotProduct(direction);
        double factor = Math.max(0,lv);
        return Ic.scale(factor);
    }


    /**
     * the direction of the light
     */
    private Vector direction;
    /**
     * To narrow the foundation
     */
    private double narrowBeam = 0d;

    // ***************** setter ********************** //

    /**
     * set for narrowBeam
     * @param narrowBeam - double
     * @return SpotLight
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

    // ***************** getter ********************** //

    /**
     * get for narrowBeam
     * @return SpotLight
     */
    public double getNarrowBeam() {
        return narrowBeam;
    }

    // ***************** constructor ********************** //


    /**
     * constructor with Parameters
     * @param intensity - vector
     * @param _position - point
     * @param _direction - vector
     */
    public SpotLight(Color intensity, Point _position, Vector _direction) {
        super(intensity,_position);
        this.direction = _direction.normalize();
    }

    // ***************** Override ********************** //



    /**
     * get for L, Returns the calculation of l
     get for L, A function that does the calculation of vectors that damage light
     *- soft shadows
     * @param point-point of     light
     * @param n- vector of ray
     * @return List of Vector -Vectors that damage light
     */
    @Override
    public List<Vector> listGetL(Point point,Vector n) {
        return super.listGetL(point,n);
    }

    /**
     *Returns one vector that meets a point of light
     * @param point-point of  light
     * @return -vector
     */
    public Vector getL(Point point) {
        return super.getL(point);
    }
}
