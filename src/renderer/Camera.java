package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * noa and noga
 */
public class Camera {


    private double printInterval;
    /**
     * num of threads
     */
    private int threadsCount;
    /**
     * The distance from our viewing plane
     */
    private Vector Vto;
    /**
     * Top direction of the camera
     */
    private Vector Vup;
    /**
     * Camera direction - Vright Camera right direction
     */
    private Vector Vright;
    /**
     * Position the camera in the space of the center of the lens
     */
    private Point P0;

    /**
     * The distance between the camera and the view plane
     */
    private double distance;
    /**
     * Image width
     */
    private double width;
    /**
     * Image height
     */
    private double height;
    /**
     * Creating an image file, and also on holding the color matrix
     */
    private ImageWriter imageWriter;
    /**
     *
     */
    private RayTracerBase rayTracerBase;

    /**
     * A field that helps calculate how many rays we want to have
     */
    private int size = 1;
    private boolean Antialiasing = false;
    private boolean SuperSampling = false;
    private int recursionDepthOrg = 0;
    /**
     *
     */


    // ***************** Constructors ********************** //

    /**
     * constructor - with parameters for position values and two vectors of direction
     *
     * @param p0 - Point
     * @param vto - Vector
     * @param vup - Vector
     */
    public Camera(Point p0, Vector vto, Vector vup) {
        if (!isZero(vto.dotProduct(vup))) {
            throw new IllegalArgumentException("Error!! Two vectors not orthogonals");
        }

        P0 = p0;
        Vto = vto.normalize();
        Vup = vup.normalize();
        Vright = vto.crossProduct(vup).normalize();

    }

// ***************** Getters ********************** //


    /**
     * return Vto the vector to the view plane
     *
     * @return Vector
     */
    public Vector getVto() {
        return Vto;
    }

    /**
     * return Vup the vector is orthogonals to Vto and Vright
     *
     * @return Vector
     */
    public Vector getVup() {
        return Vup;
    }

    /**
     * return Vright the vector is orthogonals to Vto and Vright
     *
     * @return Vector
     */
    public Vector getVright() {
        return Vright;
    }

    /**
     * return the point that the camera be there
     *
     * @return p0
     */
    public Point getP0() {
        return P0;
    }

    /**
     * getDistance - the distance between the camera and view plane
     *
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * get func
     *
     * @return width (double)
     */
    public double getWidth() {
        return width;
    }

    /**
     * getHeight func
     *
     * @return height (double)
     */
    public double getHeight() {
        return height;
    }

    // ***************** Setters ********************** //

    /**
     * func setter
     *
     * @param size int
     * @return Camera
     */
    public Camera setSize(int size) {
        this.size = size;
        return this;
    }

    /**
     * func setter
     *
     * @param superSampling - boolean
     * @return - Camera
     */
    public Camera setSuperSampling(boolean superSampling) {

        SuperSampling = superSampling;
        return this;
    }

    /**
     * func setter
     *
     * @param printInterval - double
     * @return - Camera
     */
    public Camera setPrintInterval(double printInterval) {
        this.printInterval = printInterval;
        return this;
    }

    /**
     * func setter
     *
     * @param antialiasing - boolean
     * @return Camera
     */
    public Camera setAntialiasing(boolean antialiasing) {
        Antialiasing = antialiasing;
        return this;
    }

    /**
     * func setter
     *
     * @param recursionDepthOrg int
     * @return Camera
     */
    public Camera setRecursionDepthOrg(int recursionDepthOrg) {
        this.recursionDepthOrg = recursionDepthOrg;
        return this;
    }

    /**
     * constructor - Update method (set) for the View Plane size, which receives two numbers - width and height
     *
     * @param w double
     * @param h double
     * @return this \ camera
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
     *  set of ThreadsCount
     * @param threadsCount int
     * @return Camera
     */
    public Camera setThreadsCount(int threadsCount) {
        this.threadsCount = threadsCount;
        return this;
    }

