package lighting;
import primitives.*;

//עשינו בכיתה
public class AmbientLight {
    //Intensity of color
    private final Color intensity;

    /**
     *
     * @param Ia
     * @param Ka
     */
    public  AmbientLight(Color Ia,Double3 Ka){
        intensity=Ia.scale(Ka);
    }

    /**
     * func getter
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }

    /**
     * constructor, put black in intensity
     */
    public AmbientLight() {
        intensity=Color.BLACK;
    }
}
