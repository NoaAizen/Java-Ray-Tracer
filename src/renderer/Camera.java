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
 * The Camera class represents a virtual camera used for rendering images through ray tracing.
 * It defines the camera's position, orientation, field of view, and provides methods for generating rays for each pixel on the view plane.
 *
 * Authors: Noga Jacobs and Noa
 */
public class Camera {

    /**
     * The distance between the camera and the view plane.
     */
    private double distance;

    /**
     * The width and height of the view plane.
     */
    private double width;
    private double height;

    /**
     * The position of the camera in 3D space, representing the center of the lens.
     */
    private Point P0;

    /**
     * The direction vectors of the camera.
     */
    private Vector Vto;
    private Vector Vup;
    private Vector Vright;

    /**
     * The image writer responsible for generating the final rendered image.
     */
    private ImageWriter imageWriter;

    /**
     * The ray tracer used to trace rays and calculate colors in the scene.
     */
    private RayTracerBase rayTracerBase;

    /**
     * The number of rays used for super-sampling (for anti-aliasing and high-quality rendering).
     */
    private int size = 1;

    /**
     * Flag for enabling/disabling anti-aliasing.
     */
    private boolean Antialiasing = false;

    /**
     * Flag for enabling/disabling super-sampling.
     */
    private boolean SuperSampling = false;

    /**
     * The recursion depth for recursive ray tracing (for reflections and refractions).
     */
    private int recursionDepthOrg = 0;

    /**
     * The number of threads to be used for rendering.
     */
    private int threadsCount;

    /**
     * The interval at which to print progress during rendering.
     */
    private double printInterval;

    // ***************** Constructors ********************** //

    /**
     * Constructs a camera with the specified position and orientation.
     *
     * @param p0  The position of the camera (center of the lens).
     * @param vto The vector representing the direction towards the view plane.
     * @param vup The vector representing the upward direction of the camera.
     * @throws IllegalArgumentException If the given direction vectors are not orthogonal.
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
     * Returns the distance between the camera and the view plane.
     *
     * @return The distance between the camera and the view plane.
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Returns the width of the view plane.
     *
     * @return The width of the view plane.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the height of the view plane.
     *
     * @return The height of the view plane.
     */
    public double getHeight() {
        return height;
    }

    // ***************** Setters ********************** //

    /**
     * Sets the size of the rays used for super-sampling (for anti-aliasing and high-quality rendering).
     *
     * @param size The number of rays used for super-sampling.
     * @return This Camera object with the updated size setting.
     */
    public Camera setSize(int size) {
        this.size = size;
        return this;
    }

    /**
     * Enables or disables super-sampling.
     *
     * @param superSampling A boolean value indicating whether to enable or disable super-sampling.
     * @return This Camera object with the updated super-sampling setting.
     */
    public Camera setSuperSampling(boolean superSampling) {

        SuperSampling = superSampling;
        return this;
    }

    /**
     * Sets the interval at which to print progress during rendering.
     *
     * @param printInterval The interval at which to print progress during rendering.
     * @return This Camera object with the updated print interval setting.
     */
    public Camera setPrintInterval(double printInterval) {
        this.printInterval = printInterval;
        return this;
    }

    /**
     * Enables or disables anti-aliasing.
     *
     * @param antialiasing A boolean value indicating whether to enable or disable anti-aliasing.
     * @return This Camera object with the updated anti-aliasing setting.
     */
    public Camera setAntialiasing(boolean antialiasing) {
        Antialiasing = antialiasing;
        return this;
    }

    /**
     * Sets the recursion depth for recursive ray tracing (for reflections and refractions).
     *
     * @param recursionDepth The recursion depth for recursive ray tracing.
     * @return This Camera object with the updated recursion depth setting.
     */
    public Camera setRecursionDepthOrg(int recursionDepthOrg) {
        this.recursionDepthOrg = recursionDepthOrg;
        return this;
    }

