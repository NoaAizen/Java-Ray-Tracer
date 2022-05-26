package lighting;

import primitives.*;

public class PointLight extends Light implements LightSource{

    /**
     * the point that start the ray of this light
     */
    private Point position;
    /**
     * Fixed discount coefficient
     */
    private double kC=1;
    /**
     * Discount coefficient
     */
    private double kL=0;
    /**
     * Discount coefficient
     */
    private double kQ=0;

    // ***************** constructor ********************** //

    /**
     * constructor
     * @param intensity
     */
    protected PointLight(Color intensity, Point _position) {
        super(intensity);
        this.position = _position;
    }

    /**
     * call to func distance in class point
     * @param point
     * @return
     */
    public double getDistance(Point point){
        double distance = point.distance(position);
        return distance;
    }

    // ***************** setter ********************** //

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

    // ***************** Override ********************** //

    /**
     * Get light intensity at a point IL
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
     * get for L, Returns the calculation of l
     * @param p
     * @return Vector
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
