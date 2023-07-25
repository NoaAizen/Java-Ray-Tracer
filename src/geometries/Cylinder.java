package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

/**
 * The Cylinder class represents a three-dimensional cylinder in 3D Cartesian coordinate system.
 * It is defined by its axis ray, radius, and height.
 * The Cylinder class is a subclass of the Tube class, which is a type of Geometry.
 * Cylinder objects are immutable.
 * The normal at any point on the cylinder is determined by the direction of the axis ray.
 * The cylinder is capped by two disks at its base and top, which are not explicitly modeled in this class.
 * The caps can be considered part of the cylinder's geometry for certain purposes.
 * The Cylinder class extends the functionality of the Tube class by adding height as an additional parameter.
 *
 * Author: Noga Jacobs and Noa
 */

public class Cylinder extends Tube {

    // Fields
    final double height; // The height of the cylinder


    // ***************** Constructor ********************** //

    /**
     * Constructs a Cylinder with the given axis ray, radius, and height.
     * The Cylinder is essentially a tube with a specific height along its axis.
     *
     * @param axisRay The ray that passes inside the cylinder and determines its direction
     * @param radius The radius of the cylinder
     * @param height The height of the cylinder (along the axis of the tube)
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    // ***************** Getters ********************** //

    /**
     * Returns the height of the cylinder.
     * The height is the distance between the two disks that cap the cylinder.
     *
     * @return The height of the cylinder
     */
    public double getHeight() {
        return height;
    }

    /**
     * Calculates and returns the normal vector at a given point on the cylinder.
     * The normal vector is determined by the direction of the axis ray.
     * If the point is on one of the disk caps, the normal is the same as the direction of the axis ray.
     *
     * @param point The point on the cylinder
     * @return The normal vector at the given point
     */
    @Override
    public Vector getNormal(Point point){
        Point origin =  axisRay.getP0();
        Vector direction = axisRay.getDirection();

        // projection of P-O on the ray:
        double t;
        try {
            t = alignZero(point.subtract(origin).dotProduct(direction));
        } catch (IllegalArgumentException e) { // P = O
            return direction;
        }

        // if the point is at a base
        if (t == 0 || isZero(height - t)) // if it's close to 0, we'll get ZERO vector exception
            return direction;

        origin = origin.add(direction.scale(t));
        return point.subtract(origin).normalize();
    }


    /**
     * Returns a string representation of the cylinder, including its height.
     *
     * @return A string with information about the cylinder, including its height
     */
    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                '}';
    }
}