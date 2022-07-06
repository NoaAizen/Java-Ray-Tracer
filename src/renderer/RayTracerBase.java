 package renderer;

import Scene.Scene;
import primitives.Color;
import primitives.Ray;
// * @author noga and noa

public abstract class RayTracerBase {
        protected Scene scene;

        // ***************** Constructors ********************
        /**
         * Constructors
         * set of scenez
         * @param scene
         */
        public RayTracerBase(Scene scene) {
                this.scene = scene;
        }

        /** Calculates the color before the ray
         * @param ray
         * @return Color-of pixel
         */
        public abstract Color traceRay(Ray ray);

}

