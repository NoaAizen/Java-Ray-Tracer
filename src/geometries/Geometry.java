package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;

/**
 * The Geometry abstract class is the base class for all geometries that require a normalized vector.
 * It extends the Intersectable class.
 * Geometry objects represent 3D geometrical shapes that can be intersected with rays in a Cartesian coordinate system.
 * Each geometry has properties for emission color and material used for shading.
 * The getNormal method must be implemented by subclasses to provide the normalized vector at a given point on the geometry.
 *
 * Author: Noga Jacobs and Noa
 */

public abstract class Geometry extends Intersectable
{
    /**
     * The glowing color of the geometry
     */
    protected Color emission = Color.BLACK;
    /**
     * The material of the geometry used for shading
     */
    private Material material = new Material();

    // ***************** Getter ********************** //

    /**
     * Gets the glowing color of the geometry.
     *
     * @return The emission color
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Gets the material of the geometry used for shading.
     *
     * @return The material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Returns the normalized vector at the given point on the geometry.
     * Subclasses must implement this method to provide the correct normal vector for each specific geometry.
     *
     * @param point The point on the geometry
     * @return The normalized vector at the given point
     */
    public abstract Vector getNormal (Point point);

    // ***************** Setter ********************** //

    /**
     * Sets the glowing color of the geometry using a builder pattern.
     *
     * @param emission The emission color to set
     * @return The current geometry object
     */
    public Geometry setEmission(Color emission){
        this.emission = emission;
        return this;
    }

    /**
     * Sets the material of the geometry using a builder pattern.
     *
     * @param material The material to set
     * @return The current geometry object
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

}