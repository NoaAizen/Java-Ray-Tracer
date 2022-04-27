package renderer;

import Scene.Scene;
import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;
import primitives.Color;
import primitives.Point;
import primitives.Ray;

public class RayTracerBasic extends RayTracerBase {


    /**
     * Constructor
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
     * @param geoPoint
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint,Ray ray) {
        //return scene.getAmbientLight().getIntensity();
        Color ambientLight = scene.getAmbientLight().getIntensity();
        Color emission = geoPoint.geometry.getEmission();

        Color result = ambientLight.add(emission);
        return result;
    }

    /**
     * trace ray, and find cross point and return calcColor of the point if dont have point return Backgroung
     * @param ray
     * @return Color
     */
    @Override
    public Color traceRay(Ray ray) {
        /**
        var listOfPoints= scene.getGeometries().findIntersections(ray);
      if(listOfPoints == null){
          return  scene.getBackground();
      }
        Point closetsPoint = ray.findClosestPoint(listOfPoints);
        return calcColor(closetsPoint);
         */

        var intersections = scene.getGeometries().findGeoIntersections(ray);
        if (intersections == null)
            return scene.getBackground();
        GeoPoint closestPoint = ray.findGeoClosestPoint(intersections);
        return calcColor(closestPoint,ray);

    }
}