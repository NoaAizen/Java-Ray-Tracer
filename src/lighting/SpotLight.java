package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight  extends PointLight {

    private Vector direction;
    private double narrowBeam = 0d;

    /**
     * set for narrowBeam
     * @param narrowBeam
     * @return SpotLight
     */
    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }

    /**
     * get for narrowBeam
     * @return SpotLight
     */

    public double getNarrowBeam() {
        return narrowBeam;
    }

    /**
     * constructor
     * @param intensity
     */
    protected SpotLight(Color intensity, Point _position,Vector _direction) {
        super(intensity,_position);
        this.direction = _direction.normalize();
    }

    /**
     *get for Intensity
     * @param point
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
     *    /** get for L, Returns the calculation of l
     * @param point
     * @return
     */
    @Override
    public Vector getL(Point point) {
        return super.getL(point);
    }
}