    /**
     * Sets the width and height of the view plane.
     *
     * @param w The width of the view plane.
     * @param h The height of the view plane.
     * @return This Camera object with the updated view plane size.
     * @throws IllegalArgumentException If the width or height is zero or negative.
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
     * Sets the number of threads to be used for rendering.
     *
     * @param threadsCount The number of threads to be used for rendering.
     * @return This Camera object with the updated threads count setting.
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
     * Sets the ray tracer to be used for rendering the scene.
     *
     * @param rayTracer The ray tracer to be used for rendering.
     * @return This Camera object with the updated ray tracer setting.
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        rayTracerBase = rayTracer;
        return this;
    }

    /**
     * Sets the image writer responsible for generating the final rendered image.
     *
     * @param imageWriter The image writer to be used for rendering.
     * @return This Camera object with the updated image writer setting.
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    // ***************** func ********************** //

    /**
     * Constructs a ray passing through a specific pixel on the view plane.
     *
     * @param nX The number of pixels in the X direction of the view plane.
     * @param nY The number of pixels in the Y direction of the view plane.
     * @param j  The pixel's X coordinate (column index).
     * @param i  The pixel's Y coordinate (row index).
     * @return Ray The constructed ray passing through the specified pixel.
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
     * Calculates the point in the middle of a pixel on the viewing plane.
     *
     * @param nX The width of the viewing plane (number of pixels in the x-direction).
     * @param nY The height of the viewing plane (number of pixels in the y-direction).
     * @param j The x-coordinate of the pixel location.
     * @param i The y-coordinate of the pixel location.
     * @return The point in the middle of the specified pixel on the viewing plane.
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
     * Writes the final rendered image to the file specified in the ImageWriter.
     * This method should be called after rendering the image.
     *
     * @return This Camera object.
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
     * Renders the image using ray tracing.
     * This method generates rays for each pixel in the view plane and traces those rays to determine the color of each pixel.
     * It takes into account anti-aliasing and super-sampling settings if enabled.
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
     * Casts a single ray or averages the colors from multiple rays for the specified pixel on the view plane.
     * This method is used for anti-aliasing and super-sampling.
     *
     * @param nX The number of pixels in the X direction of the view plane.
     * @param nY The number of pixels in the Y direction of the view plane.
     * @param j  The pixel's X coordinate (column index).
     * @param i  The pixel's Y coordinate (row index).
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
     * Casts multiple rays and averages their colors for super-sampling at the specified pixel on the view plane.
     *
     * @param nX The number of pixels in the X direction of the view plane.
     * @param nY The number of pixels in the Y direction of the view plane.
     * @param j  The pixel's X coordinate (column index).
     * @param i  The pixel's Y coordinate (row index).
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
     * Performs recursive super-sampling to calculate the color of the pixel.
     * This method subdivides the pixel into smaller parts and traces rays to determine the color.
     *
     * @param Rx             The width of the sub-pixel in the X direction.
     * @param Ry             The width of the sub-pixel in the Y direction.
     * @param pointCenter    The center point of the sub-pixel.
     * @param ray            The main ray passing through the center of the pixel.
     * @param recursionDepth The remaining recursion depth.
     * @return Color The calculated color of the pixel after super-sampling.
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
     * Constructs rays for super-sampling at the specified pixel on the view plane.
     * This method is used for super-sampling to generate rays for each sub-pixel within the main pixel.
     *
     * @param Rx          The width of the sub-pixel in the X direction.
     * @param Ry          The width of the sub-pixel in the Y direction.
     * @param pointCenter The center point of the pixel.
     * @return List<Ray> The list of rays used for super-sampling at the specified pixel.
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
     * Returns the new center point of a sub-pixel for super-sampling at the specified pixel.
     * This method is used to calculate the center point of each sub-pixel within the main pixel.
     *
     * @param Rx      The width of the sub-pixel in the X direction.
     * @param Ry      The width of the sub-pixel in the Y direction.
     * @param pointCenter The center point of the main pixel.
     * @param counter The counter indicating the sub-pixel's position (1, 2, 3, or 4).
     * @return Point The center point of the sub-pixel for super-sampling.
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