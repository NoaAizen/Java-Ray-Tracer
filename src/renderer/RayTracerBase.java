package renderer;

import Scene.Scene;
import primitives.Color;
import primitives.Ray;

public abstract class RayTracerBase {//abstract
        protected Scene _scene;

        /**
         * constructor for class RayTracerBase
         */
        public RayTracerBase() {
                _scene = scene;
        }

        /**
         *
         * @param ray
         * @return color from class primitives.Color
         */
        public abstract  Color treaceRay(Ray ray){
                //ומחזירה צבע (Color
                return null;
        }

        /**
         *  public void renderImage() {
         *
         *         }
         */
}


