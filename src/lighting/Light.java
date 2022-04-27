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
     * func get
     * @return
     */
    public Color getIntensity() {
        return intensity;
    }

}
