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

public class PointLight extends Light implements LightSource {
    private int lenVector = 10;
    int sizeRadius = 1;
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
     *
     * @param intensity
     */
    public PointLight(Color intensity, Point _position) {
        super(intensity);
        this.position = _position;
    }

    /**
     * call to func distance in class point
     *
     * @param point
     * @return
     */
    public double getDistance(Point point) {
        double distance = point.distance(position);
        return distance;
    }

    // ***************** setter ********************** //

    /**
     * func setter (type builder)
     *
     * @param position
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
     * @param p
     * @return Color
     */
    @Override
    public Color getIntensity(Point p) {
        double distance = (p.distance(position));
        double distancesquared = (p.distanceSquared(position));
        Double3 factor = (kC.add(kL.scale(distance)).add(kQ.scale(distancesquared)));
        return getIntensity().reduce(factor);
    }

    /**
     * get for L, Returns the calculation of l
     *
     * @param p
     * @return Vector
     */
    @Override
    public List<Vector> listGetL(Point p, Vector n) {
        // ????? ?? ???????
        List<Vector> vectorList = new LinkedList<>();
        //?????? ?? ?????? ???????
        vectorList.add(p.subtract(position).normalize());
        //????? ?? ?????? ?????

        Sphere sphere = new Sphere(position, sizeRadius);
        Random r = new Random();
        if (lenVector > 1) {
            int t;
            for (double i = 0; i < sphere.getRadius(); i += sphere.getRadius() / lenVector) {
                for (double j = 0; j < sphere.getRadius(); j += sphere.getRadius() / lenVector) {
                    Point point = position.add(new Vector(sphere.getRadius() - i, 0.1d, sphere.getRadius() - j));
                    vectorList.add(p.subtract(point).normalize());
                }
            }
            for (double i = -sphere.getRadius(); i < 0; i += sphere.getRadius() / lenVector) {
                for (double j = -sphere.getRadius(); j < 0; j += sphere.getRadius() / lenVector) {
                    Point point = position.add(new Vector(0 + i, 0.1d, 0 + j));
                    vectorList.add(p.subtract(point).normalize());
                }
            }
        }
        return vectorList;
    }


    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}