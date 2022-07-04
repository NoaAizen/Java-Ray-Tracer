package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * noa and noga
 */
public class Camera {

    public Camera setPrintInterval(double printInterval) {
        this.printInterval = printInterval;
        return this;
    }

    private  double printInterval;
    /**
     * num of threads
     */
    private int threadsCount;
    /**
     *  The distance from our viewing plane
     */
    private Vector Vto;
    /**
     *  Top direction of the camera
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
     *  The distance between the camera and the view plane
     */
    private double distance;
    /**
     *  Image width
     */
    private double width;
    /**
     *  Image height
     */
    private double height;
    /**
     *  Creating an image file, and also on holding the color matrix
     */
    private ImageWriter imageWriter;
    /**
     *
     */
    private RayTracerBase rayTracerBase;
    /**
     * A field that helps calculate how many rays we want to have
     */
    private int size=100;

    // ***************** Constructors ********************** //
    /**
     * constructor - with parameters for position values and two vectors of direction
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

// ***************** Getters ********************** //
    /**
     * return Vto the vector to the view plane
     * @return Vector
     */
    public Vector getVto() {
        return Vto;
    }

    /**
     * return Vup the vector is orthogonals to Vto and Vright
     * @return Vector
     */
    public Vector getVup() {
        return Vup;
    }

    /**
     * return Vright the vector is orthogonals to Vto and Vright
     * @return Vector
     */
    public Vector getVright() {
        return Vright;
    }

    /**
     * return the point that the camera be there
     * @return p0
     */
    public Point getP0() {
        return P0;
    }

    /**
     * getDistance - the distance between the camera and view plane
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * get func
     * @return width (double)
     */
    public double getWidth() {
        return width;
    }

    /**
     * getHeight func
     * @return height (double)
     */
    public double getHeight() {
        return height;
    }

