package renderer;

import Scene.Scene;
import primitives.Color;
import primitives.Ray;
//עשינו בכיתה
public abstract class RayTracerBase {
        protected Scene scene;

        public RayTracerBase(Scene scene) {
                this.scene = scene;
        }

        public abstract Color traceRay(Ray ray);

        public void renderImage() {

        }
}

