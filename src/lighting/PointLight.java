package lighting;

import primitives.*;

public class PointLight extends Light implements LightSource{

    private Point position;
    private double kC=1;
    private double kL=0;
    private double kQ=0;

    /**
     * constructor
     * @param intensity
     */
    protected PointLight(Color intensity, Point _position) {
        super(intensity);
        this.position = _position;

    }

    /**
     * func setter (type builder)
     * @param position
     * @return PointLight
     */
    public PointLight setPosition(Point position) {
        this.position = position;
        return this;
    }

    /**
     * func setter (type builder)
     * @param kC
     * @return PointLight
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * func setter (type builder)
     * @param _kL
     * @return PointLight
     */
    public PointLight setkL(double _kL) {
        this.kL = _kL;
        return this;
    }

    /**
     * func setter (type builder)
     * @param _kQ
     * @return PointLight
     */
    public PointLight setkQ(double _kQ) {
        this.kQ = _kQ;
        return  this;
    }

    /**
     * Get light intensity at a point IL
     *
     * @param p
     * @return Color
     */
    @Override
    public Color getIntensity(Point p) {
        double distance = p.distance(position);
        double distancesquared = p.distanceSquared(position);
        double factor = (kC + kL * distance + kQ * distancesquared);
        return getIntensity().reduce(factor);
    }


    /**
     * @param p
     * @return Vector
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
