package renderer;

import Scene.Scene;
import primitives.Color;
import primitives.Ray;
public abstract class RayTracerBase {
        protected Scene scene;

        /**
         * set of scene
         * @param scene
         */
        public RayTracerBase(Scene scene) {
                this.scene = scene;
        }

        /**
         * @param ray
         * @return Color
         */
        public abstract Color traceRay(Ray ray);


        public void renderImage() {  }
}

