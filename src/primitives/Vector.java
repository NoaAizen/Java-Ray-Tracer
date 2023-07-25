package primitives;
import java.util.Objects;
import static primitives.Util.*;

/**
 * Vector class represents a three-dimensional vector in 3D space.
 * It provides operations for vector addition, scalar multiplication, dot product, cross product, normalization,
 * length calculation, and more.
 *
 * Authors: Noga Jacobs and Noa
 */
public class Vector extends Point {

    // ***************** Constructor ********************** //

    /**
     * Constructor with individual components.
     *
     * @param x The x-component of the vector
     * @param y The y-component of the vector
     * @param z The z-component of the vector
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
       if (Double3.ZERO.equals(xyz)) {
            throw new IllegalArgumentException("Error!! zero vector");
        }
    }

    /**
     * Constructor with Double3.
     *
     * @param xyz The Double3 representing the vector components.
     */
    public Vector(Double3 xyz) {
        super(xyz);
      if (Double3.ZERO.equals(xyz)) {
            throw new IllegalArgumentException("Error!! zero vector");
        }

    }

    // ***************** Methods  ********************** //

    /**
     * Adds two vectors together.
     *
     * @param vector The second vector to add.
     * @return Vector The resulting vector after addition.
     */
    public Vector add(Vector vector) {

        Double3 result = xyz.add(vector.xyz);

        if (Double3.ZERO.equals(result)) {
            throw new IllegalArgumentException("Error!! zero vector");
        }
        return new Vector(result);
    }

    /**
     * Scales the vector by a scalar factor.
     *
     * @param factor The scaling factor.
     * @return Vector The new vector after scaling.
     */
    public Vector scale(double factor){
        if(isZero(factor)){
            throw  new IllegalArgumentException("Error!! zero vector");
        }
        return new Vector(xyz.scale(factor));
    }

    /**
     * Calculates the dot product (scalar product) of two vectors.
     *
     * @param vector The second vector to compute the dot product with.
     * @return double The dot product value.
     */
    public double dotProduct(Vector vector) {
        Double3 temp = xyz.product(vector.xyz);
        return temp.d1 + temp.d2 + temp.d3;

    }

    /**
     * Calculates the cross product of two vectors.
     *
     * @param vector The second vector to compute the cross product with.
     * @return Vector The new vector generated from the cross product.
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
     * Normalizes the vector to have a length of 1.
     *
     * @return Vector The normalized vector.
     */
    public Vector normalize() {
        double len = length();
        return new Vector(xyz.reduce(len));
    }

    /**
     * Calculates the length of the vector.
     *
     * @return double The length of the vector.
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Calculates the squared length of the vector (avoiding square root calculations).
     *
     * @return double The squared length of the vector.
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1
                + xyz.d2 * xyz.d2
                + xyz.d3 * xyz.d3;
    }

    // ***************** Override ********************** //

    /**
     * Returns a string representation of the vector.
     *
     * @return String A string representation of the vector.
     */
    @Override
    public String toString() {
        return "Vector{" +
                xyz +
                '}';
    }
}