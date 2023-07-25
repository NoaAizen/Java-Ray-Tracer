 package renderer;

import Scene.Scene;
import primitives.Color;
import primitives.Ray;

 /**
  * The abstract class RayTracerBase serves as the base class for ray tracing algorithms.
  *
  * Authors: Noga Jacobs and Noa
  */
 public abstract class RayTracerBase {
     /**
      * Scene object that holds all the scene elements (geometry, lights, etc.).
      */
     protected Scene scene;

     // ***************** Constructors ********************

     /**
      * Constructor for RayTracerBase. Sets the scene that the ray tracer will work on.
      *
      * @param scene - The Scene object representing the 3D scene to be rendered.
      */
     public RayTracerBase(Scene scene) {
         this.scene = scene;
     }
     /**
      * This is the main method responsible for calculating the color of a pixel based on a ray.
      * It will be implemented in the concrete subclasses that extend RayTracerBase.
      *
      * @param ray - The ray for which the color needs to be calculated.
      * @return Color - The color of the pixel as determined by the ray tracing algorithm.
      */
     public abstract Color traceRay(Ray ray);

}

