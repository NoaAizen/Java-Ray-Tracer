package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * AmbientLight class represents ambient light in a scene.
 * Ambient light is uniform light that illuminates all objects in the scene equally.
 * It has an intensity and color, but no specific direction or position.
 *
 * Authors: Noga Jacobs and Noa
 */
public class AmbientLight extends Light {

    // ***************** Constructor ********************** //

    /**
     * Constructor for creating an ambient light with the specified intensity and discount factor.
     *
     * @param Ia The intensity of the ambient light.
     * @param Ka The discount factor for the ambient light.
     */
    public AmbientLight(Color Ia, Double3 Ka) {
       super(Ia.scale(Ka));
    }

    /**
     * Default constructor for creating an ambient light with black intensity (no light).
     * This is typically used when ambient light is not present in the scene.
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
}
