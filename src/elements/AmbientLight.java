package elements;
import primitives.*;


public class AmbientLight {
    private final Color intensity;//עוצמה-של צבע

    public  AmbientLight(Color Ia,Double3 Ka){
        intensity=Ia.scale(Ka);
    }

    public Color getIntensity() {
        return intensity;
    }

    public AmbientLight() {
        intensity=Color.BLACK;
    }
}
