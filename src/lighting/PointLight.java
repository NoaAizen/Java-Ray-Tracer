package lighting;

import geometries.Circle;
import geometries.Cylinder;
import geometries.Sphere;
import primitives.*;
import primitives.Color;
import primitives.Point;


import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author noga and noa
 */
public class PointLight extends Light implements LightSource {
    /**
     * The amount of rays we want to run
     * "- soft shadows
     */
    private int size = 20;
    /**
     * thet lenth of the shere
     */
    private int lenVector = 10;
    /**
     * the point that start the ray of this light
     */
    private Point position;
    /**
     * Fixed discount coefficient
     */
    private Double3 kC = Double3.ONE;
    /**
     * Discount coefficient
     */
    private Double3 kL = Double3.ZERO;
    /**
     * Discount coefficient
     */
    private Double3 kQ = Double3.ZERO;

    // ***************** constructor ********************** //

    /**
     * constructor
     *@param _position-point
     * @param intensity-color
     */
    public PointLight(Color intensity, Point _position) {
        super(intensity);
        this.position = _position;
    }

    /**
     * get distance
     * call to func distance in class point
     *
     * @param point for the Calculating the distance of light
     * @return double distance
     */
    public double getDistance(Point point) {
        double distance = point.distance(position);
        return distance;
    }

    // ***************** setter ********************** //

    /**
     * func setter (type builder)
     *
     * @param position Point
     * @return PointLight
     */
    public PointLight setPosition(Point position) {
        this.position = position;
        return this;
    }

    /**
     * func setter (type builder)
     *
     * @param kC Double3
     * @return PointLight
     */
    public PointLight setkC(Double3 kC) {
        this.kC = kC;
        return this;
    }

    /**
     * func setter (type builder)
     *
     * @param _kL Double3
     * @return PointLight
     */
    public PointLight setkL(Double3 _kL) {
        this.kL = _kL;
        return this;
    }

    /**
     * func setter (type builder)
     *
     * @param _kQ Double3
     * @return PointLight
     */
    public PointLight setkQ(Double3 _kQ) {
        this.kQ = _kQ;
        return this;
    }

    /**
     * func setter (type builder)
     *
     * @param kC Double3
     * @return PointLight
     */
    public PointLight setkC(double kC) {
        this.kC = new Double3(kC);
        return this;
    }

    /**
     * func setter (type builder)
     *
     * @param _kL double
     * @return PointLight
     */
    public PointLight setkL(double _kL) {
        this.kL = new Double3(_kL);
        return this;
    }

    /**
     * func setter (type builder)
     *
     * @param _kQ double
     * @return PointLight
     */
    public PointLight setkQ(double _kQ) {
        this.kQ = new Double3(_kQ);
        return this;
    }
    // ***************** Override ********************** //

    /**
     * Get light intensity at a point IL
     *
     * @param p-point
     * @return Color-Calculate color with light
     */
    @Override
    public Color getIntensity(Point p) {
        double distance = (p.distance(position));
        double distancesquared = (p.distanceSquared(position));
        Double3 factor = (kC.add(kL.scale(distance)).add(kQ.scale(distancesquared)));
        return getIntensity().reduce(factor);
    }

    /**
     * get for L, A function that does the calculation of vectors that damage light
     *- soft shadows
     * @param p-point of     light
     * @param n- vector of ray
     * @return List Vector - Vectors that damage light
     */
    @Override
    public List<Vector> listGetL(Point p, Vector n) {
        List<Vector> vectorList = new LinkedList<>();
        vectorList.add(p.subtract(position).normalize());
        Sphere sphere = new Sphere(position, lenVector);//We turned the point of light into a sphere
            for (double i = 0; i < sphere.getRadius(); i += sphere.getRadius() / size) {//Creates vectors for the first half of the sphere
                for (double j = 0; j < sphere.getRadius(); j += sphere.getRadius() / size) {
                    Point point = position.add(new Vector(sphere.getRadius() - i, 0.1d, sphere.getRadius() - j));
                    vectorList.add(p.subtract(point).normalize());
                }
            }
            for (double i = -sphere.getRadius(); i < 0; i += sphere.getRadius() / size) {//Creates vectors for the second half of the  sphere
                for (double j = -sphere.getRadius(); j < 0; j += sphere.getRadius() / size) {
                    Point point = position.add(new Vector(0 + i, 0.1d, 0 + j));
                    vectorList.add(p.subtract(point).normalize());
                }

        }
        return vectorList;
    }

    /**
     *Returns one vector that meets a point of light
     * @param p-point of  light
     * @return - vector
     */
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}