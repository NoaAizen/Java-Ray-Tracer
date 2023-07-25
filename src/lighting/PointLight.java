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
 * PointLight class represents a point light source in a scene.
 * It provides methods to calculate the intensity of the light at a specific point,
 * as well as to get the direction vectors from the light source to a given point.
 * The class also supports soft shadows by considering the light source as a sphere of rays.
 *
 * Authors: Noga Jacobs and Noa
 */
public class PointLight extends Light implements LightSource {
    /**
     * The number of rays in the sphere of the point light for soft shadows.
     */
    private int size = 20;
    /**
     * The length of the vector representing the sphere of rays.
     */
    private int lenVector = 10;
    /**
     * The position of the point light in the scene.
     */
    private Point position;
    /**
     * Fixed discount coefficient (constant).
     */
    private Double3 kC = Double3.ONE;
    /**
     * Discount coefficient that varies with distance.
     */
    private Double3 kL = Double3.ZERO;
    /**
     * Discount coefficient that varies with the square of the distance.
     */
    private Double3 kQ = Double3.ZERO;

    // ***************** Constructor ********************** //

    /**
     * Constructor for the PointLight class.
     *
     * @param intensity The color intensity of the light.
     * @param _position  The position of the point light in the scene.
     */
    public PointLight(Color intensity, Point _position) {
        super(intensity);
        this.position = _position;
    }

    // ***************** Setter ********************** //

    /**
     * Setter for the position of the point light.
     *
     * @param position The position of the point light in the scene.
     * @return The updated PointLight object.
     */
    public PointLight setPosition(Point position) {
        this.position = position;
        return this;
    }

    /**
     * Setter for the constant discount coefficient (kC).
     *
     * @param kC The constant discount coefficient.
     * @return The updated PointLight object.
     */
    public PointLight setkC(Double3 kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Setter for the linear discount coefficient (kL).
     *
     * @param _kL The linear discount coefficient.
     * @return The updated PointLight object.
     */
    public PointLight setkL(Double3 _kL) {
        this.kL = _kL;
        return this;
    }

    /**
     * Setter for the quadratic discount coefficient (kQ).
     *
     * @param _kQ The quadratic discount coefficient.
     * @return The updated PointLight object.
     */
    public PointLight setkQ(Double3 _kQ) {
        this.kQ = _kQ;
        return this;
    }

    /**
     * Setter for the constant discount coefficient (kC).
     *
     * @param _kC The constant discount coefficient as a double.
     * @return The updated PointLight object.
     */
    public PointLight setkC(double _kC) {
        this.kC = new Double3(_kC);
        return this;
    }

    /**
     * Setter for the linear discount coefficient (kL).
     *
     * @param _kL The linear discount coefficient as a double.
     * @return The updated PointLight object.
     */
    public PointLight setkL(double _kL) {
        this.kL = new Double3(_kL);
        return this;
    }

    /**
     * Setter for the quadratic discount coefficient (kQ).
     *
     * @param _kQ The quadratic discount coefficient as a double.
     * @return The updated PointLight object.
     */
    public PointLight setkQ(double _kQ) {
        this.kQ = new Double3(_kQ);
        return this;
    }
    // ***************** Override ********************** //

    /**
     * Get the intensity of the light at a specified point in the scene.
     *
     * @param p The point at which the intensity of the light is calculated.
     * @return The color intensity of the light at the specified point.
     */
    @Override
    public Color getIntensity(Point p) {
        double distance = (p.distance(position));
        double distancesquared = (p.distanceSquared(position));
        Double3 factor = (kC.add(kL.scale(distance)).add(kQ.scale(distancesquared)));
        return getIntensity().reduce(factor);
    }

    /**
     * Get the list of direction vectors from the point light to a given point for soft shadows.
     * The method creates a sphere of rays representing the point light and generates vectors from the sphere to the point.
     *
     * @param p The point for which the direction vectors are calculated.
     * @param n The normal vector at the specified point.
     * @return A list of direction vectors from the point light to the specified point.
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
     * Get the direction vector from the point light to a given point.
     *
     * @param p The point for which the direction vector is calculated.
     * @return The direction vector from the point light to the specified point.
     */
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * Get the distance from the point light to a given point.
     *
     * @param point The point for which the distance to the point light is calculated.
     * @return The distance from the point light to the specified point.
     */
    public double getDistance(Point point) {
        double distance = point.distance(position);
        return distance;
    }
}