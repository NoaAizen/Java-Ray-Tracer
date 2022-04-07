package renderer;

import Scene.Scene;
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
    private Color calcColor(Point point) {
        return scene.getAmbientLight().getIntensity();
    }

    /**
     * trace ray, and find cross point and return calcColor of the point if dont have point return Backgroung
     * @param ray
     * @return Color
     */
    @Override
    public Color traceRay(Ray ray) {
        var listOfPoints= scene.getGeometries().findIntersections(ray);
      if(listOfPoints == null){
          return  scene.getBackground();
      }
        Point closetsPoint = ray.findClosestPoint(listOfPoints);
        return calcColor(closetsPoint);
    }
}