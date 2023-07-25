package renderer;

import Scene.Scene;
import geometries.*;
import lighting.LightSource;
import primitives.*;

import static geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * The class RayTracerBasic extends RayTracerBase and is responsible for performing ray tracing calculations to render a 3D scene.
 * It calculates the color of pixels in the scene by simulating the interaction of light rays with the scene elements.
 * It supports global effects such as reflection and refraction.
 *
 * Authors: Noga Jacobs and Noa
 */
public class RayTracerBasic extends RayTracerBase {

    /**
     * MAX CALC COLOR LEVEL
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    /**
     *  MIN CALC COLOR K
     */
    private static final double MIN_CALC_COLOR_K = 0.001;
    /**
     *  Stop conditions of k
     */
    private static final Double3 INITIAL_K = Double3.ONE;
    /**
     *  ...
     */
    private static final double DELTA = 0.1;

    /**
     * Flag to enable soft shadows
     */
    private boolean useSoftShadows =false;

    /**
     * Setter for enabling or disabling soft shadows
     *
     * @param useSoftShadows - true to enable soft shadows, false to disable
     * @return RayTracerBasic - Returns the current instance of RayTracerBasic
     */
    public RayTracerBasic setUseSoftShadows(boolean useSoftShadows) {
        this.useSoftShadows = useSoftShadows;
        return  this;
    }
    // ***************** Constructors ********************** //

    /**
     * Constructor for RayTracerBasic
     *
     * @param scene - The Scene object representing the 3D scene to be rendered.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * default Constructor
     */
    public RayTracerBasic() {
        super(null);
    }

    // ***************** func's calcColor ********************** //

    /**
     * Private method to calculate the color at a specific point (including global effects)
     *
     * @param gp  - The GeoPoint representing the point of intersection with the ray.
     * @param ray - The Ray for which to calculate the color.
     * @return Color - The calculated color at the given point.
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        Color color=calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K);
        return color.add(scene.getAmbientLight().getIntensity());
    }

    /**
     * Recursive method to calculate the color at a specific point (including global effects)
     *
     * @param gp    - The GeoPoint representing the point of intersection with the ray.
     * @param ray   - The Ray for which to calculate the color.
     * @param level - The recursion level (to limit the number of recursive calls).
     * @param k     - The coefficient to determine the color contribution from global effects.
     * @return Color - The calculated color at the given point with global effects.
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray,k);
        if(level == 1){
            return color;
        }
        color = color.add(calcGlobalEffects(gp, ray, level, k));
        return color;
    }

    /**
     * Private method to calculate global effects such as reflection and refraction.
     *
     * @param gp    - The GeoPoint representing the point of intersection with the ray.
     * @param ray   - The Ray for which to calculate the color.
     * @param level - The recursion level (to limit the number of recursive calls).
     * @param k     - The coefficient to determine the color contribution from global effects.
     * @return Color - The color contribution from global effects (reflection and refraction).
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = Color.BLACK;

        Vector n = gp.geometry.getNormal(gp.point);

        Double3 kr = gp.geometry.getMaterial().kR;
        Double3 kt = gp.geometry.getMaterial().kT;

        Double3 kkr = k.product(kr);
        Double3 kkt = k.product(kt);

        if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
            Ray reflectedRay = constructReflectedRay(gp.point, ray, n);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if (reflectedPoint!=null){
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }}
        if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
            Ray refractedRay = constructRefractedRay(gp.point, ray, n);
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);
            if (refractedPoint!=null) {
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }
        return color;
    }

    /**
     * Private method to construct a refracted ray.
     *
     * @param point - The point on the geometry surface.
     * @param ray   - The original ray.
     * @param n     - The normal vector at the point.
     * @return Ray - The refracted ray.
     */
    private Ray constructRefractedRay(Point point, Ray ray, Vector n) {
        return  new Ray(point,ray.getDir(),n.normalize());
    }

    /**
     * Private method to construct a reflected ray.
     *
     * @param point - The point on the geometry surface.
     * @param ray   - The original ray.
     * @param n     - The normal vector at the point.
     * @return Ray - The reflected ray.
     */
    private Ray constructReflectedRay(Point point, Ray ray, Vector n) {
        Vector v = ray.getDir();
        double vn = alignZero(v.dotProduct(n));
        Vector r = v.subtract(n.scale(2*vn));
        return new Ray(point,r,n);
    }

