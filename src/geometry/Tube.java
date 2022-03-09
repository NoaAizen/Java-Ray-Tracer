package geometry;
import java.util.List;

import primitives.*;
import static primitives.Util.*;
/**
 * Triangle class Vertex polygon
 * system
 *
 * @author noga and noa
 */
public class Tube implements Geometry {
    final Ray axisRay;
    final double radius;

    public Tube(Ray axisRay, double radius) {
        this.axisRay = axisRay;
        this.radius = radius;
    }

    /**
     * return axisRay of Tube
     * @return Ray
     */
    public Ray getAxisRay() {
        return axisRay;
    }

    /**
     * return radius of Tube
     * @return double
     */
    public double getRadius() {
        return radius;
    }

    /**
     * return vector normal
     * @param p
     * @return vector normal
     */
    public Vector getNormal(Point p){
        return null;
    }

    /**
     * with Ray (axisRay) and radius (double)
     * @return string
     */
    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + axisRay +
                ", radius=" + radius +
                '}';
    }
}
