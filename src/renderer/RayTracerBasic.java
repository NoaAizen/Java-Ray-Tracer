package renderer;

import Scene.Scene;
import primitives.Color;
import primitives.Ray;

/**
 * ). יש להוסיף תיעוד למחלקה ולבנאי.
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     * constructor for class RayTracerBasic, use  constructor from class RayTracerBase
     * @param scene
     */
    public RayTracerBasic(Scene scene) {
        //בנאי מפעיל את הבנאי של מחלקת האב ומימוש ריק (עם חריגה או עם החזרת ערך null).
        super();//מפעיל את הבנאי של מחלקת האב
    }

    @Override
    public Color treaceRay(Ray ray) {
        return null;
    }
}