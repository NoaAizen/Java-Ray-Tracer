package renderer;

import Scene.Scene;
import geometries.Intersectable;
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
     * @param point
     * @return color
     */
    private Color calcColor(Intersectable.GeoPoint point) {
        //return scene.getAmbientLight().getIntensity();
        return scene.getAmbientLight().getIntensity()
                .add(point._geometry.getEmission());
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
        if (intersections == null) return scene.getBackground();
        Intersectable.GeoPoint closestPoint = ray.findGeoClosestPoint(intersections);
        return calcColor(closestPoint);

    }
}