package lighting;

import primitives.Color;

abstract class Light {

    /**
     *Color intensity
     */
    private Color intensity;

    // ***************** constructor ********************** //

    /**
     * constructor
     * @param intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    // ***************** getter ********************** //

    /**
     * func get for  intensity
     * @return color
     */
    public Color getIntensity() {
        return intensity;
    }

}
