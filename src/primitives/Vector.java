package primitives;
import java.util.Objects;
import static primitives.Util.*;

/**
 * @author noa and noga
 */
public class Vector extends Point {
    // ***************** constructor ********************** //
    /**
     * constructor with Double
     * @param x - double
     * @param y - double
     * @param z - double
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
       if (Double3.ZERO.equals(xyz)) {
            throw new IllegalArgumentException("Error!! zero vector");
        }
    }

    /**
     * constructor with Double3
     * @param xyz - Double3
     */
    public Vector(Double3 xyz) {
        super(xyz);
      if (Double3.ZERO.equals(xyz)) {
            throw new IllegalArgumentException("Error!! zero vector");
        }

    }

    // ***************** func ********************** //
    /**
     * add vectors-
     * @param vector-The second vector you get for making a connection
     * @return Vector- The new vector after the connection
     */
    public Vector add(Vector vector) {

        Double3 result = xyz.add(vector.xyz);

        if (Double3.ZERO.equals(result)) {
            throw new IllegalArgumentException("Error!! zero vector");
        }
        return new Vector(result);
    }

    /**
     * Scale with vector
     * @param factor--The second vector you get for making a Scale
     * @return - The new vector after the scale
     */
    public Vector scale(double factor){
        if(isZero(factor)){
            throw  new IllegalArgumentException("Error!! zero vector");
        }
        return new Vector(xyz.scale(factor));
    }

    /**
     * Vector multiplication-dot Product
     * @param vector- the second vector you get for making a -dot Product
     * @return - The new number generated from a vector product
     */
    public double dotProduct(Vector vector) {
        Double3 temp = xyz.product(vector.xyz);
        return temp.d1 + temp.d2 + temp.d3;

    }

    /**
     * Vectors multiplication-cross Product
     * @param vector- the second vector you get for making a cross Product
     * @return Vector- The new Vector generated from a vector product
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
     * @return Vector-Victor who is normalized
     */
    public Vector normalize() {
        double len = length();
        return new Vector(xyz.reduce(len));
    }

    /**
     * length
     * @return double-The number returned from a root
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * length Squared
     * @return double-The length itself
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1
                + xyz.d2 * xyz.d2
                + xyz.d3 * xyz.d3;
    }

    // ***************** Override ********************** //
    /**
     * to String
     * @return string
     */
    @Override
    public String toString() {
        return "Vector{" +
                xyz +
                '}';
    }
}