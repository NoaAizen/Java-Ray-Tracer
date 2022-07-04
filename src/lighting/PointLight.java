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
    //private int size = 900;
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
     * get form L, Returns the calculation of l
     * @return Vector
     * @param p
     * @param n
     */
    @Override
    public List<Vector> listGetL(Point p,Vector n) {

        double distance = p.subtract(position).length();
        Sphere sphere = new Sphere(position, distance / 10);
        double radius = sphere.getRadius();
        List<Vector> vectors = new LinkedList();
        for (double i = -radius; i < radius; i += radius / 10) {
            for (double j = -radius; j < radius; j += radius / 10) {
                if (i != 0 && j != 0) {
                    Point point = position.add(new Vector(i, 0.1d, j));
                    if (point.equals(position)) {
                        vectors.add(p.subtract(point).normalize());
                    } else {
                        try {
                            if (point.subtract(position).dotProduct(point.subtract(position)) <= radius * radius) {
                                vectors.add(p.subtract(point).normalize());
                            }
                        } catch (Exception e) {
                            vectors.add(p.subtract(point).normalize());
                        }

                    }
                }

            }
        }
        vectors.add(getL(p));
        return vectors;
    }
    /**
    public List<Vector> listGetL(Point p, Vector n) {
        // list of vector
        List<Vector> vectorList = new LinkedList<>();
        //enter the position to the list
        vectorList.add(p.subtract(position).normalize());
        //the point become sphere
        Sphere sphere = new Sphere(position, lenVector);
        // Random r = new Random();
        // int t;
        for (double i = 0; i < sphere.getRadius() - 0.0001; i += sphere.getRadius() / 2) {
            for (double j = 0; j < sphere.getRadius() - 0.0001; j += sphere.getRadius() / 2) {
                    //t = r.nextInt(0, 10);
                    //Point pt = new Point(position.getX() + t, position.getY() + t, position.ge.tZ() + t);
                    //vectorList.add( v = pt.subtract(p).normalize());
                Point point = position.add(new Vector(sphere.getRadius() - i, 0.1d, sphere.getRadius() - j));
                System.out.println("One point = "+ point+ " i=" + i+"  j="+ j);
                vectorList.add(p.subtract(point).normalize());
                }
            }
        for (double i = -sphere.getRadius() + 0.0001; i < 0; i += sphere.getRadius() / 2) {
            for (double j = -sphere.getRadius() + 0.0001; j < 0; j += sphere.getRadius() / 2) {
                    //t = r.nextInt(-10, 0);
                    //Point pt = new Point(position.getX() + t, position.getY() + t, position.getZ() + t);
                    //vectorList.add( pt.subtract(p).normalize());
                Point point = position.add(new Vector(0 + i, 0.1d, 0 + j));
                System.out.println("Two point = "+ point+ " i=" + i+"  j="+ j);


                vectorList.add(p.subtract(point).normalize());
            }
        }

        return vectorList;
    }

    public List<Vector> listGetL(Point p,Vector n) {
        // ????? ?? ???????
        List<Vector> vectorList = new LinkedList<>();
        //?????? ?? ?????? ???????
        vectorList.add(p.subtract(position).normalize());
        //????? ?? ?????? ?????
        Sphere sphere = new Sphere(position, lenVector);
        //Ray ray= new Ray(p, getL(p), n);
        //p=ray.getP0();
        Random r = new Random();
        //int in = size / 2;
        //int out = size - size / 2;
        if (size > 1) {
            int t;
            for (double i = -sphere.getRadius(); i < sphere.getRadius(); i += sphere.getRadius() / 2) {
                for (double j = -sphere.getRadius(); j < sphere.getRadius(); j += sphere.getRadius() / 2) {
                    if (i != 0 && j != 0) {
                        Point point = position.add(new Vector(i, 0.1d, j));
                        if (point.equals(position)) {
                            vectorList.add(p.subtract(point).normalize());
                        }
                        else {
                            try {
                                if (point.subtract(position).dotProduct(point.subtract(position)) <= sphere.getRadius() * sphere.getRadius()) {
                                    vectorList.add(p.subtract(point).normalize());
                                }
                            } catch (Exception e) {
                                vectorList.add(p.subtract(point).normalize());
                            }
                        }
                    }
                }
            }
        }
        return vectorList;
    }
*/

    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}