package primitives;

import java.util.Objects;
import primitives.Double3;

public class Point {
    final Double3 xyz;
    public static Point ZERO=new Point(0,0,0);

    // ***************** Constructors ********************** //
    /**
     * primary constructor for Point
     * @param xyz Double3 value for x,z,z axis
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * secondary constructor for Point
     *
     * @param x coordinate value for X axis
     * @param y coordinate value for Y axis
     * @param z coordinate value for Z axis
     */
    public Point(double x, double y , double z) {
        xyz = new Double3(x,y,z);
    }

    // ***************** Override ********************** //
    /**
     * check if the o and this equals
     * @param o
     * @return true or false if it the same class and the same value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return xyz.equals(point.xyz);
    }

    /**
     * hash code
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    /**
     * to string
     * @return string with point and the value
     */
    @Override
    public String toString() {
        return "Point " + xyz ;
    }

    // ***************** func ********************** //
    /**
     * use add from class Double3
     * @param vector
     * @return the head vector with this point
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * use subtract from class Double3
     * @param point
     * @return Vector subtraction
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * The distance between two points squared
     * @param other
     * @return  d = ((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1))
     */
    public double distanceSquared(Point other){
        double x1 = xyz.d1;
        double y1 = xyz.d2;
        double z1 = xyz.d3;

        double x2 = other.xyz.d1;
        double y2 = other.xyz.d2;
        double z2 = other.xyz.d3;

        return ((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) + (z2 - z1)*(z2 - z1));
    }

    /**
     * use func distanceSquared and sqrt on it
     * @param other
     * @return d = Sqrt (lengthSquare)
     * @link https://www.engineeringtoolbox.com/distance-relationship-between-two-points-d_1854.html
     */
    public  double distance (Point other){
        return Math.sqrt(distanceSquared(other));
    }

    // ***************** getter ********************** //

    /**
     * return d1 of point
     * @return double
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * return d2 of point
     * @return double
     */
    public double getY() {
        return xyz.d2;
    }
}