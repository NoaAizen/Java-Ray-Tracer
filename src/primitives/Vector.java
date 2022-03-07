package primitives;

import primitives.Point;
import primitives.Double3;

public class Vector extends Point {
    /**
     * constructor with Double
     * @param x
     * @param y
     * @param z
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
        if (Double3.ZERO.equals(this)) {
            throw new IllegalArgumentException("Error!! zero vector");
        }

    }

    /**
     * constructor with Double3
     * @param xyz
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (Double3.ZERO.equals(xyz)) {
            throw new IllegalArgumentException("Error!! zero vector");
        }
    }

    /**
     * add vector
     * @param vector
     * @return
     */
    public Vector add(Vector vector) {

        Double3 result = new Double3(
                xyz.d1 + vector.xyz.d1,
                xyz.d2 + vector.xyz.d2,
                xyz.d3 + vector.xyz.d3);

        if (Double3.ZERO.equals(result)) {
            throw new IllegalArgumentException("Error!! zero vector");
        }
        return new Vector(result.d1,result.d2, result.d3);
    }

    /**
     * Scale
     * @param temp
     * @return
     */
    Vector Scale(double temp){
        Double3 result = new Double3(xyz.d1*temp,xyz.d2*temp,xyz.d3*temp);

        if (Double3.ZERO.equals(result)) {
            throw new IllegalArgumentException("Error!! zero vector");
        }
        return new Vector(result.d1,result.d2, result.d3);
    }

    /**
     * dot Product
     * @param vector
     * @return
     */
    public double dotProduct(Vector vector) {
        return this.xyz.d1 * vector.xyz.d1
                +this.xyz.d2 * vector.xyz.d2
                + this.xyz.d3 * vector.xyz.d3;

    }

    /**
     * cross Product
     * @param vector
     * @return
     */
    public Vector crossProduct(Vector vector) {
        double ax = xyz.d1;
        double ay = xyz.d2;
        double az = xyz.d3;

        double bx = vector.xyz.d1;
        double by = vector.xyz.d2;
        double bz = vector.xyz.d3;

        double cx = ay * bz - az * by;
        double cy = az * bx - ax * bz;
        double cz = ax * by - ay * bx;

        return new Vector(cx, cy, cz);
    }

    /**
     * normalize
     * @return
     */
    public Vector normalize() {
        double len = length();
        return new Vector(xyz.reduce(len));
    }

    /**
     * length
     * @return
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /*
     * lengthSquared
     * @return
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1
                + xyz.d2 * xyz.d2
                + xyz.d3 * xyz.d3;
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        return "Vector{" +
                super.toString() +
                '}';
    }

}