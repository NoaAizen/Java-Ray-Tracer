package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight  extends PointLight {

    private Vector direction;//כיוון
    private double narrowBeam = 0d;

    public SpotLight setNarrowBeam(double narrowBeam) {
        this.narrowBeam = narrowBeam;
        return this;
    }
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
     *
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
     *
     * @param point
     * @return
     */
    @Override
    public Vector getL(Point point) {
        return super.getL(point);
    }
}
