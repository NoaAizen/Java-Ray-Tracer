 package renderer;

import Scene.Scene;
import primitives.Color;
import primitives.Ray;

 /**
  * @author noga and noa
  */
 public abstract class RayTracerBase {
     /**
      * Scene
      */
        protected Scene scene;

        // ***************** Constructors ********************
        /**
         * Constructors
         * set of scenez
         * @param scene - Scene
         */
        public RayTracerBase(Scene scene) {
                this.scene = scene;
        }

        /** Calculates the color before the ray
         * @param ray - Ray
         * @return Color-of pixel
         */
        public abstract Color traceRay(Ray ray);

}

