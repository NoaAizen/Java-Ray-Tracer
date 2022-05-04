package renderer;

import Scene.Scene;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {

    // ***************** Constructors ********************** //
    /**
     * Constructor
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * default Constructor
     *
     */
    public RayTracerBasic() {
        super(null);
    }

    /**
     * Calculate the Color
     * @param geoPoint
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint,Ray ray) {
        Color color = scene.getAmbientLight().getIntensity();
        color = color.add(calcLocalEffects(geoPoint,ray));
        return color;
    }

    /**
     * Calculate refraction and reflection of bodies
     * @param geoPoint
     * @param ray
     * @return Color
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray) {
        Color color = geoPoint.geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        double nv = alignZero(n.dotProduct(v));
        Material material = geoPoint.geometry.getMaterial();
        if(nv==0)
            return color;
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) {
                    Color iL = lightSource.getIntensity(geoPoint.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }

    /**
     *  Calculate Flashes, a kind of reflection of light, on the surface
     * @param material
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return Double3
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.add(n.Scale(-2*nl));
        double minusVR = -alignZero(r.dotProduct(v));
        if (minusVR<=0)
            return Double3.ZERO;
        Double3 result =  material.getkS().scale(Math.pow(minusVR,material.nShininess));
        return result;
    }

    /**
     * Calculate Diffusive, The surfaces of the bodies are not smooth, so the reflected light is scattered
     * In all directions, affects light and shadow on the body, creates a deep look.
     * @param material
     * @param nl
     * @return Double3
     */
    private Double3 calcDiffusive(Material material, double nl) {
        nl = Math.abs(nl);
        Double3 result = material.getkD().scale(nl);
        return result;
    }


    /**
     * trace ray, and find cross point and return calcColor of the point if dont have point return Backgroung
     * @param ray
     * @return Color
     */
    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null)
            return scene.getBackground();
        GeoPoint closestPoint = ray.findGeoClosestPoint(intersections);
        return calcColor(closestPoint,ray);

    }
}