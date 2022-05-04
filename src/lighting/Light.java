package lighting;

import primitives.Color;

abstract class Light {

    private Color intensity;

    /**
     * constructor
     * @param intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * func get for  intensity
     * @return color
     */
    public Color getIntensity() {
        return intensity;
    }

}
