package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * this is the interface for all geometries that need to get a normalized vector.its the most basic interface for all geometries.
 */
// * @author noga and noa

public abstract class Geometry extends Intersectable
{
    /**
     *  glowing color
     */
    protected Color emission = Color.BLACK;
    /**
     * parameter of a department that is responsible for entering data into coefficients
     */
    private Material material = new Material();

    // ***************** Getter ********************** //

    /**
     * func getter
     * @return Color
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * func getter
     * @return Material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * func getter
     * @param point
     * @return Vector
     */
    public abstract Vector getNormal (Point point);

    // ***************** Setter ********************** //

    /**
     * func setter with build
     * @param emission
     * @return Geometry
     */
    public Geometry setEmission(Color emission){
        this.emission = emission;
        return this;
    }

    /**
     * func setter type builder
     * @param material
     * @return Geometry
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

}