    /**
     * Calculate effect of lights
     *
     * @param geoPoint -piont cut
     * @param ray-ray cut
     * @return Color-Color according to calculations of light effects
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray,Double3 k) {
        Color color = geoPoint.geometry.getEmission();

        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        Material material = geoPoint.geometry.getMaterial();
        if (nv == 0)
            return color.BLACK;
        Double3 ktr=Double3.ZERO;
        double nl=0.0;
        for (LightSource lightSource : scene.getLights()) {
            List<Vector> listVector = lightSource.listGetL(geoPoint.point,n);//for the soft shadows
            Vector l = lightSource.getL(geoPoint.point);
            int count = 0;
            if (useSoftShadows) {
                for (Vector vector : listVector)
                {
                    nl = alignZero(n.dotProduct(vector));
                    if (nl * nv > 0) {
                        count ++;
                        ktr = ktr.add(transparency(geoPoint, lightSource, vector, n, nv));
                    }
                }
                if (nl * nv > 0) {
                    if (!k.product(ktr).lowerThan(MIN_CALC_COLOR_K)) {
                        Color iL = lightSource.getIntensity(geoPoint.point).scale(ktr.reduce(count));
                        color = color.add(iL.scale(calcDiffusive(material, nl)),
                                iL.scale(calcSpecular(material, n, l, nl, v)));
                    }
                }

            }
            else {
                Vector vector = lightSource.getL(geoPoint.point);
                nl = alignZero(n.dotProduct(vector));
                if (nl * nv > 0) {
                    ktr = transparency(geoPoint, lightSource, vector, n, nv);
                    if (!k.product(ktr).lowerThan(MIN_CALC_COLOR_K)) {
                        Color iL = lightSource.getIntensity(geoPoint.point).scale(ktr);
                        color = color.add(iL.scale(calcDiffusive(material, nl)),
                                iL.scale(calcSpecular(material, n, vector, nl, v)));
                    }
                }
            }
        }
        return color;
    }

    /**
     * Private method to calculate specular reflection.
     *
     * @param material - The material properties of the geometry.
     * @param n        - The normal vector at the point.
     * @param l        - The direction vector from the light source to the point.
     * @param nl       - The dot product of n and l.
     * @param v        - The direction vector of the original ray.
     * @return Double3 - The reflection coefficient.
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.add(n.scale(-2 * nl));
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR <= 0)
            return Double3.ZERO;
        Double3 result = material.getkS().scale(Math.pow(minusVR, material.nShininess));
        return result;
    }

    /**
     * Private method to calculate diffuse reflection.
     *
     * @param material - The material properties of the geometry.
     * @param nl       - The dot product of n and l.
     * @return Double3 - The coefficient for the diffuse color.
     */
    private Double3 calcDiffusive (Material material, double nl) {
        nl = Math.abs(nl);
        Double3 result = material.getkD().scale(nl);
        return result;

    }

    /**
     * Private method to calculate the transparency of an object for shadow rays.
     *
     * @param gp          - The GeoPoint representing the point of intersection with the ray.
     * @param lightSource - The light source.
     * @param l           - The direction vector from the light source to the point.
     * @param n           - The normal vector at the point.
     * @param nv          - The dot product of n and the ray's direction.
     * @return Double3 - The transparency factor.
     */
    private Double3 transparency(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        double maxdistance = lightSource.getDistance(gp.point);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay);

        if (intersections==null){
            return Double3.ONE;
        }
        Double3 ktr=Double3.ONE;
        for (var item: intersections ) {
            var kT=item.geometry.getMaterial().kT;
            ktr=kT.product(ktr);
        }
        return ktr;
    }

    /**
     * Private method to find the closest intersection point of a ray with scene geometry.
     *
     * @param ray - The ray for which to find the closest intersection.
     * @return GeoPoint - The closest intersection point.
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
        if(intersections==null)  {
            return null;
        }
        GeoPoint closestPoint = ray.findGeoClosestPoint(intersections);
        return closestPoint;
    }


    /**
     * The method to trace rays and find the color of pixels in the scene.
     *
     * @param ray - The ray to trace.
     * @return Color - The color of the pixel represented by the ray.
     */
    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null)
            return scene.getBackground();
        GeoPoint closestPoint = ray.findGeoClosestPoint(intersections);
        return calcColor(closestPoint, ray);

    }
}