    /**
     * Update method for the View Plane distance from the camera
     *
     * @param d double
     * @return camera
     */
    public Camera setVPDistance(double d) {
        if (d <= 0) {
            throw new IllegalArgumentException(" distance can not be negative or 0");
        }
        distance = d;
        return this;
    }

    /**
     * set Ray Tracer
     *
     * @param rayTracer - RayTracerBase
     * @return Camera
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        rayTracerBase = rayTracer;
        return this;
    }

    /**
     * turn on imageWriter
     *
     * @param imageWriter ImageWriter
     * @return Camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    // ***************** func ********************** //

    /**
     * Returns the cut ray, before a formula learned in class
     *
     * @param nX-size of view plan
     * @param nY-size of view plan
     * @param j-      Pixel     Location
     * @param i-      Pixel     Location
     * @return ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point Pc = P0.add(Vto.scale(distance));
        double Rx = height / nX;
        double Ry = width / nY;

        double Yi = -(i - (nY - 1) / 2d) * Ry;
        double Xj = (j - (nX - 1) / 2d) * Rx;
        Point Pij = Pc;

        if (isZero(Xj) && isZero(Yi)) {
            return new Ray(P0, Pij.subtract(P0));
        }
        if (isZero(Xj)) {
            Pij = Pij.add(Vup.scale(Yi));
            return new Ray(P0, Pij.subtract(P0));
        }
        if (isZero(Yi)) {
            Pij = Pij.add(Vright.scale(Xj));
            return new Ray(P0, Pij.subtract(P0));
        }
        Pij = Pij.add(Vright.scale(Xj).add(Vup.scale(Yi)));
        return new Ray(P0, Pij.subtract(P0));
    }

    /**
     * A function that calculates the midpoint with a pixel with a viewing plane and a camera
     *
     * @param nX-size of view plan
     * @param nY-size of view plan
     * @param j-      Pixel     Location
     * @param i-      Pixel     Location
     * @return Point Middle
     */
    public Point PointMiddle(int nX, int nY, int j, int i) {
        Point Pc = P0.add(Vto.scale(distance));
        double Rx = width / (double) nX;
        double Ry = height / (double) nY;
        double Yi = (-(i - ((double) nY - 1) / 2d) * Ry);
        double Xj = ((j - ((double) nX - 1) / 2d) * Rx);
        Point Pij = Pc;
        if (Xj != 0d) {
            Pij = Pij.add(getVright().scale(Xj));
        }
        if (Yi != 0d) {
            Pij = Pij.add(getVup().scale(Yi));
        }
        return Pij;
    }

    /**
     * Antialiasing
     * Function for calculating that a list of rays intersects the pixel
     *
     * @param nX-size of view plan
     * @param nY-size of view plan
     * @param j-      Pixel     Location
     * @param i-      Pixel     Location
     * @return List of rays cut
     */
    public List<Ray> constructRayS(int nX, int nY, int j, int i) {
        Point pointCnetr = PointMiddle(nX, nY, j, i);
        List<Ray> rayList = new LinkedList<>();
        rayList.add(new Ray(P0, pointCnetr.subtract(P0)));
        double Rx = ((width) / nX) / (double) size;
        double Ry = ((height) / nY) / (double) size;
        double x = 0;
        double y = 0;

        for (int r = 0; r < size; r++) {
            if (r > size / 2) {
                y = (size / 2) + (size - r) * Ry;
            } else {
                y = (size / 2) - r * Ry;
            }
            for (int c = 0; c < size; c++) {
                if (c > size / 2) {
                    x = (size / 2) + (size - c) * Rx;
                } else {
                    x = (size / 2) - c * Rx;
                }
            }
            Point Pij = pointCnetr;
            if (isZero(x) && isZero(y)) {
                Pij = Pij.subtract(P0);
            }
            if (isZero(y)) {
                Pij = Pij.add(Vright.scale(x));
            } else if (isZero(x)) {
                Pij = Pij.add(Vup.scale(y));
            } else {
                Pij = Pij.add(Vright.scale(x).add(Vup.scale(y)));
            }
            rayList.add(new Ray(P0, Pij.subtract(P0)));

        }
        return rayList;
    }

