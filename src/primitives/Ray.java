package primitives;

import primitives.Point;
import primitives.Double3;
import primitives.Vector;

import java.util.List;
import java.util.Objects;

import static primitives.Util.*;

public class Ray {
    final Point p0;
    final Vector dir;

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

    /**
     * toString
     * @return
     */
    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }


    /**
     * equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p0, dir);
    }

    /**
     * constructor
     * @param p0
     * @param dir
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * func of get
     * @return vector
     */
    public Vector getDirection() {
        return new Vector(dir.xyz);
    }

    /**
     * Multiply by double the point
     * @param t
     * @return point
     */
    public Point getPoint(double t){
        if (isZero(t)){
            return  p0;
        }
        return p0.add(dir.Scale(t));
    }

    /**
     * @param pointList
     * @return the point closest to the beginning of the foundation.
     */
    public Point findClosestPoint(List<Point> pointList)//
    {
        Point closestPoint=null;
        double distance=Double.MAX_VALUE;
        double d;
        if (pointList==null)
            return null;
        if(!pointList.isEmpty())
            return closestPoint;
        for (var pt: pointList)//בודק את נקודה הכי קורבה לקרן עם חישוב מרחק
        {
            d=p0.distance(pt);

            if(d<distance)
            {
                distance=d;
                closestPoint=pt;
            }

        }
        return  closestPoint;
    }
}
