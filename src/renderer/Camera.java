package renderer;

import primitives.*;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


public class Camera {

    private Vector Vto;
    private Vector Vup;
    private Vector Vright;
    private Point P0;

    private double distance;
    private double width;
    private double height;

    /*
    moth get
     */
    /*
    return Vto the vector to the view plane
     */
    public Vector getVto() {
        return Vto;
    }
    /*
    return Vup the vector is orthogonals to Vto and Vright
     */
    public Vector getVup() {
        return Vup;
    }
    /*
    return Vright the vector is orthogonals to Vto and Vright
    */
    public Vector getVright() {
        return Vright;
    }
    /*
    return the point that the camera be there
     */
    public Point getP0() {
        return P0;
    }
    /*
    the distance between the camera and view plane
    */
    public double getDistance() {
        return distance;
    }
    /*
    return width of the view plane
     */
    public double getWidth() {
        return width;
    }
    /*
    return height of the view plane
    */
    public double getHeight() {
        return height;
    }
    /*
    constructor
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

    public Camera setVPSize(double w, double h) {
        if (w <= 0 || h <= 0) {
            throw new IllegalArgumentException("Height and width can not be negative or 0");
        }
        width = w;
        height = h;
        return this;
    }

    public Camera setVPDistance(double d) {
        if (d <= 0) {
            throw new IllegalArgumentException(" distance can not be negative or 0");
        }
       distance=d;
       return this;
    }
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