    /**
     * turn off writeToImage
     * @return Camera
     */
    public Camera writeToImage() {
        imageWriter.writeToImage();
        return this;
    }

    /**
     * * Print Grid call to func writePixel
     * turn on printGrid with gap and intervalColor the func get
     *
     * @param gap-
     * @param intervalColor-color
     */
    public void printGrid(int gap, Color intervalColor) {
        imageWriter.printGrid(gap, intervalColor);
    }

    /**
     * check imageWriter and rayTracerBase is null, and check height and width and distance is zero
     * and write pixel.
     */
    public void renderImage() {
        try {
            if (imageWriter == null)
                throw new MissingResourceException("dont have something in image writer ", "ImageWriter", " writer");
            if (rayTracerBase == null)
                throw new MissingResourceException("dont have something in ray tracer", "RayTracerBase", " ray trace");
            if (height == 0 || width == 0 || distance == 0)
                throw new MissingResourceException("One of the camera's elemnts is illegal", "double", "double");

            int Ny = imageWriter.getNy();
            int Nx = imageWriter.getNx();
            //Activating the processes
            if (Antialiasing) {
                Pixel.initialize(Ny, Nx, printInterval);
                while (threadsCount-- > 0) {
                    new Thread(() -> {
                        for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                            castRay(Nx, Ny, pixel.col, pixel.row);
                    }).start();
                }
                Pixel.waitToFinish();

            }
            if (SuperSampling) {//
                Pixel.initialize(Ny, Nx, printInterval);
                while (threadsCount-- > 0) {
                    new Thread(() -> {
                        for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                            castRaySuperSampling(Nx, Ny, pixel.col, pixel.row);
                    }).start();
                }
                Pixel.waitToFinish();
            }
        } catch (Exception exception) {
            throw new UnsupportedOperationException("can not render the image ");
        }

    }

    /**
     * Antialiasing
     * Returns the color of a pixel before the average color and also with more rays cut in the pixel     * @param Nx
     *
     * @param Ny-The  size of the plain
     * @param i-pixel
     * @param j-pixel
     * @param Nx-The  size of the plain
     */
    private void castRay(int Nx, int Ny, int j, int i) {
        if (size == 1) {//If we have one fund left
            imageWriter.writePixel(j, i, rayTracerBase.traceRay(constructRay(Nx, Ny, j, i)));
        } else {
            int red = 0;
            int green = 0;
            int blue = 0;
            var rays = constructRayS(Nx, Ny, j, i);//The list of rays cuts the pixel
            Color colors = null;
            for (var ray : rays) {//Insert a color into the appropriate variable
                Color temp = rayTracerBase.traceRay(ray);
                red += temp.getColor().getRed();
                green += temp.getColor().getGreen();
                blue += temp.getColor().getBlue();
            }
            colors = new Color(red / (rays.size()), green / (rays.size()), blue / (rays.size()));//Calculate the color mean
            imageWriter.writePixel(j, i, colors);
        }
    }

    // ***************** super ********************** //

