package lighting;

import primitives.Color;
/**
 * Light class is an abstract class representing a light source in a scene.
 * It contains the color intensity of the light, which determines the brightness of the light source.
 *
 * Authors: Noga Jacobs and Noa
 */
abstract class Light {

    /**
     * The color intensity of the light source.
     */
    private Color intensity;

    // ***************** Constructor ********************** //

    /**
     * Constructor for creating a light source with the specified intensity.
     *
     * @param intensity The color intensity of the light.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    // ***************** Getter ********************** //

    /**
     * Get the color intensity of the light source.
     *
     * @return The color intensity of the light.
     */
    public Color getIntensity() {
        return intensity;
    }

}
