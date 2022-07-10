package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * @author noga and noa
 */
public class AmbientLight extends Light {

    // ***************** constructor ********************** //

    /**
     * constructor, Final power of ambient lighting
     * @param Ia- Color
     * @param Ka-Double3 for the Discount factor
     */
    public AmbientLight(Color Ia, Double3 Ka) {
       super(Ia.scale(Ka));
    }

    /**
     * constructor, put black in intensity of class light
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
}
