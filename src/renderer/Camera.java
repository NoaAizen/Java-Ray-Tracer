package renderer;

import primitives.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * noa and moga
 */
public class Camera {

    private Vector Vto;
    private Vector Vup;
    private Vector Vright;
    private Point P0;

    private double distance;
    private double width;
    private double height;



    /**\
     *     return Vto the vector to the view plane
     * @return Vector
     */
    public Vector getVto() {
        return Vto;
    }


    /**
     *     return Vup the vector is orthogonals to Vto and Vright
     * @return Vector
     */
    public Vector getVup() {
        return Vup;
    }

    /**
     *     return Vright the vector is orthogonals to Vto and Vright
     * @return Vector
     */
    public Vector getVright() {
        return Vright;
    }


    /**
     *     return the point that the camera be there
     * @return p0
     */
    public Point getP0() {
        return P0;
    }


    /**
     * getDistance-    the distance between the camera and view plane
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * get
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * getHeight
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * constructor-with parameters for position values and two vectors of direction
     * @param p0
     * @param vto
     * @param vup
     */
    public Camera( Point p0,Vector vto, Vector vup) {
        if (!isZero(vto.dotProduct(vup))) {
            throw new IllegalArgumentException("Error!! Two vectors not orthogonals");
        }

        P0 = p0;
        Vto = vto.normalize();
        Vup = vup.normalize();
        Vright = vto.crossProduct(vup).normalize();

    }

    /**
     *         constructor-• Update method (set) for the View Plane size, which receives two numbers - width and height
     * @param w
     * @param h
     * @return
     */
    public Camera setVPSize(double w, double h) {
        if (w <= 0 || h <= 0) {
            throw new IllegalArgumentException("Height and width can not be negative or 0");
        }
        width = w;
        height = h;
        return this;
    }

    /**
     * • Update method for the View Plane distance from the camera
     * @param d
     * @return camera
     */
    public Camera setVPDistance(double d) {
        if (d <= 0) {
            throw new IllegalArgumentException(" distance can not be negative or 0");
        }
       distance=d;
       return this;
    }

    /**
     * Returns the cut ray, before a formula learned in class
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return ray
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        Point Pc = P0.add(Vto.Scale(distance));
        double Rx= height/nX;
        double Ry=width/nY;
        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;
        Point Pij = Pc;

        if (isZero(Xj) && isZero(Yi)) {
            return new Ray(P0, Pij.subtract(P0));
        }
        if (isZero(Xj)) {
            Pij = Pij.add(Vup.Scale(Yi));
            return new Ray(P0, Pij.subtract(P0));
        }
        if (isZero(Yi)) {
            Pij = Pij.add(Vright.Scale(Xj));
            return new Ray(P0, Pij.subtract(P0));
        }

        Pij = Pij.add(Vright.Scale(Xj).add(Vup.Scale(Yi)));
        return new Ray(P0, Pij.subtract(P0));

    }

}