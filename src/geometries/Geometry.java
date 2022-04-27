package geometries;

import primitives.Color;
import primitives.Ray;
import primitives.Vector;
import primitives.Point;

import java.util.List;

/**
 * this is the interface for all geometries that need to get a normalized vector.its the most basic interface for all geometries.
 */

public abstract class Geometry extends Intersectable
{
    protected Color emission = Color.BLACK;

    /**
     * func gett
     * @return Color
     */
    public Color getEmission() {
        return emission;
    }

    /**
     *
     * @param point
     * @return Vector
     */
    public abstract Vector getNormal (Point point);






        /**
         *
         * @param emission
         * @return
         */
        public Geometry setEmission(Color emission){
            this.emission = emission;
            return this;
        }

}