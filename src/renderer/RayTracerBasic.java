package renderer;

import Scene.Scene;
import primitives.Color;
import primitives.Point;
import primitives.Ray;

public class RayTracerBasic extends RayTracerBase {

    //עשינו בכיתה
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
    private Color calcColor(Point point) {
        return scene.getAmbientLight().getIntensity();
    }
//לכתוב תיעוד
    @Override
    public Color traceRay(Ray ray) {
        var listOfPoints=scene.getGeometries().findIntersections(ray);
      if(listOfPoints == null){
          return  scene.getBackground();
      }
        Point closetsPoint=ray.findClosestPoint(listOfPoints);
        return calcColor(closetsPoint);
    }
}