    // ***************** Setters ********************** //
    /**
     * constructor - Update method (set) for the View Plane size, which receives two numbers - width and height
     * @param w
     * @param h
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
     * set of ThreadsCount
     * @param threadsCount
     */
    public Camera setThreadsCount(int threadsCount) {
        this.threadsCount = threadsCount;
        return  this;
    }
    /**
     * Update method for the View Plane distance from the camera
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
     * set Ray Tracer
     * @param rayTracer
     * @return Camera
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        rayTracerBase = rayTracer;
        return this;
    }

    /**
     * turn on imageWriter
     * @param imageWriter
     * @return Camera
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    // ***************** func ********************** //
    /**
     * Returns the cut ray, before a formula learned in class
     * @param nX
     * @param nY
     * @param j
     * @param i
     * @return ray
     */
    public Ray constructRay(int nX, int nY, int j, int i){
        Point Pc = P0.add(Vto.scale(distance));
        double Rx= height/nX;
        double Ry=width/nY;

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
     *A function that calculates the midpoint with a pixel with a viewing plane and a camera
     * @param nX-size of view plan
     * @param nY-size of view plan
     * @param j-  Pixel     Location
     * @param i- Pixel     Location
     * @return  Point Middle
     */
    public Point PointMiddle(int nX, int nY, int j, int i){
        Point Pc = P0.add(Vto.scale(distance));
        double Rx= width/(double)nX;
        double Ry=height/(double)nY;
        double Yi = (-(i - ((double)nY - 1) / 2d) * Ry);
        double Xj = ((j - ((double)nX - 1) / 2d) * Rx) ;
        Point Pij = Pc;
        if (Xj!=0d){
            Pij=Pij.add(getVright().scale(Xj));
        }
        if (Yi!=0d){
            Pij=Pij.add(getVup().scale(Yi));
        }
        return Pij;
    }

    /**Antialiasing
     *Function for calculating that a list of rays intersects the pixel
     * @param nX-size of view plan
     * @param nY-size of view plan
     * @param j-  Pixel     Location
     * @param i- Pixel     Location
     * @return List of rays cut
     */
        public List<Ray> constructRayS(int nX, int nY, int j, int i){

        Point pointCnetr=PointMiddle(nX,nY,j,i);
        List <Ray> rayList=new LinkedList<>();
        rayList.add(new Ray(P0, pointCnetr.subtract(P0)));
        double Rx= ((width)/nX)/(double) size;
        double Ry=((height)/nY)/(double) size;
        double x=0;
        double y=0;

        for (int r=0;r<size;r++){
             if (r>size/2){
                 y= (size/2)+(size- r)*Ry;
             }
             else {
                 y=(size/2)-r*Ry;
             }
             for (int c=0;c<size;c++){
                if (c>size/2){
                   x=(size/2)+(size- c)*Rx;
                }
                else {
                    x=(size/2)-c*Rx;
                }
                }
                Point Pij = pointCnetr;
                if (isZero(x) && isZero(y)) {
                   Pij= Pij.subtract(P0);
                }
                if (isZero(y)){
                    Pij = Pij.add(Vright.scale(x));
                }
                else  if (isZero(x)) {
                    Pij = Pij.add(Vup.scale(y));
                }
                else {
                    Pij = Pij.add(Vright.scale(x).add(Vup.scale(y)));
                }
                rayList.add(new Ray(P0, Pij.subtract(P0)));

        }
        return rayList;
    }
    /**
     * turn off writeToImage
     * @return
     */
    public Camera writeToImage() {
        imageWriter.writeToImage();
        return this;
    }

    /**
     * turn on printGrid with gap and intervalColor the func get
     * @param gap
     * @param intervalColor
     */
    public void printGrid(int gap, Color intervalColor) {
        imageWriter.printGrid(gap,intervalColor);
    }

    /**
     * check imageWriter and rayTracerBase is null, and check height and width and distance is zero
     * and write pixel.
     */
    public void renderImage() {
        int x=0,y=0;
        try {
            if (imageWriter == null)
                throw new MissingResourceException("dont have something in image writer ", "ImageWriter", " writer");
            if (rayTracerBase == null)
                throw new MissingResourceException("dont have something in ray tracer", "RayTracerBase", " ray trace");
            if (height == 0 || width == 0 || distance == 0)
                throw new MissingResourceException("One of the camera's elemnts is illegal", "double", "double");

            int Ny = imageWriter.getNy();
            int Nx = imageWriter.getNx();
            //  for (int i = 0; i < Ny; i++) {//שורות
            //    for (int j = 0; j < Nx; j++) {
            //      x=i;
            //    y=j;
            //  castRay(Nx, Ny, j, i);
            Pixel.initialize(Ny, Nx, printInterval);
            while (threadsCount-- > 0) {
                new Thread(() -> {
                    for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                        castRay(Nx, Ny, pixel.col, pixel.row);
                }).start();
            }
            Pixel.waitToFinish();

        //}
       // }
        }
        catch (Exception exception){
    throw new UnsupportedOperationException("can not render the image " );
        }
    }

    /** Antialiasing
     Returns the color of a pixel before the average color and also with more rays cut in the pixel     * @param Nx
     * @param Ny
     * @param i
     * @param j
     */
    private void castRay(int Nx, int Ny, int j, int i) {
     if (size==1){//If we have one fund left
         imageWriter.writePixel(j, i,rayTracerBase.traceRay(constructRay(Nx, Ny, j, i)));
     }
     else {
        int red=0;
        int green=0;
        int blue=0;
        var rays=constructRayS(Nx, Ny, j, i);//The list of rays cuts the pixel
        Color colors=null;
        for (var ray: rays){//Insert a color into the appropriate variable
           Color temp=rayTracerBase.traceRay(ray);
            red+=temp.getColor().getRed();
            green+=temp.getColor().getGreen();
            blue+=temp.getColor().getBlue();
        }
        colors=new Color(red/(rays.size()),green/(rays.size()),blue/(rays.size()));//Calculate the color mean
        imageWriter.writePixel(j, i,colors);
    }}}
