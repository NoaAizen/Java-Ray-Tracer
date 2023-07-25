package primitives;

import java.util.Objects;
/**
 * Point class represents a point in 3D space with coordinates (x, y, z).
 * It provides methods for performing various calculations with points.
 *
 * Authors: Noga Jacobs and Noa
 */
public class Point {
    /**
     * A field that holds the coordinates of the point as a Double3 object.
     */
    final Double3 xyz;
    /**
     * A field that represents the zero point (0, 0, 0).
     */
    public static Point ZERO = new Point(0, 0, 0);

    // ***************** Constructors ********************** //

    /**
     * Primary constructor for Point.
     *
     * @param xyz The Double3 value representing the x, y, and z coordinates.
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Secondary constructor for Point.
     *
     * @param x The coordinate value for the X axis.
     * @param y The coordinate value for the Y axis.
     * @param z The coordinate value for the Z axis.
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    // ***************** Override ********************** //

    /**
     * Checks if this point is equal to the given object.
     *
     * @param o The second parameter to compare with this point.
     * @return true if the object is the same class and has the same value as this point, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    /**
     * Calculates the hash code for this point.
     *
     * @return int The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    /**
     * Returns a string representation of this point.
     *
     * @return String A string containing the point's coordinates.
     */
    @Override
    public String toString() {
        return "Point " + xyz;
    }

    // ***************** Methods ********************** //

    /**
     * Adds a vector to this point, creating a new point at the end of the vector.
     *
     * @param vector The vector to add to this point.
     * @return Point The new point resulting from the addition.
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * Subtracts another point from this point, creating a new vector from the two points.
     *
     * @param point The second point to subtract from this point.
     * @return Vector The vector resulting from the subtraction.
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * Calculates the squared distance between two points.
     *
     * @param other The second point to calculate the squared distance with.
     * @return double The squared distance between this point and the other point.
     */
    public double distanceSquared(Point other) {
        double x1 = xyz.d1;
        double y1 = xyz.d2;
        double z1 = xyz.d3;

        double x2 = other.xyz.d1;
        double y2 = other.xyz.d2;
        double z2 = other.xyz.d3;

        return ((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1) + (z2 - z1) * (z2 - z1));
    }

    /**
     * Calculates the distance between two points.
     *
     * @param other The second point to calculate the distance with.
     * @return double The distance between this point and the other point.
     */
    public double distance(Point other) {
        return Math.sqrt(distanceSquared(other));
    }

    // ***************** Getter ********************** //

    /**
     * Returns the x-coordinate of this point.
     *
     * @return double The x-coordinate value.
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return double The y-coordinate value.
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * Returns the z-coordinate of this point.
     *
     * @return double The z-coordinate value.
     */
    public double getZ() {return xyz.d3; }
}