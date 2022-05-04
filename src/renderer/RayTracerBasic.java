package renderer;

import Scene.Scene;
import lighting.LightSource;
import primitives.*;
import primitives.Ray;
import static geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    //בשביל עצירה של רקוסיה והיא עושה תלויה בהשתקפות
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;
    private static final double DELTA = 0.1;    //תיעוד

    //תיעוד
    private Double3 transparency(GeoPoint gp, LightSource lightSource, Vector l, Vector n, double nv) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(gp.point, lightDirection, n);
        double maxdistance = lightSource.getDistance(gp.point);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay);

        if (intersections==null){
             return Double3.ONE;
        }//משתנה K כמות הצללה
        Double3 ktr=Double3.ONE;
        for (var item: intersections ) {
            var kT=item.geometry.getMaterial().kT;
            ktr=kT.product(ktr);
            }
        return ktr;
    }



    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
      if(intersections==null)  {
          return null;
      }
        GeoPoint closestPoint = ray.findGeoClosestPoint(intersections);
        return closestPoint;
    }

    // ***************** Constructors ********************** //

    /**
     * Constructor
     *
     * @param scene
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

    /**
     * Calculate the Color
     *
     * @param gp
     * @return color
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.getAmbientLight().getIntensity());
           }

    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray,k);
        if(level == 1){
            return color;
        }
         color = color.add(calcGlobalEffects(gp, ray, level, k));
        return color;
    }

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

    private Ray constructRefractedRay(Point point, Ray ray, Vector n) {
        return  new Ray(point,ray.getDir(),n.normalize());
    }

    private Ray constructReflectedRay(Point point, Ray ray, Vector n) {//השתקפות
        Vector v = ray.getDir();
        double vn = alignZero(v.dotProduct(n));
        Vector r = v.subtract(n.scale(2*vn));
        return new Ray(point,r,n);
    }

    /**
     * Calculate refraction and reflection of bodies
     *
     * @param geoPoint
     * @param ray
     * @return Color
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray,Double3 k) {
        Color color = geoPoint.geometry.getEmission();
    Vector v = ray.getDir();
    Vector n = geoPoint.geometry.getNormal(geoPoint.point);
    double nv = alignZero(n.dotProduct(v));
    Material material = geoPoint.geometry.getMaterial();
        if (nv == 0)
            return color.BLACK;
        for (LightSource lightSource : scene.getLights()) {
        Vector l = lightSource.getL(geoPoint.point);
        double nl = alignZero(n.dotProduct(l));
        if (nl * nv > 0) {
            Double3 ktr = transparency(geoPoint,lightSource,l, n, nv);
            if(!k.product(ktr).lowerThan(MIN_CALC_COLOR_K)){
                Color iL = lightSource.getIntensity(geoPoint.point).scale(ktr);
                color = color.add(iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
    }
        return color;
    }
    /**
     * Calculate Flashes, a kind of reflection of light, on the surface
     *
     * @param material
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return Double3
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
     * Calculate Diffusive, The surfaces of the bodies are not smooth, so the reflected light is scattered
     * In all directions, affects light and shadow on the body, creates a deep look.
     *
     * @param material
     * @param nl
     * @return Double3
     */
    private Double3 calcDiffusive (Material material, double nl) {
        nl = Math.abs(nl);
        Double3 result = material.getkD().scale(nl);
        return result;

    }


    /**
     * trace ray, and find cross point and return calcColor of the point if dont have point return Backgroung
     *
     * @param ray
     * @return Color
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

