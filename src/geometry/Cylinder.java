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
public class Cylinder extends Tube {

    double height;

    /**
     * constctor using super main using Polygon

     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * height of Cylinder
     * @return double
     */
    public double getHeight() {
        return height;
    }

    /**
     * return vector normal
     * @param p
     * @return vector normal
     */
    @Override
    public Vector getNormal(Point p){
        return null;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                '}';
    }
}