    /**
     * cell to rec func
     * @param nX - int
     * @param nY - int
     * @param j - int
     * @param i - int
     */
    private void castRaySuperSampling(int nX, int nY, int j, int i) {
        int recursionDepth = 1;
        Ray RaypointCenter = constructRay(nX, nY, j, i);
        Point pointCenter = RaypointCenter.getPoint(distance);
        double Rx = alignZero(height / nX);
        double Ry = alignZero(width / nY);
        var rayList = constructRaySSuperSampling(Rx, Ry, pointCenter);
        Color color = rayTracerBase.traceRay(RaypointCenter);
        int conter = 1;
        Color temp = null;
        for (var ray : rayList) {
            temp = rayTracerBase.traceRay(ray);
            if (color.equals(temp)) {
                color = color.add(temp);
                conter++;
            } else {
                Rx = alignZero(height / nX) / 4;
                Ry = alignZero(width / nY) / 4;
                Point newpoint = PointSuperSampling(Rx, Ry, pointCenter, conter);
                color = color.add(RecursionSuperSampling(Rx, Ry, newpoint, ray, recursionDepth));
                conter++;

            }

        }
        color = color.reduce(rayList.size() + 1);
        imageWriter.writePixel(j, i, color);
    }

    /**
     * thanks to ?????? ?????
     * rec fun for Super sampling calc the color of the pixel
     * @param Rx - double
     * @param Ry - double
     * @param pointCenter - Point
     * @param ray - Ray
     * @param recursionDepth - int
     * @return Color
     */
    private Color RecursionSuperSampling(double Rx, double Ry, Point pointCenter, Ray ray, int recursionDepth) {

        if (recursionDepth > 0) {
            recursionDepth--;
            var rayCenter = new Ray(P0, pointCenter.subtract(P0));
            Color color = rayTracerBase.traceRay(rayCenter);
            var rayList = constructRaySSuperSampling(Rx, Ry, pointCenter);
            int conter = 1;
            Color temp = null;
            for (var rays : rayList) {
                temp = rayTracerBase.traceRay(rays);
                if (color.equals(temp)) {
                    color = color.add(temp);
                    conter++;
                } else {
                    Rx = alignZero(height / Rx) / 4;
                    Ry = alignZero(width / Ry) / 4;
                    Point newpoint = PointSuperSampling(Rx, Ry, pointCenter, conter);
                    color = color.add(RecursionSuperSampling(Rx, Ry, newpoint, ray, recursionDepth));
                    conter++;

                }
            }
            color = color.reduce(rayList.size() + 1);
            return color;

        } else {
            return rayTracerBase.traceRay(ray);
        }
    }

    /**
     * calcu the rays of 4 ver
     * @param Rx - double
     * @param Ry - double
     * @param pointCenter - Point
     * @return List Ray
     */
    public List<Ray> constructRaySSuperSampling(double Rx, double Ry, Point pointCenter) {

        List<Ray> Rays = new LinkedList<>();
        Point pij = pointCenter;
        // leftUp
        pij = pij.add(Vup.scale(Ry)).add(Vright.scale(Rx));
        Rays.add(new Ray(P0, pij.subtract(P0)));
        // leftDown
        pij = pij.add(Vup.scale(-2 * Ry));
        Rays.add(new Ray(P0, pij.subtract(P0)));
        // rightDown
        pij = pij.add(Vright.scale(-2 * Rx));
        Rays.add(new Ray(P0, pij.subtract(P0)));
        // rightUp
        pij = pij.add(Vup.scale(2 * Ry));
        Rays.add(new Ray(P0, pij.subtract(P0)));
        return Rays;
    }

    /**
     * return new point center of tt pixel
     * @param Rx - double
     * @param Ry - double
     * @param pointCenter - Point
     * @param counter - int
     * @return Point
     */
    public Point PointSuperSampling(double Rx, double Ry, Point pointCenter, int counter) {
        switch (counter) {
            case 1:
                // leftUp
                return pointCenter.add(Vup.scale(Ry)).add(Vright.scale(Rx));
            case 2:
                // leftDown
                return pointCenter.add(Vup.scale(Ry)).add(Vright.scale(-Rx));
            case 3:
                // rightDown
                return pointCenter.add(Vup.scale(-Ry)).add(Vright.scale(Rx));
            case 4:
                // rightUp
                return pointCenter.add(Vup.scale(-Ry)).add(Vright.scale(-Rx));
        }
        return null;
    }
}