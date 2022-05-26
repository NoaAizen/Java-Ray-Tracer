package lighting;

import primitives.*;

public class AmbientLight extends Light {

    // ***************** constructor ********************** //

    /**
     * constructor, Final power of ambient lighting
     * @param Ia
     * @param Ka
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
